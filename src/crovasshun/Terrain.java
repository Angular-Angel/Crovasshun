package crovasshun;

import geomerative.RShape;
import processingdisplay.ASCIIShape;

public class Terrain {
	public ASCIIShape shape;
	public TerrainType type;
	
	public Terrain(RShape shape, TerrainType type) {
		this.shape = new ASCIIShape(shape, type.appearance);
		this.type = type;
	}
}
