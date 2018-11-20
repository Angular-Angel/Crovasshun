package crovasshun;

import geomerative.RPoint;

public class MoveAction extends BodyAction {
	
	public final RPoint target;

	public MoveAction(Body body, RPoint target) {
		super(body);
		this.target = target;
	}

	@Override
	public void update(long dt) {
        RPoint start = body.getCenter();
        
        float x = target.x - start.x;
        float y = target.y - start.y;
        
        double normal = Math.sqrt(x*x+y*y);
        
        x *= dt;
        x/= normal;
        
        y *= 2*dt;
        y /= normal;
        
        RPoint destination = new RPoint(start.x + x, start.y + y);
        
        if(x > 0 && destination.x > target.x) {
            destination.x = target.x;
            x = target.x - start.x;
        }
        
        if(x < 0 && destination.x < target.x) {
            destination.x = target.x;
            x = target.x - start.x;
        }
        
        if(y > 0 && destination.y > target.y) {
            destination.y = target.y;
            y = target.y - start.y;
        }
        
        if(y < 0 && destination.y < target.y) {
            destination.y = target.y;
            y = target.y - start.y;
        }
        
        body.moveBy(x, y);
	}

}
