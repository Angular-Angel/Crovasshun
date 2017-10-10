/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package display;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 *
 * @author angle
 */
public class MouseMovePlotting extends MouseAdapter implements ControlMode {

    private CombatScreen combatScreen;
    private LocalAreaScreen localAreaScreen;
    private Pointer mousePointer;
    private DisplayLine pointerLine;
    private MovePath movePath;
    private boolean showingPointer;

    public MouseMovePlotting() {
        this.mousePointer = new Pointer(new Point());
        showingPointer = false;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == 1) {
            Point point = e.getPoint();
            point.x -= localAreaScreen.panX;
            point.y -= localAreaScreen.panY;
            if (combatScreen.area.hasTerrain(point)) {
                movePath.addPoint(new Pointer(point));
                pointerLine.start = point;
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == 3) {
            Point point = e.getPoint();
            point.x -= localAreaScreen.panX;
            point.y -= localAreaScreen.panY;
            
            movePath.removePoint(point);
            pointerLine.start = movePath.getEnd();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        Point point = e.getPoint();
        point.x -= localAreaScreen.panX;
        point.y -= localAreaScreen.panY;
        if (combatScreen.area.hasTerrain(point)) {
            if (!showingPointer) {
                localAreaScreen.drawables.add(mousePointer);
                localAreaScreen.drawables.add(pointerLine);
                showingPointer = true;
            }
            mousePointer.point.setLocation(point);
            localAreaScreen.repaint();
        } else if (showingPointer) {
            localAreaScreen.drawables.remove(mousePointer);
            localAreaScreen.drawables.remove(pointerLine);
            showingPointer = false;
            localAreaScreen.repaint();
        }
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
        if (showingPointer) {
            localAreaScreen.drawables.remove(mousePointer);
            localAreaScreen.drawables.remove(pointerLine);
            System.out.println(localAreaScreen.drawables);
            showingPointer = false;
            localAreaScreen.repaint();
        }
    }
    
    @Override
    public void init(CombatScreen combatScreen) {
        this.combatScreen = combatScreen;
        this.localAreaScreen = combatScreen.localAreaScreen;
        localAreaScreen.addMouseListener(this);
        localAreaScreen.addMouseMotionListener(this);
        
        this.movePath =  new MovePath(combatScreen.combatLoop.player.body.getCenterPoint());
        localAreaScreen.drawables.add(movePath);
        this.pointerLine = new DisplayLine(movePath.getEnd(), mousePointer.point);
        localAreaScreen.drawables.add(pointerLine);
    }

    @Override
    public void end() {
        localAreaScreen.removeMouseListener(this);
        localAreaScreen.removeMouseMotionListener(this);
        if (showingPointer) {
            localAreaScreen.drawables.remove(mousePointer);
            localAreaScreen.drawables.remove(pointerLine);
            showingPointer = false;
        }
    }
}
