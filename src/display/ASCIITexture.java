/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package display;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.util.Random;

/**
 *
 * @author angle
 */
public class ASCIITexture {
    
    public final char[] chars;
    public final Color[] colors;
    public final Color background;
    public final Font font;
    
    public ASCIITexture(Color color, char[] chars) {
        this(color, Color.BLACK, chars);
    }
    
    public ASCIITexture(Color[] colors, char[] chars) {
        this(colors, Color.BLACK, chars, new Font("Monospaced", Font.PLAIN, 12));
    }
    
    public ASCIITexture(Color color, Color background, char[] chars) {
        Color[] colors = new Color[1];
        colors[0] = color;
        this.colors = colors;
        this.background = background;
        this.chars = chars;
        this.font = new Font("Monospaced", Font.PLAIN, 12);
    }
    
    public ASCIITexture(Color[] colors, Color background, char[] chars) {
        this(colors, background, chars, new Font("Monospaced", Font.PLAIN, 12));
    }
    
    public ASCIITexture(Color[] colors, Color background, char[] chars, Font font) {
        this.colors = colors;
        this.background = background;
        this.chars = chars;
        this.font = font;
    }
    
    public void fillPolygon(Polygon poly, Graphics2D g2) {
        
        g2.setColor(background);
        g2.fillPolygon(poly);
        g2.setColor(Color.WHITE);
        g2.drawPolygon(poly);
        
        Rectangle bounds = poly.getBounds();
        
        Random random = new Random(bounds.x + 7 * bounds.y);
        
        FontMetrics m = g2.getFontMetrics(font);
        g2.setFont(font);
        int border = 2;
        
        String string = "" + this.chars[random.nextInt(this.chars.length)];
        int centerX = bounds.x + bounds.width/2, yi = 0;
        
        while(yi <= bounds.height) {
            while(poly.contains(new Rectangle(centerX - (m.stringWidth(string)/2 + border), bounds.y + yi, m.stringWidth(string) + (border*2), m.getHeight() + border))) {
                string += this.chars[random.nextInt(this.chars.length)];
            }
            if (this.colors.length > 1) {
                int xi = -(m.stringWidth(string)/2) ;
                while (string.length() > 0) {
                    g2.setColor(this.colors[random.nextInt(this.colors.length)]);
                    g2.drawString(string.substring(0, 1), centerX + xi, bounds.y + yi + m.getHeight());
                    xi += m.stringWidth(string.substring(0, 1));
                    string = string.substring(1, string.length());
                }
            } else {
                g2.setColor(this.colors[0]);
                g2.drawString(string, centerX - (m.stringWidth(string)/2), bounds.y + yi + m.getHeight());
            }
            
            string = "";
            yi += m.getHeight();
        }
        
    }
    
}
