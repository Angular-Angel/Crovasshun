/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crovasshun;

import display.ASCIITexture;
import java.awt.Color;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.geom.Area;
import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.Random;

/**
 *
 * @author angle
 */
public class LocalMapGenerator {
    
    private static final HashMap<String, TerrainType> terrainMap =  new HashMap<>();
    private static boolean initialized = false;
    private static Random random = new Random();
    
    public static TerrainType getTerrain(String terrain) {
        if (!initialized) initialize();
        return terrainMap.get(terrain);
    }
    
    public static void addTerrain(TerrainType terrain) {
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
        addTerrain(new TerrainType("Dirt", new ASCIITexture(dirtColors, new Color(139,69,19), dirtChars)));
        
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
        addTerrain(new TerrainType("Grass", new ASCIITexture(grassColors, new Color(0, 70, 0), grassChars)));
        
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
        addTerrain(new TerrainType("Stone", new ASCIITexture(stoneColors, Color.DARK_GRAY, stoneChars)));
        
        addTerrain(new TerrainType("Polished Stone", new ASCIITexture(Color.LIGHT_GRAY, Color.DARK_GRAY, '+', false)));
        
        initialized = true;
    }
    
    public static LocalArea getMixedTerrain(int width, int height, TerrainType primary, TerrainType secondary, float percentage) {
        if (primary == null || secondary == null) throw new IllegalArgumentException("Null terrain!");
        
        LocalArea ret = new LocalArea(width, height, primary);
        
        for (int i = 0; i < width*height*percentage; i++) {
           //ret.setTerrain(random.nextInt(width), random.nextInt(height), secondary);
        }
        
        return ret;
    }
    
    public static LocalArea getField(int width, int height) {
        return getMixedTerrain(width, height, getTerrain("Grass"), getTerrain("Dirt"), 0.25f);
    }
    
    public static LocalArea getPassage(int width, int height) {
        LocalArea ret = new LocalArea(width, height, getTerrain("Stone"));
        
        for (int i = 0; i < width; i++) {
            //ret.setTerrainType(i, height/2, getTerrain("Polished Stone"));
        }
        
        return ret;
    }
    
    public static LocalArea getObelisk(int width, int height) {
        LocalArea ret = new LocalArea(width, height, getTerrain("Grass"));
        
        int i = width - 200, j = height/2 - 2;
        
        int hexHeight = 100;                             // h = basic dimension: height (distance between two adj centresr aka size)
        int hexRadius = hexHeight/2;			// r = radius of inscribed circle
        int sideLength = (int) (hexHeight / 1.73205);	// s = (h/2)/cos(30)= (h/2) / (sqrt(3)/2) = h / sqrt(3)
        int triangleLength = (int) (hexRadius / 1.73205);	// t = (h/2) tan30 = (h/2) 1/sqrt(3) = h / (2 sqrt(3)) = r / sqrt(3)

        int[] cx, cy;

        cx = new int[] {triangleLength, sideLength+triangleLength, sideLength+(2*triangleLength), sideLength+triangleLength, triangleLength, 0};	//this is for the whole hexagon to be below and to the right of this point

        cy = new int[] {0,0,hexRadius,(2*hexRadius),(2*hexRadius),hexRadius};
        Polygon polygon = new Polygon(cx,cy,6);
        
        char[] obeliskChars = new char[2];
        obeliskChars[0] = '-';
        obeliskChars[1] = '|';
        ASCIITexture asciiTexture = new ASCIITexture(Color.MAGENTA, Color.DARK_GRAY, obeliskChars, false);
        
        LargeObject largeObject = new LargeObject("Obelisk", polygon, asciiTexture, new Point(0, 0));
        
        ret.terrain.get(0).subtract(largeObject);
        
        ret.objects.add(largeObject);
        
        return ret;
    }
    
}
