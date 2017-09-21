/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crovasshun;

import display.ASCIISprite;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Point;

/**
 *
 * @author angle
 */
public class Body {
    public final String name;
    public Point position;
    private ASCIISprite mapSprite, displaySprite;
    
    public Body(String name, ASCIISprite mapsprite, ASCIISprite displaySprite) {
        this(name, mapsprite, displaySprite, new Point(0, 0));
    }
    
    public Body(String name, ASCIISprite mapsprite, ASCIISprite displaySprite, Point position) {
        this.name = name;
        this.mapSprite = mapsprite;
        this.displaySprite = displaySprite;
        this.position = position;
    }
    
    public ASCIISprite getMapSprite() {
        return mapSprite;
    }
    
    public ASCIISprite getDisplaySprite() {
        return displaySprite;
    }
    
    public void draw(Graphics2D g) {
        g.setFont(new Font("Monospaced", Font.PLAIN, 12));
        FontMetrics m = g.getFontMetrics();
        g.translate(m.stringWidth(" ") * mapSprite.getWidth()/2, m.getHeight() * mapSprite.getHeight()/2);
        mapSprite.draw(position, g);
        g.translate(-m.stringWidth(" ") * mapSprite.getWidth()/2, -m.getHeight() * mapSprite.getHeight()/2);
    }
}
