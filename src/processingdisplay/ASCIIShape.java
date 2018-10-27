package processingdisplay;

import java.util.Random;

import geomerative.RPoint;
import geomerative.RShape;
import processing.core.PApplet;

public class ASCIIShape {
	public final RShape shape;
	
	public ASCIIShape(RShape shape, ASCIITexture texture) {
		this.shape = shape;
    	shape.setFill(texture.background);
    	RPoint point = shape.getBottomLeft();
    	Random randomGen = new Random();
    	int border = 5;
    	
    	RShape shrink = new RShape(shape);
    	//shrink.scale(border)
    	shrink.scale((shape.getWidth() - border) / shape.getWidth(), (shape.getHeight() - border) / shape.getHeight());
        
        int index = 0;
        int yIncrease = 0;
        final float width = shrink.getWidth();
        final float height = shrink.getHeight();
        
        final float x = shrink.getX();
        final float y = shrink.getY();

        System.out.println("Shape: " + shape.getX() + ", " + shape.getY() + ", " + shape.getWidth() + ", " + shape.getHeight());
        System.out.println("Shrink: " + x + ", " + y + ", " + width + ", " + height);
        while(yIncrease <= height) {
        	
        	int xIncrease = 0;
        	boolean drewCharacter = false;
            
            while (xIncrease + border * 2 < width) {
            	char c;
            	if (texture.isRandom) 
            		c = texture.chars[randomGen.nextInt(texture.chars.length)];
            	else c = texture.chars[index++ % texture.chars.length];
            	RShape character = texture.font.toShape(c);
            	character.translate(x + xIncrease + border, y + yIncrease + border + texture.font.getLineSpacing());
	            while (!contains(shrink, character) && xIncrease + border * 2 < width) {
	            	character.translate(1, 0);
	            	xIncrease++;
	            }
	            index = 0;
	            if (xIncrease + border * 2 < width) {
	            	character.setStroke(false);
	            	if (texture.isRandom && texture.colors.length > 1) 
	            		character.setFill(texture.getColor(randomGen.nextInt(texture.colors.length)));
	            	else character.setFill(texture.colors[index++ % texture.colors.length]);
	                shape.addChild(character);
	                System.out.println("Adding character: " + c);
	                drewCharacter = true;
	                xIncrease += character.getWidth() + texture.font.size/5;
	            } else {
	                System.out.println("Failing to add character: " + c);
	            }
            }
            if (drewCharacter) {
	            yIncrease += texture.font.getLineSpacing();
            } else yIncrease++;
        }
	}
	
	public boolean contains(RShape shape1, RShape shape2) {
		RPoint[] pts = shape2.getPoints();
		for (int i = 0; i < pts.length; i++) {
			if (!shape1.contains(pts[i])) { 
	        	return false;
	    	}
	    }
	    return true;
	}
	
	public void draw(PApplet context) {
		shape.draw(context);
	}
}
