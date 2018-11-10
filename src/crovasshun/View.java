package crovasshun;

import geomerative.RPoint;
import processing.core.PApplet;

public class View {
	public float x = 0, y = 0, scale = 1;
	
	public void apply(PApplet applet) {
		applet.translate(x, y);
		applet.scale(scale);
	}
	
	public RPoint adjustPoint(RPoint point) {
		point.translate(-x, -y);
		point.scale(1/scale);
		return point;
	}
	
	public RPoint adjustPoint(float x, float y) {
		return adjustPoint(new RPoint(x, y));
	}
}
