/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package display;

import crovasshun.Body;
import crovasshun.LargeObject;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;

/**
 *
 * @author angle
 */
public class LargeObjectScreen extends Screen {
    public final LargeObject largeObject;
    
    public LargeObjectScreen(LargeObject largeObject) {
        this.largeObject = largeObject;
        setFont(new Font("Monospaced", Font.PLAIN, 12));
        FontMetrics m = getFontMetrics(getFont());
        Dimension dimension = new Dimension();
        Rectangle bounds = largeObject.getFootprint().getBounds();
        dimension.width = Math.max(m.stringWidth(largeObject.name) + 10, 10 + bounds.width);
        dimension.height = m.getHeight() + 10 + bounds.height;
        setPreferredSize(dimension);
        
    }
    
    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        super.paintComponent(g2);;
        FontMetrics m = g.getFontMetrics();
        g2.drawString(largeObject.name, 10, m.getHeight());
        Point position = largeObject.getPosition();
        g.translate(-position.x + 5, -position.y + m.getHeight() + 5);
        largeObject.draw(g2);
        g.translate(position.x - 5, position.y - m.getHeight() - 5);
    }
}
