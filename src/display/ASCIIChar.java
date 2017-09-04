/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package display;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Point;

/**
 *
 * @author angle
 */
public class ASCIIChar {
    
    public final Color color;
    public final char character;
    
    public ASCIIChar(Color color, char character) {
        this.color = color;
        this.character = character;
    }
    
    public void draw(Point position, Graphics2D g) {
        g.setColor(color);
        FontMetrics m = g.getFontMetrics();
        g.drawString("" + character, position.x, position.y);
    }
    
}
