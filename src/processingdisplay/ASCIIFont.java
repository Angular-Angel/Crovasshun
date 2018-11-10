package processingdisplay;

import geomerative.RFont;
import geomerative.RShape;

public class ASCIIFont extends RFont{
	
	private float standardWidth;

	public ASCIIFont(String arg0, int size) throws RuntimeException {
		super(arg0);
		super.setSize(size);
		standardWidth = super.toShape('#').getWidth();
		// TODO Auto-generated constructor stub
	}
	
	public RShape toShape(char character, int color) {
		RShape shape;
		if (character == ' ') { 
			shape = super.toShape('#');
			shape.setStroke(false);
			shape.setFill(false);
		}
		else {
			shape = super.toShape(character);
			shape.setStroke(false);
			shape.setFill(color);
		}
		return shape;
	}
	
	public void setSize(int size) {
		super.setSize(size);
		
		standardWidth = super.toShape('#').getWidth();
	}
	
	public float getStandardWidth() {
		return standardWidth;
	}

}
