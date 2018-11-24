package crovasshun.ui;

import java.util.ArrayList;

import geomerative.RPoint;
import geomerative.RShape;
import processing.core.PApplet;

public class ASCIISprite {

	public final RShape shape;
	
	public ASCIISprite(RShape background, int color, ASCIIFont font, String string) {
		shape = new RShape(background);
		
		ArrayList<ArrayList<ASCIIChar>> chars = new ArrayList<>();
        chars.add(new ArrayList<>());
		
		for (int i = 0, k = 0; i < string.length(); i++) {
            if (string.charAt(i) == '\n') {
                k++;
                chars.add(new ArrayList<>());
            } else {
                chars.get(k).add(new ASCIIChar(color, string.charAt(i)));
            }
        }
		
		RPoint position = new RPoint(shape.getTopLeft());
		
		float baseX = position.x;
		position.y += (shape.getHeight() - chars.size() * font.getLineSpacing()) / 2;
		
		float width = 0;
		ArrayList<RShape> charShapes = new ArrayList<>();
		
        for (ArrayList<ASCIIChar> list : chars) {
            position.y += font.getLineSpacing()/2;
            position.x = baseX;
            float lineWidth = 0;
            for (ASCIIChar character : list) {
            	RShape charShape = font.toShape(character.character, color);
                position.x += font.getStandardWidth()/2;
            	charShape.translate(position);
            	charShapes.add(charShape);
                position.x += font.getStandardWidth()/2 + font.size/5;
            	lineWidth += font.getStandardWidth() + font.size/5;
            }
            position.y += font.getLineSpacing()/2;
            if (lineWidth > width) width = lineWidth;
        }
        
        //System.out.println(width);
        for (RShape charShape : charShapes) {
        	charShape.translate((shape.getWidth() - width) / 2, 0);
        	shape.addChild(charShape);
        }
	}
	
	public void draw(PApplet context) {
		shape.draw(context);
	}
}
