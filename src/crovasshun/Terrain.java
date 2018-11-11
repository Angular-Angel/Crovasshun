package crovasshun;

import geomerative.RShape;
import processingdisplay.ASCIIShape;
import processingdisplay.Drawable;

public class Terrain implements Footprint, Drawable {
	public ASCIIShape shape;
	public TerrainType type;
    public RShape collisionShape;
	
	public Terrain(RShape shape, TerrainType type) {
		this.shape = new ASCIIShape(shape, type.appearance);
		this.collisionShape = this.shape.shape;
		shape.setStroke(false);
		this.type = type;
	}
    
	@Override
    public float getX() {
    	return collisionShape.getX();
    }

	@Override
    public float getY() {
    	return collisionShape.getY();
    }

	@Override
    public float getWidth() {
    	return collisionShape.getWidth();
    }

	@Override
    public float getHeight() {
    	return collisionShape.getHeight();
    }


	@Override
	public void draw(Game game) {
		shape.draw(game);
	}
	
	@Override
	public RShape getShape() {
		return shape.shape;
	}
}
