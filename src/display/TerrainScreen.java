/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package display;

import crovasshun.Terrain;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;

/**
 *
 * @author angle
 */
public class TerrainScreen extends Screen {
    
    public final Terrain terrain;
    
    public TerrainScreen(Terrain terrain) {
        this.terrain = terrain;
        FontMetrics m = getFontMetrics(new Font("Monospaced", Font.PLAIN, 12));
        int width = Math.max(m.stringWidth(" ")*10 + 15, m.stringWidth(terrain.name) + 15);
        setPreferredSize(new Dimension(width, (m.getHeight() + 1)*6 + 10));
    }
    
    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        super.paintComponent(g2);
        FontMetrics m = g.getFontMetrics();
        terrain.appearance.fillShape(new Rectangle(8, m.getHeight() + 5, m.stringWidth(" ")*10, (m.getHeight() + 1)*5), g2);
        g2.drawString(terrain.name, 10, m.getHeight());
    }
    
}
