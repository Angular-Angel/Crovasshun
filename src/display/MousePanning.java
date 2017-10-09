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
public class MousePanning extends MouseAdapter implements ControlMode {
            
    Point mousePoint;
    private LocalAreaScreen localAreaScreen;

    @Override
    public void mousePressed(MouseEvent e) {
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

    @Override
    public void mouseReleased(MouseEvent e) {
        mousePoint = null;
        localAreaScreen.repaint();
    }

    @Override
    public void init(CombatScreen combatScreen) {
        this.localAreaScreen = combatScreen.localAreaScreen;
        localAreaScreen.addMouseListener(this);
        localAreaScreen.addMouseMotionListener(this);
    }

    @Override
    public void end() {
        localAreaScreen.removeMouseListener(this);
        localAreaScreen.removeMouseMotionListener(this);
    }

};