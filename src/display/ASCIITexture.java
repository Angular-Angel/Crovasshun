/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package display;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.util.Random;

/**
 *
 * @author angle
 */
public class ASCIITexture {
    
	public final String name;
    public final char[] chars;
    public final Color[] colors;
    public final Color background;
    public final boolean random;
    
    private int stringIndex = 0;
    
    public ASCIITexture(String name, Color color, char[] chars) {
        this(name, color, Color.BLACK, chars);
    }
    
    public ASCIITexture(String name, Color[] colors, char[] chars) {
        this(name, colors, Color.BLACK, chars, true);
    }
    
    public ASCIITexture(String name, Color color, Color background, char[] chars) {
        this(name, color, background, chars, true);
    }
    
    public ASCIITexture(String name, Color color, Color background, char[] chars, boolean random) {
    	this.name = name;
        Color[] colors = new Color[1];
        colors[0] = color;
        this.colors = colors;
        this.background = background;
        this.chars = chars;
        this.random = random;
    }
    
    public ASCIITexture(String name, Color color, Color background, char character, boolean random) {
    	this.name = name;
        Color[] colors = new Color[1];
        colors[0] = color;
        this.colors = colors;
        this.background = background;
        char[] chars = new char[1];
        chars[0] = character;
        this.chars = chars;
        this.random = random;
    }
    
    public ASCIITexture(String name, Color[] colors, Color background, char[] chars) {
        this(name, colors, background, chars, true);
    }
    
    public ASCIITexture(String name, Color[] colors, Color background, char[] chars,  boolean random) {
    	this.name = name;
        this.colors = colors;
        this.background = background;
        this.chars = chars;
        this.random = random;
    }
    
    private char getCharacter(Random randomGen) {
        if (random) return this.chars[randomGen.nextInt(this.chars.length)];
        else return this.chars[stringIndex++ % this.chars.length];
    }
    
    private Color getColor(Random randomGen) {
    	if (random) return this.colors[randomGen.nextInt(this.colors.length)];
        else return this.colors[stringIndex++ % this.colors.length];
    }
    
    public void fillShape(Shape shape, Graphics2D g) {
        
        g.setColor(background);
        g.fill(shape);
        
        Rectangle bounds = shape.getBounds();
        
        Random randomGen = new Random(bounds.x + 7 * bounds.y);
        
        FontMetrics m = g.getFontMetrics();
        int border = 3;
        
        stringIndex = 0;
        int yi = 0;
        
        while(yi <= bounds.height) {
            int leftBound = bounds.x;
            
            while (leftBound + border < bounds.x + bounds.width) {
            	String string = "";
	            while (!shape.contains(new Rectangle(leftBound, bounds.y + yi, m.stringWidth(string) + border*2, m.getHeight() + border)) && leftBound < bounds.width + bounds.x) {
	            	leftBound++;
	            }
	            stringIndex = 0;
	            while(shape.contains(new Rectangle(leftBound, bounds.y + yi, m.stringWidth(string) + border*2, m.getHeight() + border))) {
	            	string += getCharacter(randomGen);
	            }
	            if (string.length() > 0) {
	            	
		            if (this.colors.length > 1) { //If the texture has multiple colors, draw it's characters one at a time, alternating colors
		                stringIndex = 0;
		                while (string.length() > 0) {
		                	g.setColor(getColor(randomGen));
		                    String substring = string.substring(0, 1);
		                    g.drawString(substring, leftBound, bounds.y + yi + m.getHeight());
		                    leftBound += m.stringWidth(substring);
		                    string = string.substring(1, string.length());
		                }
		            } else {
		                g.setColor(this.colors[0]);
		                g.drawString(string, leftBound, bounds.y + yi + border + m.getHeight());
	                    leftBound += m.stringWidth(string);
		            }
	            }
            }

        	//System.out.println(name + ": " + yi);
            yi += m.getHeight();
        }
        
    }
    
}
