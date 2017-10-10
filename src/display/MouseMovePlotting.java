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
    public ArrayList<Pointer> pointers;
    private boolean showingPointer;

    public MouseMovePlotting() {
        this.mousePointer = new Pointer(new Point());
        this.pointers =  new ArrayList<>();
        showingPointer = false;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == 1) {
            Point point = e.getPoint();
            point.x -= localAreaScreen.panX;
            point.y -= localAreaScreen.panY;
            if (combatScreen.area.hasTerrain(point)) {
                Pointer pointer = new Pointer(point);
                pointers.add(pointer);
                localAreaScreen.drawables.add(pointer);
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == 3 && pointers.size() > 0) {
            Point point = e.getPoint();
            point.x -= localAreaScreen.panX;
            point.y -= localAreaScreen.panY;
            
            Pointer pointer = null;
            
            double distance = 75;
            
            for (Pointer curPointer : pointers) {
                if (curPointer.point.distance(point) < distance) {
                    pointer = curPointer;
                    distance = curPointer.point.distance(point);
                }
            }
            
            pointers.remove(pointer);
            localAreaScreen.drawables.remove(pointer);
            localAreaScreen.repaint();
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
                showingPointer = true;
            }
            mousePointer.point.setLocation(point);
            localAreaScreen.repaint();
        } else if (showingPointer) {
            localAreaScreen.drawables.remove(mousePointer);
            showingPointer = false;
            localAreaScreen.repaint();
        }
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
        if (showingPointer) {
            localAreaScreen.drawables.remove(mousePointer);
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
    }

    @Override
    public void end() {
        localAreaScreen.removeMouseListener(this);
        localAreaScreen.removeMouseMotionListener(this);
        if (showingPointer) {
            localAreaScreen.drawables.remove(mousePointer);
            showingPointer = false;
        }
    }
}
