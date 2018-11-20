package crovasshun;

import java.util.HashMap;

import geomerative.RG;
import geomerative.RPoint;
import geomerative.RShape;
import processingdisplay.ASCIITexture;
import processingdisplay.RandomASCIITexture;
import processingdisplay.RegularASCIITexture;

public class LocalMapGenerator {
    
    private static final HashMap<String, TerrainType> terrainTypes =  new HashMap<>();
    private static boolean initialized = false;
    private static Game game;
    
    public static TerrainType getTerrain(String terrain) {
        if (!initialized) throw new IllegalStateException("Class not initialized!");
        return terrainTypes.get(terrain);
    }
    
    public static void addTerrain(TerrainType terrain) {
        terrainTypes.put(terrain.name, terrain);
    }
    
    public static void initialize(Game game) {
    	LocalMapGenerator.game = game;
    	
        addTerrain(new TerrainType("Dirt", new RandomASCIITexture("Dirt Texture", game, 
        		new char[] {'*', '#', '`', '\'', ',', '.'},
        		new int[] {game.color(205,133,63), game.color(244,164,96), game.color(210,180,140)}, 
        		game.color(139,69,19))));
        
        addTerrain(new TerrainType("Grass", new RandomASCIITexture("Grass Texture", game, 
    			new char[] {',', '.', '`', '\'', '"'}, 
    			new int[] {game.color(0, 255, 0), game.color(0, 130, 0), game.color(30, 180, 0)}, 
    			game.color(0,100,0))));
        
        addTerrain(new TerrainType("Stone", new RandomASCIITexture("Stone Texture", game, 
    			new char[] {'#', '+', '-', '=', '/', '\\'}, 
    			new int[] {game.color(200), game.color(144), game.color(255)}, 
    			game.color(100))));
        
        addTerrain(new TerrainType("Polished Stone", new RegularASCIITexture(" PolishedStone Texture", game, 
    			new char[] {'+'}, 
    			new int[] {game.color(200)}, 
    			game.color(82))));
        
        initialized = true;
    }
    
    /*public static LocalArea getMixedTerrain(int width, int height, TerrainType primary, TerrainType secondary, float percentage) {
        if (primary == null || secondary == null) throw new IllegalArgumentException("Null terrain!");
        
        LocalArea ret = new LocalArea(width, height, primary);
        
        for (int i = 0; i < width*height*percentage; i++) {
           //ret.setTerrain(random.nextInt(width), random.nextInt(height), secondary);
        }
        
        return ret;
    }*/
    
    public static LocalArea getField(int width, int height) {
        return new LocalArea(width, height, getTerrain("Grass"));
    }
    
    public static LocalArea getPassage(int width, int height) {
        LocalArea ret = new LocalArea(width, height, getTerrain("Stone"));
        
        ret.addTerrain(new Terrain(RG.getRect(0, height/3, width, height/3), getTerrain("Polished Stone")));
        
        return ret;
    }
    
    public static LocalArea getStoneTriangles(float width, float height) {
    	LocalArea ret = new LocalArea(width, height, getTerrain("Grass"));
    	
    	ASCIITexture asciiTexture = new RegularASCIITexture("Triangle Stone Texture", game, 
    			new char[] {'+'}, 
    			new int[] {game.color(200)}, 
    			game.color(100)) {
    		
    		@Override
    		public void fill(RShape shape) {
    			basicColor(shape);
    			//fillRightTriangle(shape, 5);
    		}
    	};
    	
    	RPoint[] rPoints = new RPoint[] {new RPoint(width/10, height/10),
    									 new RPoint(width*3/10, height/10),
    									 new RPoint(width/10, height*3/10),
    									 new RPoint(width/10, height/10)};
    	
    	RShape triangle = RG.createShape(new RPoint[][] {rPoints});
    	
    	MapObject mapObject = new MapObject(triangle, asciiTexture);
    	
    	ret.addMapObject(mapObject);
    	
    	rPoints = new RPoint[] {new RPoint(width*7/10, height/10), 
				  new RPoint(width*9/10, height/10),
				  new RPoint(width*9/10, height*3/10),
				  new RPoint(width*7/10, height/10)};
                                                  
		triangle = RG.createShape(new RPoint[][] {rPoints});       
		                                                  
		mapObject = new MapObject(triangle, asciiTexture);
		
		ret.addMapObject(mapObject);
		
		rPoints = new RPoint[] {new RPoint(width*7/10, height*9/10), 
				  new RPoint(width*9/10, height*7/10),
				  new RPoint(width*9/10, height*9/10),
				  new RPoint(width*7/10, height*9/10)};
                                                
		triangle = RG.createShape(new RPoint[][] {rPoints});       
		                                                  
		mapObject = new MapObject(triangle, asciiTexture);
		
		//ret.addMapObject(mapObject);
		
		rPoints = new RPoint[] {new RPoint(width/10, height*9/10), 
				  new RPoint(width*3/10, height*9/10),
				  new RPoint(width/10, height*7/10),
				  new RPoint(width/10, height*9/10)};
                                                
		triangle = RG.createShape(new RPoint[][] {rPoints});       
		                                                  
		mapObject = new MapObject(triangle, asciiTexture);
		
		//ret.addMapObject(mapObject);
    	
    	return ret;
    }
    
    public static LocalArea getObelisk(int width, int height) {
        LocalArea ret = new LocalArea(width, height, getTerrain("Grass"));
        for (int k = 0; k < 5; k++) {
            int i = 400 - (k*80), j = height/2 - (k*95);

            int hexHeight = Math.min(width, height)/4;        // h = basic dimension: height (distance between two adj centresr aka size)
            int hexRadius = hexHeight/2;			          // r = radius of inscribed circle
            int sideLength = (int) (hexHeight / 1.73205);     // s = (h/2)/cos(30)= (h/2) / (sqrt(3)/2) = h / sqrt(3)
            int triangleLength = (int) (hexRadius / 1.73205); // t = (h/2) tan30 = (h/2) 1/sqrt(3) = h / (2 sqrt(3)) = r / sqrt(3)
            
            RPoint[] rPoints = new RPoint[] {new RPoint(triangleLength, 0),
						            		 new RPoint(sideLength+triangleLength, 0),
						            		 new RPoint(sideLength+(2*triangleLength), hexRadius),
						            		 new RPoint(sideLength+triangleLength, (2*hexRadius)),
						            		 new RPoint(triangleLength, (2*hexRadius)),
						            		 new RPoint(0, hexRadius)};
            
            RShape polygon = RG.createShape(new RPoint[][] {rPoints});

            polygon.translate(i, j);
            
            ASCIITexture asciiTexture = new RegularASCIITexture("Obelisk Texture", game, new char[] {'-', '|'}, new int[] {game.color(82)}, game.color(200, 40, 200));

            MapObject mapObject = new MapObject(polygon, asciiTexture);

            ret.addMapObject(mapObject);
        }
        
        return ret;
    }
    
}
