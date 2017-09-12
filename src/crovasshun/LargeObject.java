/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crovasshun;

import display.ASCIITexture;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;

/**
 *
 * @author angle
 */
public class LargeObject implements Footprint {
    private Shape shape;
    public final String name;
    public ASCIITexture texture;
    private Point position;
    
    public LargeObject(String name, Shape shape, ASCIITexture texture, Point position) {
        this.name = name;
        this.shape = shape;
        this.texture = texture;
        this.position = position;
    }
    
    public void draw(Graphics2D g) {
        g.translate(position.x, position.y);
        g.draw(shape);
        texture.fillShape(shape, g);
    }

    @Override
    public Shape getFootprint() {
        return shape;
    }

    @Override
    public Point getPosition() {
        return new Point(position);
    }
    
}
