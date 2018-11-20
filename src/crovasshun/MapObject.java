package crovasshun;

import geomerative.RPoint;
import geomerative.RShape;
import processingdisplay.ASCIIShape;
import processingdisplay.ASCIITexture;
import processingdisplay.Drawable;

public class MapObject implements Footprint, Drawable {

	public ASCIIShape shape;
    public RShape collisionShape;
    
    public MapObject (RShape shape, ASCIITexture texture) {
    	this.shape = new ASCIIShape(shape, texture);
		this.collisionShape = shape;
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
}
