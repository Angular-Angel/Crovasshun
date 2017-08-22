/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crovasshun;

import display.ASCIITexture;
import java.awt.Color;
import java.util.HashMap;
import java.util.Random;

/**
 *
 * @author angle
 */
public class LocalMapGenerator {
    
    public static final HashMap<String, Terrain> terrainMap =  new HashMap<>();
    private static boolean initialized = false;
    private static Random random = new Random();
    
    public static void initialize() {
        Color[] dirtColors = new Color[3];
        dirtColors[0] = new Color(205,133,63);
        dirtColors[1] = new Color(244,164,96);
        dirtColors[2] = new Color(210,180,140);
        char[] dirtChars = new char[6];
        dirtChars[0] = '*';
        dirtChars[1] = '#';
        dirtChars[2] = '`';
        dirtChars[3] = '\'';
        dirtChars[4] = ',';
        dirtChars[5] = '.';
        terrainMap.put("Dirt", new Terrain("Dirt", new ASCIITexture(dirtColors, new Color(139,69,19), dirtChars)));
        
        Color[] grassColors = new Color[3];
        grassColors[0] = Color.GREEN;
        grassColors[1] = new Color(0, 130, 0);
        grassColors[2] = new Color(30, 180, 0);
        char[] grassChars = new char[5];
        grassChars[0] = ',';
        grassChars[1] = '\'';
        grassChars[2] = '"';
        grassChars[3] = '.';
        grassChars[4] = '`';
        terrainMap.put("Grass", new Terrain("Grass", new ASCIITexture(grassColors, new Color(0, 70, 0), grassChars)));
        
        initialized = true;
    }
    
    public static LocalArea getField(int width, int height) {
        if (!initialized) initialize();
        
        LocalArea ret = new LocalArea(width, height, terrainMap.get("Grass"));
        
        for (int i = 0; i < width*height*0.35; i++) {
           ret.setTerrain(random.nextInt(width), random.nextInt(height), terrainMap.get("Dirt"));
        }
        
        return ret;
    }
    
    
    
}
