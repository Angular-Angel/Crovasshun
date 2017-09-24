/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crovasshun;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.util.ArrayList;

/**
 *
 * @author angle
 */
public class LocalArea {
    
    private int width;
    private int height;
    
    public ArrayList<Terrain> terrain;
    public ArrayList<Body> bodies;
    public ArrayList<LargeObject> objects;
    
    public LocalArea(int width, int height) {
        this.height = height;
        this.width = width;
        terrain = new ArrayList<>();
        bodies = new ArrayList<>();
        objects = new ArrayList<>();
    }
    
    public LocalArea(int width, int height, TerrainType t) {
        this(width, height);
        
        terrain.add(new Terrain(new Area(new Rectangle(width, height)), t));
    }

    /**
     * @return the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * @return the height
     */
    public int getHeight() {
        return height;
    }
    
    public void splitTerrain(Terrain t, Point point) {
        Area newArea = new Area(t.area);
        point.y -= t.area.getBounds().height;
        t.area.subtract(new Area(new Rectangle(point.x, point.y, 
                t.area.getBounds().width, t.area.getBounds().height*2)));
        newArea.subtract(t.area);
        Terrain newTerrain = new Terrain(newArea, t.type);
        terrain.add(newTerrain);
    }
    
    public void addTerrainObject(TerrainObject terrainObject) {
        Rectangle bounds = terrainObject.getFootprint().getBounds();
        Point point = bounds.getLocation();
        point.x += bounds.width/2;
        point.y += bounds.height/2;
        for (int i = 0; i < terrain.size(); i++) {
            Terrain t = terrain.get(i);
            t.subtract(terrainObject);
            if (!t.area.isSingular())
                splitTerrain(t, point);
        }
        objects.add(terrainObject);
    } 
    
}
