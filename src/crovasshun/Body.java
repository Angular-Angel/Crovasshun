/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crovasshun;

import display.ASCIIChar;
import java.awt.Point;

/**
 *
 * @author angle
 */
public class Body {
    public Point position;
    public ASCIIChar sprite;
    
    public Body(ASCIIChar sprite) {
        this(sprite, new Point(0, 0));
    }
    
    public Body(ASCIIChar sprite, Point position) {
        this.sprite = sprite;
        this.position = position;
    }
    
}
