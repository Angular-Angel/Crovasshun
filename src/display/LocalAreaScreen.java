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
    public CombatScreen combatScreen;
    
    public int panX, panY;
    
    public MouseAdapter mouseMode;

    public LocalAreaScreen(CombatScreen combatScreen) {
        this(15, combatScreen);
    }
    
    public LocalAreaScreen(int borderSize, CombatScreen combatScreen) {
        super();
        this.combatScreen = combatScreen;
        setFont(new Font("Monospaced", Font.PLAIN, 12));
        setBackground(Color.BLACK);
        panX = 10;
        panY = 10;
        
        setMouseMode(new MousePanning(this));
    }
    
    public void setMouseMode(MouseAdapter mouseAdapter) {
        if (mouseMode != null) {
            removeMouseListener(mouseMode);
            removeMouseMotionListener(mouseMode);
        }
        
        mouseMode = mouseAdapter;
        
        addMouseListener(mouseMode);
        addMouseMotionListener(mouseMode);
    }
    
    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        super.paintComponent(g2);
        
        g2.translate(panX, panY);
        
        for (Terrain t : combatScreen.area.terrain) {
            t.draw(g2);
        }
        
        for (LargeObject l : combatScreen.area.objects) {
            l.draw(g2);
        }
        
        for (Body b : combatScreen.area.bodies) {
            b.draw(g2);
        }
        
    }
    
}
