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
    
    private static final HashMap<String, Terrain> terrainMap =  new HashMap<>();
    private static boolean initialized = false;
    private static Random random = new Random();
    
    public static Terrain getTerrain(String terrain) {
        if (!initialized) initialize();
        return terrainMap.get(terrain);
    }
    
    public static void addTerrain(Terrain terrain) {
        terrainMap.put(terrain.name, terrain);
    }
    
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
        addTerrain(new Terrain("Dirt", new ASCIITexture(dirtColors, new Color(139,69,19), dirtChars)));
        
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
        addTerrain(new Terrain("Grass", new ASCIITexture(grassColors, new Color(0, 70, 0), grassChars)));
        
        Color[] stoneColors = new Color[3];
        stoneColors[0] = Color.LIGHT_GRAY;
        stoneColors[1] = Color.GRAY;
        stoneColors[2] = Color.WHITE;
        char[] stoneChars = new char[6];
        stoneChars[0] = '#';
        stoneChars[1] = '+';
        stoneChars[2] = '-';
        stoneChars[3] = '=';
        stoneChars[4] = '/';
        stoneChars[5] = '\\';
        addTerrain(new Terrain("Stone", new ASCIITexture(stoneColors, Color.DARK_GRAY, stoneChars)));
        
        addTerrain(new Terrain("Polished Stone", new ASCIITexture(Color.LIGHT_GRAY, Color.DARK_GRAY, '+', false)));
        
        char[] obeliskChars = new char[4];
        obeliskChars[0] = '-';
        obeliskChars[1] = '|';
        obeliskChars[2] = '-';
        obeliskChars[3] = '|';
        addTerrain(new Terrain("Obelisk", new ASCIITexture(Color.MAGENTA, Color.DARK_GRAY, obeliskChars, false)));
        
        initialized = true;
    }
    
    public static LocalArea getMixedTerrain(int width, int height, Terrain primary, Terrain secondary, float percentage) {
        if (primary == null || secondary == null) throw new IllegalArgumentException("Null terrain!");
        
        LocalArea ret = new LocalArea(width, height, primary);
        
        for (int i = 0; i < width*height*percentage; i++) {
           ret.setTerrain(random.nextInt(width), random.nextInt(height), secondary);
        }
        
        return ret;
    }
    
    public static LocalArea getField(int width, int height) {
        return getMixedTerrain(width, height, getTerrain("Grass"), getTerrain("Dirt"), 0.25f);
    }
    
    public static LocalArea getPassage(int width, int height) {
        LocalArea ret = new LocalArea(width, height, getTerrain("Stone"));
        
        for (int i = 0; i < width; i++) {
            ret.setTerrain(i, height/2, getTerrain("Polished Stone"));
        }
        
        return ret;
    }
    
    public static LocalArea getObelisk(int width, int height) {
        LocalArea ret = new LocalArea(width, height, getTerrain("Grass"));
        
        int i = width/2, j = height/2;
        
        ret.setTerrain(i - 1, j, getTerrain("Obelisk"));
        ret.setTerrain(i - 1, j - 1, getTerrain("Obelisk"));
        ret.setTerrain(i, j - 1, getTerrain("Obelisk"));
        ret.setTerrain(i, j, getTerrain("Obelisk"));
        ret.setTerrain(i, j + 1, getTerrain("Obelisk"));
        ret.setTerrain(i + 1, j, getTerrain("Obelisk"));
        ret.setTerrain(i + 1, j - 1, getTerrain("Obelisk"));
        
        ret.setTerrain(6, 6, null);
        ret.setTerrain(5, 6, null);
        ret.setTerrain(5, 5, null);
        
        return ret;
    }
    
}