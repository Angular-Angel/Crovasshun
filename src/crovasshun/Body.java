/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crovasshun;

import display.ASCIISprite;
import java.awt.Point;

/**
 *
 * @author angle
 */
public class Body {
    public final String name;
    public Point position;
    public ASCIISprite mapSprite;
    
    public Body(String name, ASCIISprite sprite) {
        this(name, sprite, new Point(0, 0));
    }
    
    public Body(String name, ASCIISprite sprite, Point position) {
        this.name = name;
        this.mapSprite = sprite;
        this.position = position;
    }
    
}
