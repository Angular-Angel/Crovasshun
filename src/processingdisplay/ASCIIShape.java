package processingdisplay;

import java.util.ArrayList;
import java.util.Random;

import geomerative.RPoint;
import geomerative.RShape;
import processing.core.PApplet;

public class ASCIIShape {
	public final RShape shape;
	//public final ArrayList<RShape> characters = new ArrayList<>();
	
	public ASCIIShape(RShape shape, ASCIITexture texture) {
		this(shape, texture, 5);
	}
	
	public ASCIIShape(RShape shape, ASCIITexture texture, int border) {
		this.shape = shape;
    	shape.setFill(texture.background);
    	Random randomGen = new Random();
    	
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
            	int color;
            	if (texture.isRandom) 
            		color = texture.getColor(randomGen.nextInt(texture.colors.length));
            	else color = texture.colors[index++ % texture.colors.length];
            	RShape character = texture.font.toShape(c, color);
            	character.translate(x + xIncrease, y + yIncrease + texture.font.getLineSpacing());
            	
            	RPoint centroid  = character.getCentroid();
            	RShape borderedCharacter = new RShape(character); //Expand the shape to account for the borders.
            	borderedCharacter.scale((character.getWidth() + border*2) / character.getWidth(), 
            							(character.getHeight() + border*2) / character.getHeight(), 
            							centroid.x, centroid.y);
            	
	            while (!fits(borderedCharacter) && xIncrease < width) {
	            	character.translate(1, 0);
	            	borderedCharacter.translate(1, 0);
	            	xIncrease++;
	            }
	            
	            if (xIncrease < width) {	            	
	                shape.addChild(character);
	                //characters.add(character);
	                
	                xIncrease += texture.font.getStandardWidth() + texture.font.size/5;
	                drewCharacter = true;
	            }
            }
            if (drewCharacter) {
	            yIncrease += texture.font.getLineSpacing();
            } else yIncrease++;
        }
	}
	
	public boolean fits(RShape shape2) {
		RPoint[] pts = shape2.getPoints();
		for (int i = 0; i < pts.length; i++) {
			if (!shape.contains(pts[i])) { 
	        	return false;
	    	}
	    }
		/*for (RShape character : characters) {
			if (character.intersects(shape2)) { 
	        	return false;
	    	}
		}*/
	    return true;
	}
	
	public void draw(PApplet context) {
		shape.draw(context);
	}
}
