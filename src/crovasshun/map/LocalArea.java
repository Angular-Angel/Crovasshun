package crovasshun.map;

import java.util.ArrayList;

import crovasshun.Body;
import geomerative.RG;
import geomerative.RShape;

public class LocalArea {
	
	public final float width, height;
	
	public ArrayList<Terrain> terrain = new ArrayList<>();
	public ArrayList<Body> bodies = new ArrayList<>();
	public ArrayList<MapObject> mapObjects = new ArrayList<>();
	
	public LocalArea(float width, float height) {
		this.width = width;
		this.height = height;
	}
	
	public LocalArea(float width, float height, TerrainType terrainType) {
		this(width, height);
		addTerrain(new Terrain(RG.getRect(0, 0, width, height), terrainType));
	}
	
	public void addTerrain(Terrain newTerrain) {
		
		if (contains(newTerrain)) {
			terrain.add(newTerrain);
		} else {
			throw new IllegalArgumentException("Terrain outside LocalArea!");
		}
	}
	
	public void addBody(Body body) {
		if (contains(body))
			bodies.add(body);
		else {
			throw new IllegalArgumentException("Body outside LocalArea!");
		}
	}
	
	public void addMapObject(MapObject mapObject) {
		if (contains(mapObject)) {
			mapObjects.add(mapObject);
		} else {
			throw new IllegalArgumentException("Map Object outside LocalArea: x: " + mapObject.getX() + 
					", y: " + mapObject.getY() +
					", width: " + mapObject.getWidth() + ", height: " + mapObject.getHeight());
		}
	}
	
	public boolean contains (Footprint footprint) {
		return contains(footprint.getX(), footprint.getY(), footprint.getWidth(), footprint.getHeight());
	}
	
	public boolean contains(float x, float y, float width, float height) {
		return (x >= 0 && y >= 0 && x + width <= this.width && y + height <= this.height);
	}
}
