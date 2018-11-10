package crovasshun;

import processing.core.PApplet;

public class View {
	public float x = 0, y = 0, scale = 1;
	
	public void apply(PApplet applet) {
		applet.translate(x, y);
		applet.scale(scale);
	}
}
