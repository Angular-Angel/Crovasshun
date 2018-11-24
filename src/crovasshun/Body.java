package crovasshun;

import crovasshun.map.Footprint;
import crovasshun.ui.ASCIISprite;
import crovasshun.ui.Drawable;
import geomerative.RPoint;
import geomerative.RShape;

public class Body implements Footprint, Drawable, Actor {
    public final String name;
    public ASCIISprite mapSprite, displaySprite;
    public RShape collisionShape;
    private BodyAction currentAction = null;
    

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
	public RPoint getCenter() {
		return collisionShape.getCenter();
	}
	
	public void moveBy(RPoint point) {
		collisionShape.translate(point);
	}
	
	public void moveBy(float x, float y) {
		collisionShape.translate(x, y);
	}

	@Override
	public void draw(Game game) {
		mapSprite.draw(game);
	}
	
	@Override
	public RShape getShape() {
		return collisionShape;
	}

	@Override
	public void update(long deltaTime) {
		if (currentAction != null) currentAction.update(deltaTime);
	}

	@Override
	public boolean ready(long deltaTime) {
		// TODO Auto-generated method stub
		return false;
	}
}
