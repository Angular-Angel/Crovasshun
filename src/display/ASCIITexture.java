/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package display;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.util.Random;

/**
 *
 * @author angle
 */
public class ASCIITexture {
    
    public final char[] chars;
    public final Color[] colors;
    public final Color background;
    public final boolean random;
    
    public ASCIITexture(Color color, char[] chars) {
        this(color, Color.BLACK, chars);
    }
    
    public ASCIITexture(Color[] colors, char[] chars) {
        this(colors, Color.BLACK, chars, true);
    }
    
    public ASCIITexture(Color color, Color background, char[] chars) {
        this(color, background, chars, true);
    }
    
    public ASCIITexture(Color color, Color background, char[] chars, boolean random) {
        Color[] colors = new Color[1];
        colors[0] = color;
        this.colors = colors;
        this.background = background;
        this.chars = chars;
        this.random = random;
    }
    
    public ASCIITexture(Color color, Color background, char character, boolean random) {
        Color[] colors = new Color[1];
        colors[0] = color;
        this.colors = colors;
        this.background = background;
        char[] chars = new char[1];
        chars[0] = character;
        this.chars = chars;
        this.random = random;
    }
    
    public ASCIITexture(Color[] colors, Color background, char[] chars) {
        this(colors, background, chars, true);
    }
    
    public ASCIITexture(Color[] colors, Color background, char[] chars,  boolean random) {
        this.colors = colors;
        this.background = background;
        this.chars = chars;
        this.random = random;
    }
    
    public void fillShape(Shape shape, Graphics2D g) {
        
        g.setColor(background);
        g.fill(shape);
        
        Rectangle bounds = shape.getBounds();
        
        Random randomGen = new Random(bounds.x + 7 * bounds.y);
        
        FontMetrics m = g.getFontMetrics();
        int border = 2, i = 0;
        
        String string = "";
        int yi = 2;
        
        while(yi <= bounds.height) {
            int leftX = bounds.x;
            while (!shape.contains(new Rectangle(leftX, bounds.y + yi, 10, m.getHeight() + border)) && leftX < bounds.width) {
                leftX++;
            }
            i = 0;
            while(shape.contains(new Rectangle(leftX, bounds.y + yi, m.stringWidth(string) + (border*2), m.getHeight() + border))) {
                if (random) string += this.chars[randomGen.nextInt(this.chars.length)];
                else string += this.chars[i % this.chars.length];
                i++;
            }
            if (this.colors.length > 1) {
                int xi = 0;
                i = 0;
                while (string.length() > 0) {
                    if (random) g.setColor(this.colors[randomGen.nextInt(this.colors.length)]);
                    else g.setColor(this.colors[i % this.colors.length]);
                    g.drawString(string.substring(0, 1), leftX + xi, bounds.y + yi + m.getHeight());
                    xi += m.stringWidth(string.substring(0, 1));
                    string = string.substring(1, string.length());
                    i++;
                }
            } else {
                g.setColor(this.colors[0]);
                g.drawString(string, leftX, bounds.y + yi + m.getHeight());
            }
            string = "";
            yi += m.getHeight();
        }
        
    }
    
}
