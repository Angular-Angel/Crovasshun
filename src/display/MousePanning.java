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

/**
 *
 * @author angle
 */
public class MousePanning extends MouseAdapter implements ControlMode {
            
    Point mousePoint;
    boolean dragging = false;
    private LocalAreaScreen localAreaScreen;

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == 1) {
            mousePoint = e.getPoint();
            dragging = true;
        }
    }
    
    public void mouseMoved(MouseEvent e) {
        mousePoint = e.getPoint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (mousePoint != null) {
            localAreaScreen.panX += e.getX() - mousePoint.x;
            localAreaScreen.panY += e.getY() - mousePoint.y;
            mousePoint = e.getPoint();
            localAreaScreen.repaint();
        }
    }
    
    public void mouseExited(MouseEvent e) {
        mousePoint = null;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mousePoint = null;
        dragging = false;
        localAreaScreen.repaint();
    }
    
    public void step(long dt) {
        /*if (mousePoint == null) 
            return;
        if (mousePoint.x < 100)
            localAreaScreen.panX += dt;
        if (mousePoint.x > localAreaScreen.getWidth() - 100)
            localAreaScreen.panX -= dt;
        
        if (mousePoint.y < 100)
            localAreaScreen.panY += dt;
        if (mousePoint.y > localAreaScreen.getHeight() - 100)
            localAreaScreen.panY -= dt;*/
    }

    @Override
    public void init(CombatScreen combatScreen) {
        combatScreen.controlModes.add(this);
        this.localAreaScreen = combatScreen.localAreaScreen;
        localAreaScreen.addMouseListener(this);
        localAreaScreen.addMouseMotionListener(this);
    }

    @Override
    public void end() {
        localAreaScreen.combatScreen.controlModes.remove(this);
        localAreaScreen.removeMouseListener(this);
        localAreaScreen.removeMouseMotionListener(this);
    }

};