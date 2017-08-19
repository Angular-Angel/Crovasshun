/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crovasshun;

/**
 *
 * @author angle
 */
public class LocalArea {
    
    private int width;
    private int height;
    
    private Terrain[][] terrain;
    
    public LocalArea(int width, int height) {
        this.height = height;
        this.width = width;
        terrain = new Terrain[width][height];
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
        return terrain[x][y];
    }
    
}