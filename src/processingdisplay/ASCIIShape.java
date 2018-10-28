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
    	Random randomGen = new Random();
    	int border = 5;
    	
        int index = 0;
        int yIncrease = 0;
        final float width = shape.getWidth();
        final float height = shape.getHeight();
        
        final float x = shape.getX();
        final float y = shape.getY();

        while(yIncrease <= height) {
        	
        	int xIncrease = 0;
        	boolean drewCharacter = false;
            
            while (xIncrease < width) {
            	char c;
            	if (texture.isRandom) 
            		c = texture.chars[randomGen.nextInt(texture.chars.length)];
            	else c = texture.chars[index++ % texture.chars.length];
            	
            	RShape character = texture.font.toShape(c);
            	character.translate(x + xIncrease, y + yIncrease + texture.font.getLineSpacing());
            	
            	RPoint centroid  = character.getCentroid();
            	RShape borderedCharacter = new RShape(character); //Expand the shape to account for the borders.
            	borderedCharacter.scale((character.getWidth() + border*2) / character.getWidth(), (character.getHeight() + border*2) / character.getHeight(), centroid.x, centroid.y);
                
	            while (!contains(shape, borderedCharacter) && xIncrease < width) {
	            	character.translate(1, 0);
	            	borderedCharacter.translate(1, 0);
	            	xIncrease++;
	            }
	            index = 0;
	            if (xIncrease < width) {
	            	character.setStroke(false);
	            	
	            	if (texture.isRandom && texture.colors.length > 1) 
	            		character.setFill(texture.getColor(randomGen.nextInt(texture.colors.length)));
	            	else character.setFill(texture.colors[index++ % texture.colors.length]);
	            	
	                shape.addChild(character);
	                
	                xIncrease += character.getWidth() + texture.font.size/5;
	                drewCharacter = true;
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
