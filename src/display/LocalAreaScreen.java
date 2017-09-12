/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package display;

import crovasshun.Body;
import crovasshun.LargeObject;
import crovasshun.LocalArea;
import crovasshun.LocalMapGenerator;
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
    private CombatPane combatPane;
    
    private LocalArea area;

    public LocalAreaScreen(CombatPane combatPane) {
        this(15, combatPane);
    }
    
    public LocalAreaScreen(int borderSize, CombatPane combatPane) {
        super();
        this.combatPane = combatPane;
        setFont(new Font("Monospaced", Font.PLAIN, 12));
        setBackground(Color.BLACK);
        
        LocalAreaScreen localAreaScreen = this;
        
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                
            }

            
        });
        
        area = LocalMapGenerator.getObelisk(1600, 800);
        
        area.bodies.add(new Body("Player", new ASCIISprite(new Color(30, 30, 30, 255), new Color(255, 182, 193), "_ |\n" +
                                                                                     "-0-"), 
                new ASCIISprite(new Color(0, 0, 0, 0), new Color(255, 182, 193), "   0\n" +
                                                          "1_/|\\_O\n" +
                                                          "   |\n" +
                                                          "  / \\\n" +
                                                          "  | |"),new Point(105, 150)));
    }
    
    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        super.paintComponent(g2);
        
        g2.translate(10, 10);
        
        for (Terrain t : area.terrain) {
            t.draw(g2);
        }
        
        for (Body b : area.bodies) {
            b.draw(g2);
        }
        
        for (LargeObject l : area.objects) {
            l.draw(g2);
        }
    }
    
}
