/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crovasshun;

import java.util.ArrayList;

/**
 *
 * @author angle
 */
public class LocalArea {
    
    private int width;
    private int height;
    
    private Terrain[][] terrain;
    public ArrayList<Body> bodies;
    
    public LocalArea(int width, int height) {
        this.height = height;
        this.width = width;
        terrain = new Terrain[width][height];
        bodies = new ArrayList<>();
    }
    
    public LocalArea(int width, int height, Terrain t) {
        this(width, height);
        setAll(t);
    }
    
    public void setAll(Terrain t) {
        for (int i = 0; i < terrain.length; i++) {
            for (int j = 0; j < terrain[i].length; j++) {
                terrain[i][j] = t;
            }
        }
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
    
    public Terrain getTerrain(int x, int y) {
        if (x < 0 || x >= width || y < 0 || y >= height) throw new IllegalArgumentException("Bad X or Y value: " + x + ", " + y);
        return terrain[x][y];
    }
    
    public void setTerrain(int x, int y, Terrain terrain) {
        if (x < 0 || x >= width || y < 0 || y >= height) throw new IllegalArgumentException("Bad X or Y value: " + x + ", " + y);
        this.terrain[x][y] = terrain;
    }
    
}
