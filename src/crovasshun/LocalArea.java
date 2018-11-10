package crovasshun;

import java.util.ArrayList;

public class LocalArea {
	
	public final float width, height;
	
	public ArrayList<Terrain> terrain = new ArrayList<>();
	
	public LocalArea(float width, float height) {
		this.width = width;
		this.height = height;
	}
	
	public void addTerrain(Terrain newTerrain) {
		
		float x = newTerrain.shape.shape.getX();
		float y = newTerrain.shape.shape.getY();
		float terrainWidth = newTerrain.shape.shape.getWidth();
		float terrainHeight = newTerrain.shape.shape.getHeight();
		
		if (x >= 0 && y >= 0 && x + terrainWidth <= width && y + terrainHeight <= height)
			terrain.add(newTerrain);
	}
}
