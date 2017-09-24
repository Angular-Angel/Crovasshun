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
public class GameHex {
    public final TerrainType terrain;
    public final Point point;
    
    public ArrayList<Body> bodies;
    
    public GameHex(TerrainType terrain, Point point) {
        this.point = point;
        this.terrain = terrain;
        bodies = new ArrayList<>();
    }
}
