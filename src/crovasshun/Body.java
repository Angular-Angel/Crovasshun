/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crovasshun;

import display.ASCIISprite;
import display.Drawable;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;

/**
 *
 * @author angle
 */
public class Body implements Footprint, Drawable {
    public final String name;
    public final Shape shape;
    private Actor actor;
    private ASCIISprite mapSprite, displaySprite;
    
    public Body(String name, ASCIISprite mapsprite, ASCIISprite displaySprite, Shape shape) {
        this.name = name;
        this.mapSprite = mapsprite;
        this.displaySprite = displaySprite;
        this.shape = shape;
    }
    
    public ASCIISprite getMapSprite() {
        return mapSprite;
    }
    
    public ASCIISprite getDisplaySprite() {
        return displaySprite;
    }
    
    @Override
    public void draw(Graphics2D g) {
        g.setFont(new Font("Monospaced", Font.PLAIN, 12));
        FontMetrics m = g.getFontMetrics();
        mapSprite.draw(getPosition(), g);
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

    /**
     * @return the actor
     */
    public Actor getActor() {
        return actor;
    }

    /**
     * @param actor the actor to set
     */
    public void setActor(Actor actor) {
        this.actor = actor;
    }
    
    public class BodyPart {
        
    }
}
