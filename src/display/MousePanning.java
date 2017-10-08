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
public class MousePanning extends MouseAdapter {
            
    Point mousePoint = null;
    private LocalAreaScreen localAreaScreen;

    public MousePanning(LocalAreaScreen localAreaScreen) {
        this.localAreaScreen = localAreaScreen;
    }
    
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
    public void mouseMoved(MouseEvent e) {
        try { 
            Point point = e.getPoint();
            point.x -= localAreaScreen.panX;
            point.y -= localAreaScreen.panY;
            localAreaScreen.combatScreen.showTileReadout(localAreaScreen.combatScreen.area.getDetails(point));

        } catch (IllegalArgumentException ex) { 
            System.out.println(ex);
            localAreaScreen.combatScreen.hideTileReadout(); 
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mousePoint = null;
        localAreaScreen.repaint();
    }

};