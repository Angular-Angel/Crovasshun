/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crovasshun;

import display.BodyScreen;
import display.LargeObjectScreen;
import display.Screen;
import display.TerrainScreen;
import java.awt.Point;
import java.awt.Rectangle;
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
    
    public LocalArea() {
        width = 0;
        height = 0;
        terrain = new ArrayList<>();
        bodies = new ArrayList<>();
        objects = new ArrayList<>();
    }
    
    public LocalArea(int width, int height, TerrainType t) {
        this();
        
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
    
    public void addTerrain(Terrain t) {
        Rectangle bounds = t.getFootprint().getBounds();
        if (bounds.x + bounds.width > width) width = bounds.x + bounds.width;
        if (bounds.y + bounds.height > height) height = bounds.y + bounds.height;
    }
    
    public boolean hasTerrain(int x, int y) {
        for (Terrain t : terrain) {
            if (t.area.contains(new Point(x, y)))
                return true;
        }
        return false;
    }
    
    public boolean hasTerrain(Point point) {
        return hasTerrain(point.x, point.y);
    }
    
    public Terrain getTerrain(int x, int y) {
        for (Terrain t : terrain) {
            if (t.area.contains(new Point(x, y)))
                return t;
        }
        throw new IllegalArgumentException("Bad X or Y value: " + x + ", " + y);
    }
    
    public Terrain getTerrain(Point point) {
        return getTerrain(point.x, point.y);
    }
    
    public GamePoint getPoint(int x, int y) { 
        //if (x < 0 || x >= width || y < 0 || y >= height) throw new IllegalArgumentException("Bad X or Y value: " + x + ", " + y); 
        GamePoint hex = new GamePoint(getTerrain(x, y).type, new Point(x, y)); 
        for (Body b : bodies) { 
            if (b.getFootprint().contains(new Point(x, y))) 
                hex.bodies.add(b); 
        } 
        return hex; 
    }
     
    public GamePoint getPoint(Point point) { 
        return getPoint(point.x, point.y); 
    }
    
    public ArrayList<Screen> getDetails(Point point) {
        ArrayList<Screen> ret = new ArrayList<>();
        try {
            ret.add(new TerrainScreen(getTerrain(point).type));
        } catch(IllegalArgumentException ex) {
            
        }
        
        for (Body b : bodies) { 
            if (b.getFootprint().contains(point)) 
                ret.add(new BodyScreen(b));
        }
        
        for (LargeObject l : objects) { 
            if (l.getFootprint().contains(point)) 
                ret.add(new LargeObjectScreen(l));
        }
        
        return ret;
    }
    
}
