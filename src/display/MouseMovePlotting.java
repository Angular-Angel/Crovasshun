/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package display;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author angle
 */
public class MouseMovePlotting extends MouseAdapter implements ControlMode {

    private CombatScreen combatScreen;
    private LocalAreaScreen localAreaScreen;
    private Pointer pointer;
    private boolean showingPointer;

    public MouseMovePlotting() {
        this.pointer = new Pointer(new Point());
        showingPointer = false;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }


    @Override
    public void mouseMoved(MouseEvent e) {
        Point point = e.getPoint();
        point.x -= localAreaScreen.panX;
        point.y -= localAreaScreen.panY;
        if (combatScreen.area.hasTerrain(point)) {
            if (!showingPointer) {
                localAreaScreen.drawables.add(pointer);
                showingPointer = true;
            }
            pointer.point.setLocation(point);
            localAreaScreen.repaint();
        } else if (showingPointer) {
            localAreaScreen.drawables.remove(pointer);
            showingPointer = false;
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
            localAreaScreen.drawables.remove(pointer);
            showingPointer = false;
        }
    }
}
