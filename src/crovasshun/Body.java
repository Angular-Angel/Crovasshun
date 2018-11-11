package crovasshun;

import geomerative.RShape;
import processingdisplay.ASCIISprite;
import processingdisplay.Drawable;

public class Body implements Footprint, Drawable {
    public final String name;
    public ASCIISprite mapSprite, displaySprite;
    public RShape collisionShape;
    

    public Body(String name, ASCIISprite mapSprite, ASCIISprite displaySprite) {
    	this(name, mapSprite, displaySprite, 0, 0);
    }
    
    public Body(String name, ASCIISprite mapSprite, ASCIISprite displaySprite, float x, float y) {
    	mapSprite.shape.translate(x, y);
    	this.name = name;
    	this.mapSprite = mapSprite;
    	this.displaySprite = displaySprite;
    	this.collisionShape = mapSprite.shape;
    }
    
    public float getX() {
    	return collisionShape.getX();
    }
    
    public float getY() {
    	return collisionShape.getY();
    }
    
    public float getWidth() {
    	return collisionShape.getWidth();
    }
    
    public float getHeight() {
    	return collisionShape.getHeight();
    }

	@Override
	public void draw(Game game) {
		// TODO Auto-generated method stub
		mapSprite.draw(game);
	}
	
	@Override
	public RShape getShape() {
		return collisionShape;
	}
}
