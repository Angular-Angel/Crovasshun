/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package display;

import crovasshun.Body;
import crovasshun.LargeObject;
import crovasshun.Terrain;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author angle
 */
public class LocalAreaScreen extends Screen {
    private CombatScreen combatPane;
    
    private int panX, panY;

    public LocalAreaScreen(CombatScreen combatPane) {
        this(15, combatPane);
    }
    
    public LocalAreaScreen(int borderSize, CombatScreen combatPane) {
        super();
        this.combatPane = combatPane;
        setFont(new Font("Monospaced", Font.PLAIN, 12));
        setBackground(Color.BLACK);
        panX = 10;
        panY = 10;
        
        LocalAreaScreen localAreaScreen = this;
        
        MouseAdapter mouseAdapter;
        mouseAdapter = new MouseAdapter() {
            
            Point mousePoint = null;
            
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
                    point.x -= panX;
                    point.y -= panY;
                    combatPane.showTileReadout(combatPane.area.getDetails(point));
                    
                } catch (IllegalArgumentException ex) { 
                    System.out.println(ex);
                    combatPane.hideTileReadout(); 
                }
            }
            
            @Override
            public void mouseReleased(MouseEvent e) {
                mousePoint = null;
                localAreaScreen.repaint();
            }

        };
        
        addMouseListener(mouseAdapter);
        addMouseMotionListener(mouseAdapter);
        
    }
    
    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        super.paintComponent(g2);
        
        g2.translate(panX, panY);
        
        for (Terrain t : combatPane.area.terrain) {
            t.draw(g2);
        }
        
        for (LargeObject l : combatPane.area.objects) {
            l.draw(g2);
        }
        
        for (Body b : combatPane.area.bodies) {
            b.draw(g2);
        }
        
    }
    
}
