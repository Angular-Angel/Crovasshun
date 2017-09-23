/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crovasshun;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;

/**
 *
 * @author angle
 */
public class Terrain implements Footprint {
    public final Area area;
    public final TerrainType type;
    
    public Terrain(Area area, TerrainType type) {
        this.area = area;
        this.type = type;
    }
    
    public void draw(Graphics2D g) {
        g.translate(area.getBounds().x, area.getBounds().y);
        type.appearance.fillShape(area, g);
        g.setColor(Color.WHITE);
        //g.draw(area);
        g.translate(-area.getBounds().x, -area.getBounds().y);
    }
    
    public void subtract(Footprint footprint) {
        Shape shape = footprint.getFootprint();
        Area subArea = new Area(shape);
        Point footPos = footprint.getPosition();
        //System.out.println(shape.);
        subArea.transform(AffineTransform.getTranslateInstance(footPos.x, footPos.y));
        area.subtract(subArea);
    } 

    @Override
    public Shape getFootprint() {
        return new Area(area);
    }

    @Override
    public Point getPosition() {
        return new Point(area.getBounds().getLocation());
    }

    @Override
    public Point getCenterPoint() {
        Rectangle bounds = area.getBounds();
        Point point = getPosition();
        point.x += bounds.width/2;
        point.y += bounds.height/2;
        return point;
    }
    
}
