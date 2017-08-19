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
    public final Color color;
    public final Font font;
    
    public ASCIITexture(Color color, char[] chars, Font font) {
        this.color = color;
        this.chars = chars;
        this.font = font;
    }
    
}
