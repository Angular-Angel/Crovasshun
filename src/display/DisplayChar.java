/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package display;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

/**
 *
 * @author angle
 */
public class DisplayChar implements Drawable {
    private char symbol;
    private Color color;
    private Color bgColor;
    private Font font;
    public int x, y;
    
    public DisplayChar(char symbol, Color color) {
        this(symbol, color, Color.black);
    }
    
    public DisplayChar(char symbol, Color color, Color bgColor) {
        this(symbol, color, bgColor, new Font("Monospaced", Font.PLAIN, 12));
    }
    
    /**
     * Constructor
     * @param symbol The character 
     * @param color The color
     * @param bgColor The background character
     * @param font The font
     */
    public DisplayChar(char symbol, Color color, Color bgColor, Font font){
        this.symbol = symbol;
        this.color = color;
        this.bgColor = bgColor;
        this.font = font;
    }
    
    @Override
    public void draw(Graphics g) {
        g.setFont(font);
        FontMetrics metrics = g.getFontMetrics();
        g.setColor(bgColor);
        g.fillRect(x, y,
                  x + metrics.getMaxAdvance(), y + metrics.getHeight());
        g.setColor(color);
        String c = "" + symbol;
        g.drawString(c, x, y);
    }
    
}
