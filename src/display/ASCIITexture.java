/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package display;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Random;

/**
 *
 * @author angle
 */
public class ASCIITexture {
    
    public final char[] chars;
    public final Color[] colors;
    public final Color background;
    public final Font font;
    
    public ASCIITexture(Color color, char[] chars) {
        this(color, Color.BLACK, chars);
    }
    
    public ASCIITexture(Color[] colors, char[] chars) {
        this(colors, Color.BLACK, chars, new Font("Monospaced", Font.PLAIN, 12));
    }
    
    public ASCIITexture(Color color, Color background, char[] chars) {
        Color[] colors = new Color[1];
        colors[0] = color;
        this.colors = colors;
        this.background = background;
        this.chars = chars;
        this.font = new Font("Monospaced", Font.PLAIN, 12);
    }
    
    public ASCIITexture(Color[] colors, Color background, char[] chars) {
        this(colors, background, chars, new Font("Monospaced", Font.PLAIN, 12));
    }
    
    public ASCIITexture(Color[] colors, Color background, char[] chars, Font font) {
        this.colors = colors;
        this.background = background;
        this.chars = chars;
        this.font = font;
    }
    
}
