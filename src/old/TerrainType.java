/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package old;

import display.ASCIITexture;

/**
 *
 * @author angle
 */
public class TerrainType {
    
    public final String name;
    public final int moveCost;
    public final ASCIITexture appearance;
    
    public TerrainType(String name, ASCIITexture appearance) {
        this(name, appearance, 1);
    } 
    
    public TerrainType(String name, ASCIITexture appearance, int moveCost) {
        this.name = name;
        this.appearance = appearance;
        this.moveCost = moveCost;
    }
    
}
