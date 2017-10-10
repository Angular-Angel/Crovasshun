/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package display;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

/**
 *
 * @author angle
 */
public class Pointer implements Drawable {
    public final Point point;
    public Color color;
    
    public Pointer(Point point) {
        this.point = point;
        this.color = Color.WHITE;
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(color);
        g.drawLine(point.x - 10, point.y, point.x + 10, point.y);
        g.drawLine(point.x, point.y - 10, point.x, point.y + 10);
        g.drawOval(point.x - 5, point.y - 5, 10, 10);
    }
}
