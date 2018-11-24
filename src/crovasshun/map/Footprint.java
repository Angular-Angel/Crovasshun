package crovasshun.map;

import geomerative.RPoint;
import geomerative.RShape;

public interface Footprint {
	public float getX();
    
    public float getY();
    
    public float getWidth();
    
    public float getHeight();
    
    public RPoint getCenter();
    
    public RShape getShape();
}
