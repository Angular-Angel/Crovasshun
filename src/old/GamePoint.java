/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package old;

import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author angle
 */
public class GamePoint {
    public final TerrainType terrain;
    public final Point point;
    
    public ArrayList<Body> bodies;
    
    public ArrayList<LargeObject> objects;
    
    public GamePoint(TerrainType terrain, Point point) {
        this.point = point;
        this.terrain = terrain;
        bodies = new ArrayList<>();
        objects = new ArrayList<>();
    }
}
