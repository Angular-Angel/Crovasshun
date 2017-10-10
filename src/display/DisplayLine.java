/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package display;

import java.awt.Graphics2D;
import java.awt.Point;

/**
 *
 * @author angle
 */
public class DisplayLine implements Drawable {
    
    public Point start, end;

    @Override
    public void draw(Graphics2D g) {
        g.drawLine(start.x, start.y, end.x, end.y);
    }
    
}
