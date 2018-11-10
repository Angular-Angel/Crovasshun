/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package old;

import java.awt.Point;
import java.awt.Shape;

/**
 *
 * @author angle
 */
public interface Footprint {
    
    public Shape getFootprint();
    
    public Point getPosition();
    
    public Point getCenterPoint();
}
