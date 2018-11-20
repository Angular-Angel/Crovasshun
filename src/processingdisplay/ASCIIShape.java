package processingdisplay;

import crovasshun.Dirtyable;
import geomerative.RPoint;
import geomerative.RShape;
import processing.core.PApplet;

public class ASCIIShape implements Dirtyable {
	public RShape shape;
	private boolean dirty;
	
	/*public ASCIIShape(float width, float height, GeneralASCIITexture texture) {
		spaceX = width % texture.font.getStandardWidth();
		spaceY = height % texture.font.getLineSpacing();
		shape = RG.getRect(0, 0, width, height);
	}*/
	
	public ASCIIShape(RShape shape, ASCIITexture texture) {
		this(shape, texture, 5);
	}
	
	
	public ASCIIShape(RShape shape, ASCIITexture texture, int border) {
		this.shape = shape;
    	texture.fill(shape);
	}
	
	/*private void fillShape(RShape shape, ASCIITexture texture, int border) {
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
            	
            	char c; //Choose a character.
            	if (texture.isRandom) 
            		c = texture.chars[randomGen.nextInt(texture.chars.length)];
            	else c = texture.chars[index++ % texture.chars.length];
            	
            	int color; //Choose a color.
            	if (texture.isRandom) 
            		color = texture.getColor(randomGen.nextInt(texture.colors.length));
            	else color = texture.colors[index++ % texture.colors.length];
            	
            	RShape character = texture.font.toShape(c, color); //Get the shape for the character.
            	character.translate(x + xIncrease, y + yIncrease + texture.font.getLineSpacing()); //Put it in the right place.
            	
            	RShape borderedCharacter = borderCharacter(character, border); //Expand character to account for borders.
            	
	            while (!fits(borderedCharacter) && xIncrease < width) { //See if it fits, and if there's still space to add it.
	            	character.translate(1, 0); //If not, move it, and adjust various variables appropriately.
	            	borderedCharacter.translate(1, 0);
	            	xIncrease++;
	            }
	            
	            if (xIncrease < width) { //If we haven't run out of room for our character,
	                shape.addChild(character); //Add it.
	                
	                //And adjust the positioning of the next characters appropriately.
	                xIncrease += texture.font.getStandardSpacing();
	                drewCharacter = true;
	            }
            }
            
            //If we drew characters last line, go down by a whole line, 
            //otherwise just go down by a single pixel and try again.
            if (drewCharacter) {
	            yIncrease += texture.font.getLineSpacing();
            } else yIncrease++;
        }
	}*/
	
	private RShape borderCharacter(RShape character, float border) {
		RPoint centroid  = character.getCentroid();
    	RShape borderedCharacter = new RShape(character); //Expand the shape to account for the borders.
    	borderedCharacter.scale((character.getWidth() + border*2) / character.getWidth(), 
    							(character.getHeight() + border*2) / character.getHeight(), 
    							centroid.x, centroid.y);
    	return borderedCharacter;
    	//return RG.getRect(character.getX() - border/2, character.getY() - border/2, character.getWidth() + border, character.getHeight() + border);
	}
	
	public void draw(PApplet context) {
		shape.draw(context);
	}

	@Override
	public boolean isDirty() {
		return dirty;
	}


	@Override
	public void dirty() {
		dirty = true;
	}
}
