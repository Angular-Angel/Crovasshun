/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package display;

import crovasshun.Body;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;

/**
 *
 * @author angle
 */
public class BodyScreen extends Screen {
    
    public final Body body;
    
    public BodyScreen(Body body) {
        this.body = body;
        setFont(new Font("Monospaced", Font.PLAIN, 12));
        FontMetrics m = getFontMetrics(getFont());
        Dimension dimension = new Dimension();
        ASCIISprite displaySprite = body.getDisplaySprite();
        System.out.println(displaySprite.getWidth());
        dimension.width = Math.max(m.stringWidth(body.name) + 10, 10 + displaySprite.getWidth() * m.stringWidth(" "));
        dimension.height = m.getHeight() + 10 + displaySprite.getHeight() * m.getHeight();
        setPreferredSize(dimension);
        
    }
    
    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        super.paintComponent(g2);;
        FontMetrics m = g.getFontMetrics();
        g2.drawString(body.name, 10, m.getHeight());
        Point position = new Point();
        ASCIISprite displaySprite = body.getDisplaySprite();
        position.x = displaySprite.getWidth() * m.stringWidth(" ") / 2;
        position.y = 2 + m.getHeight() + displaySprite.getHeight() * m.getHeight() / 2;
        
        displaySprite.draw(position, g2);
        
    }
    
}
