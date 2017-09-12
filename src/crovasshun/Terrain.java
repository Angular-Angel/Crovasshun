/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crovasshun;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;

/**
 *
 * @author angle
 */
public class Terrain {
    public final Point position;
    public final Area area;
    public final TerrainType type;
    
    public Terrain(Point position, Area area, TerrainType type) {
        this.position = position;
        this.area = area;
        this.type = type;
    }
    
    public void draw(Graphics2D g) {
        g.translate(-position.x, -position.y);
        g.draw(area);
        type.appearance.fillShape(area, g);
    }
    
    public void subtract(Footprint footprint) {
        Area subArea = new Area(footprint.getFootprint());
        Point footPos = footprint.getPosition();
        subArea.transform(AffineTransform.getTranslateInstance(footPos.x, footPos.y));
        area.subtract(subArea);
    } 
    
}
