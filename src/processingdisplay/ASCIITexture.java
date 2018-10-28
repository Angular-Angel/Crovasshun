/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package processingdisplay;

import java.util.Random;

import geomerative.RFont;
import geomerative.RPoint;
import geomerative.RShape;
import processing.core.PApplet;

/**
 *
 * @author angle
 */
public class ASCIITexture {
    
	public final String name;
    public final RFont font;
    public final char[] chars;
    public final int[] colors;
    public final int background;
    public final boolean isRandom;
    
    private int index = 0;
    
    public ASCIITexture(String name, RFont font, int color, int background, char... chars) {
        this(name, font, color, background, chars, true);
    }
    
    public ASCIITexture(String name, RFont font, int color, int background, char[] chars, boolean random) {
    	this.name = name;
    	this.font = font;
    	int[] colors = new int[1];
        colors[0] = color;
        this.colors = colors;
        this.background = background;
        this.chars = chars;
        this.isRandom = random;
    }
    
    public ASCIITexture(String name, RFont font, int color, int background, char character, boolean random) {
    	this.name = name;
    	this.font = font;
    	int[] colors = new int[1];
        colors[0] = color;
        this.colors = colors;
        this.background = background;
        char[] chars = new char[1];
        chars[0] = character;
        this.chars = chars;
        this.isRandom = random;
    }
    
    public ASCIITexture(String name, RFont font, int[] colors, int background, char... chars) {
    	this(name, font, colors, background, chars, true);
    }
    
    public ASCIITexture(String name, RFont font, int[] colors, int background, char[] chars,  boolean random) {
    	this.name = name;
    	this.font = font;
        this.colors = colors;
        this.background = background;
        this.chars = chars;
        this.isRandom = random;
    }
    
    public char getCharacter(int index) {
    	return this.chars[index];
    }
    
    public int getColor(int index) {
    	return this.colors[index];
    }
    
}
