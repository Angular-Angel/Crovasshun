package crovasshun.map;

import crovasshun.Game;
import crovasshun.ui.ASCIIShape;
import crovasshun.ui.Drawable;
import geomerative.RPoint;
import geomerative.RShape;

public class Terrain implements Footprint, Drawable {
	public ASCIIShape shape;
	public TerrainType type;
    public RShape collisionShape;
	
	public Terrain(RShape shape, TerrainType type) {
		this.shape = new ASCIIShape(shape, type.appearance);
		this.collisionShape = this.shape.shape;
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
	
	@Override
	public RPoint getCenter() {
		return collisionShape.getCenter();
	}
	
	public void subtract(RShape shape) {
		this.shape.shape = this.shape.shape.diff(shape);
		collisionShape = this.shape.shape;
	}
}
