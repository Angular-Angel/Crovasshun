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
    public final Terrain terrain;
    public final Point point;
    
    public ArrayList<Body> bodies;
    
    public GameHex(Terrain terrain, Point point) {
        this.point = point;
        this.terrain = terrain;
        bodies = new ArrayList<>();
    }
}
