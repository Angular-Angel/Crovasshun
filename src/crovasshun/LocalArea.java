/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crovasshun;

import java.awt.Point;
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
    
    /*public GameHex getHex(int x, int y) {
        if (x < 0 || x >= width || y < 0 || y >= height) throw new IllegalArgumentException("Bad X or Y value: " + x + ", " + y);
        GameHex hex = new GameHex(getTerrain(x, y), new Point(x, y));
        for (Body b : bodies) {
            if (b.position.equals(new Point(x, y)))
                hex.bodies.add(b);
        }
        return hex;
    }*/
    
    /*public GameHex getHex(Point point) {
        return getHex(point.x, point.y);
    }*/
    
}
