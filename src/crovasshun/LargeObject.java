/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crovasshun;

import display.ASCIITexture;
import java.awt.Shape;
import java.awt.geom.Point2D;

/**
 *
 * @author angle
 */
public class LargeObject {
    public Shape shape;
    public final String name;
    public ASCIITexture texture;
    public Point2D.Float position;
    
    public LargeObject(String name, Shape shape, ASCIITexture texture) {
        this.name = name;
        this.shape = shape;
        this.texture = texture;
    }
    
}
