package processingdisplay;

import crovasshun.Game;
import geomerative.RPath;
import geomerative.RPoint;
import geomerative.RShape;
import geomerative.RStyle;

public abstract class ASCIITexture {
	
	public final String name;
	public final ASCIIFont font;
	public final RStyle style;
	
	public abstract char getNextChar();
	public abstract int getNextColor();
	public abstract void fill(RShape shape);

	public ASCIITexture(String name, Game game, int backgroundColor) {
		this.name = name;
		this.font = game.font;
		style = new RStyle();
		style.setFill(backgroundColor);
		style.setStroke(false);
	}
	
	public ASCIITexture(String name, Game game, int backgroundColor, int outlineColor) {
		this.name = name;
		this.font = game.font;
		style = new RStyle();
		style.setFill(backgroundColor);
		style.setStroke(outlineColor);
	}
	
	public void basicColor(RShape shape) {
		shape.setStyle(style);
	}
	
	public void fillRect(RShape shape, float minBorder) {
		Layout layout = layoutRect(shape, minBorder);
		
		fillRect(shape, layout);
	}
	
	public Layout layoutRect(RShape rect, float minBorder) {
		int rows = (int) ((rect.getHeight() - minBorder*2) / font.getLineSpacing());
		int columns = (int) ((rect.getWidth() - minBorder*2) / font.getStandardSpacing());
		return new Layout(rows, columns, 0, (rect.getWidth() % columns) / 2, (rect.getHeight() % rows) / 2, false);
	}
	
	public void fillRect(RShape shape, Layout layout) {
		for (int i = 0; i < layout.rows; i++) {
			float y = shape.getY() + layout.topBorder + i * font.getLineSpacing();
			float x = shape.getX() + layout.leftBorder;
			fillRow(shape, x, y, layout.columns);
		}
	}
	
	public void fillRightTriangle(RShape shape, float minBorder) {
		Layout layout = layoutRightTriangle(shape, minBorder);
		
		fillRightTriangle(shape, layout);
	}
	
	public Layout layoutRightTriangle(RShape rightTriangle, float minBorder) {
		
		float slope = 0;
		boolean backwards = false;
		
		//Go through the lines...
		for (RPath path : rightTriangle.paths) {
			RPoint [] points = path.getHandles(); //Check the points on each line...
			
			for (int i = 1; i < points.length; i++) {
				RPoint start = points[i], end = points[i-1];
				
				float dx = end.x - start.x;
				float dy = end.y - start.y;
				
				if (dx != 0 && dy != 0) { //Until we find a slope...
					slope = dx/dy;
					backwards = dx < 0; //Then return a layout.
				}
			}
		}
		
		//Calculate rows and columns.
		int rows = (int) (((rightTriangle.getHeight() - minBorder*2) / font.getLineSpacing()));
		int columns = (int) ((rightTriangle.getWidth() - minBorder*2) / font.getStandardSpacing());
		float borderX = (rightTriangle.getWidth() - columns * font.getStandardSpacing()) / 2;
		float borderY = (rightTriangle.getHeight() - rows * font.getLineSpacing()) / 2;
		
		float absSlope = Math.abs(slope);
		
		if (borderX < minBorder) {
			System.out.println("Border X: " + borderX + ", Min Border: " + minBorder);
			borderX = minBorder;
		}
		
		if (borderY < minBorder) {
			System.out.println("Border Y: " + borderY + ", Min Border: " + minBorder);
			borderY = minBorder;
		}
		
		if (slope != 0) {
			return new Layout(rows, columns, slope,
					borderX, borderY, backwards); //Then return a layout.
		} else throw new IllegalArgumentException("Not a right triangle!"); //Or throw an exception if there are no slopes here.
	}
	
	public void fillRightTriangle(RShape shape, Layout layout) {
		if (!layout.backwards) {
			if (layout.slope > 0) fill3rdQuadrantRightTriangle(shape, layout);
			else fill2ndQuadrantRightTriangle(shape, layout);
		} else {
			if (layout.slope > 0) fill1stQuadrantRightTriangle(shape, layout);
			else fill4thQuadrantRightTriangle(shape, layout);
		}
	}
	
	public void fill1stQuadrantRightTriangle(RShape shape, Layout layout) {
		System.out.println("1st Quadrant!");
		for (int i = 0; i < layout.rows; i++) {
			float y = shape.getY() + shape.getHeight() - (layout.bottomBorder + i * font.getLineSpacing());
			float x = shape.getX() + layout.leftBorder;
			int rowLength = (int) (layout.columns + Math.floor(layout.slope * i));
			fillRow(shape, x, y, rowLength);
		}
	}
	
	public void fill2ndQuadrantRightTriangle(RShape shape, Layout layout) {
		System.out.println("2nd Quadrant!");
		for (int i = 0; i < layout.rows; i++) {
			float y = shape.getY() + i * font.getLineSpacing() + layout.topBorder;
			float x = shape.getX() + layout.leftBorder;
			int rowLength = (int) (layout.columns + (layout.slope * (i + 1)));
			fillRow(shape, x, y, rowLength);
		}
	}
	
	public void fill3rdQuadrantRightTriangle(RShape shape, Layout layout) {
		System.out.println("3rd Quadrant!");
		for (int i = 0; i < layout.rows; i++) {
			int slopeDecrease = (int) Math.floor(layout.slope * i);
					
			float y = shape.getY() + layout.topBorder + i * font.getLineSpacing();
			float x = shape.getX() + layout.leftBorder + slopeDecrease * font.getStandardSpacing();
			
			int rowLength = layout.columns + slopeDecrease;
			fillRow(shape, x, y, rowLength);
		}
	}
	
	public void fill4thQuadrantRightTriangle(RShape shape, Layout layout) {
		System.out.println("4th Quadrant!");
		for (int i = 0; i < layout.rows; i++) {
			int slopeDecrease = (int) Math.floor(layout.slope * i);
					
			float y = shape.getY() + shape.getHeight() - (layout.bottomBorder + i * font.getLineSpacing());
			float x = shape.getX() + layout.leftBorder + slopeDecrease * font.getStandardSpacing();
			
			int rowLength = layout.columns - slopeDecrease;
			fillRow(shape, x, y, rowLength);
		}
	}
	
	public void fillRow(RShape shape, float x, float y, int length) {
		for (int j = 0; j < length; j++) {
			RShape character = font.toShape(getNextChar(), getNextColor()); //Get the shape for the character.
        	character.translate(x, y); //Put it in the right place.
        	shape.addChild(character);
			x += font.getStandardSpacing();
		}
	}
	
	public void fillColumn(RShape shape, float x, float y, int length) {
		for (int j = 0; j < length; j++) {
			RShape character = font.toShape(getNextChar(), getNextColor()); //Get the shape for the character.
        	character.translate(x, y); //Put it in the right place.
        	shape.addChild(character);
			y += font.getLineSpacing();
		}
	}
	
	public class Layout {
		public final int rows, columns;
		public final float leftBorder, rightBorder, topBorder, bottomBorder, slope;
		public final boolean backwards;
		
		public Layout(int rows, int columns, float slope, float borderX, float borderY, boolean backwards) {
			this.rows = rows;
			this.columns = columns;
			this.slope = slope;
			this.leftBorder = borderX;
			this.rightBorder = borderX;
			this.topBorder = borderY;
			this.bottomBorder = borderY;
			this.backwards = backwards;
		}
	}
}
