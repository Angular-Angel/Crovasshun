package crovasshun;

import java.awt.Color;
import java.awt.Polygon;
import java.util.HashMap;
import java.util.Random;

import geomerative.RG;
import geomerative.RPoint;
import geomerative.RShape;
import processingdisplay.ASCIITexture;

public class LocalMapGenerator {
    
    private static final HashMap<String, TerrainType> terrainMap =  new HashMap<>();
    private static boolean initialized = false;
    private static Random random = new Random();
    private static Game game;
    
    public static TerrainType getTerrain(String terrain) {
        if (!initialized) throw new IllegalStateException("Class not initialized!");
        return terrainMap.get(terrain);
    }
    
    public static void addTerrain(TerrainType terrain) {
        terrainMap.put(terrain.name, terrain);
    }
    
    public static void initialize(Game game) {
    	LocalMapGenerator.game = game;
        int[] dirtColors = new int[3];
        dirtColors[0] = game.color(205,133,63);
        dirtColors[1] = game.color(244,164,96);
        dirtColors[2] = game.color(210,180,140);
        char[] dirtChars = new char[6];
        dirtChars[0] = '*';
        dirtChars[1] = '#';
        dirtChars[2] = '`';
        dirtChars[3] = '\'';
        dirtChars[4] = ',';
        dirtChars[5] = '.';
        addTerrain(new TerrainType("Dirt", new ASCIITexture("Dirt Texture", game.font, dirtColors, game.color(139,69,19), dirtChars)));
        
        int[] grassColors = new int[3];
        grassColors[0] = game.color(0, 255, 0);
        grassColors[1] = game.color(0, 130, 0);
        grassColors[2] = game.color(30, 180, 0);
        char[] grassChars = new char[5];
        grassChars[0] = ',';
        grassChars[1] = '\'';
        grassChars[2] = '"';
        grassChars[3] = '.';
        grassChars[4] = '`';
        addTerrain(new TerrainType("Grass", new ASCIITexture("Grass Texture", game.font, grassColors, game.color(0, 70, 0), grassChars)));
        
        int[] stoneColors = new int[3];
        stoneColors[0] = game.color(200);
        stoneColors[1] = game.color(144);
        stoneColors[2] = game.color(255);
        char[] stoneChars = new char[6];
        stoneChars[0] = '#';
        stoneChars[1] = '+';
        stoneChars[2] = '-';
        stoneChars[3] = '=';
        stoneChars[4] = '/';
        stoneChars[5] = '\\';
        addTerrain(new TerrainType("Stone", new ASCIITexture("Stone Texture", game.font, stoneColors, game.color(200), stoneChars)));
        
        addTerrain(new TerrainType("Polished Stone", new ASCIITexture("Polished Stone Texture", game.font, game.color(200), game.color(82), '+', false)));
        
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
        
        ret.addTerrain(new Terrain(RG.getRect(0, height/3, width, height/3), getTerrain("Polished Stone")));
        
        return ret;
    }
    
    public static LocalArea getObelisk(int width, int height) {
        LocalArea ret = new LocalArea(width, height, getTerrain("Grass"));
        for (int k = 0; k < 5; k++) {
            int i = (k*80), j = height/2 - (k*95);

            int hexHeight = Math.min(width, height)/4;                             // h = basic dimension: height (distance between two adj centresr aka size)
            int hexRadius = hexHeight/2;			// r = radius of inscribed circle
            int sideLength = (int) (hexHeight / 1.73205);	// s = (h/2)/cos(30)= (h/2) / (sqrt(3)/2) = h / sqrt(3)
            int triangleLength = (int) (hexRadius / 1.73205);	// t = (h/2) tan30 = (h/2) 1/sqrt(3) = h / (2 sqrt(3)) = r / sqrt(3)

            int[] cx, cy;

            cx = new int[] {0};	//this is for the whole hexagon to be below and to the right of this point

            cy = new int[] {hexRadius};
            
            RPoint[] rPoints = new RPoint[] {new RPoint(triangleLength, 0),
						            		 new RPoint(sideLength+triangleLength, 0),
						            		 new RPoint(sideLength+(2*triangleLength), hexRadius),
						            		 new RPoint(sideLength+triangleLength, (2*hexRadius)),
						            		 new RPoint(triangleLength, (2*hexRadius)),
						            		 new RPoint(0, hexRadius),
            								 };
            
            RShape polygon = RG.createShape(new RPoint[][] {rPoints});

            polygon.translate(i, j);

            char[] obeliskChars = new char[2];
            obeliskChars[0] = '-';
            obeliskChars[1] = '|';
            ASCIITexture asciiTexture = new ASCIITexture("Obelisk Texture", game.font, game.color(200, 40, 200), game.color(82), obeliskChars, false);

            MapObject mapObject = new MapObject(polygon, asciiTexture);

            ret.addMapObject(mapObject);
        }
        
        return ret;
    }
    
}
