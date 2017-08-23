/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package display;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Point;

/**
 *
 * @author angle
 */
public class ASCIISprite {
    
    public final Color color;
    public final char character;
    public final Font font;
    
    public ASCIISprite(Color color, char character) {
        this(color, character, new Font("Monospaced", Font.PLAIN, 80));
    }
    
    public ASCIISprite(Color color, char character, Font font) {
        this.color = color;
        this.character = character;
        this.font = font;
    }
    
    public void draw(Point position, Graphics2D g) {
        g.setColor(color);
        g.setFont(font);
        FontMetrics m = g.getFontMetrics();
        /*g.drawLine(position.x - m.stringWidth("" + character)/2, position.y, 
                   position.x - m.stringWidth("" + character)/2, position.y - m.getHeight());*/
        g.drawString("" + character, position.x - m.stringWidth("" + character)/2, position.y + m.getHeight()/2 - m.getDescent());
    }
    
}
