/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crovasshun;

import display.ASCIITexture;
import display.Drawable;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;

/**
 *
 * @author angle
 */
public class LargeObject implements Footprint, Drawable {
    private Shape shape;
    public final String name;
    public ASCIITexture texture;
    
    public LargeObject(String name, Shape shape, ASCIITexture texture) {
        this.name = name;
        this.shape = shape;
        this.texture = texture;
    }
    
    @Override
    public void draw(Graphics2D g) {
        texture.fillShape(shape, g);
        g.setColor(Color.WHITE);
        g.draw(shape);
    }

    @Override
    public Shape getFootprint() {
        return shape;
    }

    @Override
    public Point getPosition() {
        return new Point(shape.getBounds().getLocation());
    }

    @Override
    public Point getCenterPoint() {
        Rectangle bounds = shape.getBounds();
        Point point = getPosition();
        point.x += bounds.width/2;
        point.y += bounds.height/2;
        return point;
    }
    
}
