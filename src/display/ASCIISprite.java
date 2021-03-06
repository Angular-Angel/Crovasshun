/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package display;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author angle
 */
public class ASCIISprite {
    
    private ArrayList<ArrayList<ASCIIChar>> chars;
    private int width;
    private Color background;
    
    public ASCIISprite(Color background, Color color, String string) {
        this.background = background;
        chars = new ArrayList<>();
        chars.add(new ArrayList<>());
        width = 0;
        for (int i = 0, k = 0; i < string.length(); i++) {
            if (string.charAt(i) == '\n') {
                if (chars.get(k).size() > width) 
                    width = chars.get(k).size();
                k++;
                chars.add(new ArrayList<>());
            } else {
                chars.get(k).add(new ASCIIChar(color, string.charAt(i)));
            }
        }
        
        if (chars.get(chars.size() - 1).size() > width) 
                    width = chars.get(chars.size() - 1).size();
    }
    
    public int getHeight() {
        return chars.size();
    }
    
    public int getWidth() {
        return width;
    }
    
    public void draw(Point position, Graphics2D g) {
        drawRotated(position, g, 0);
    }
    
    public void drawRotated(Point position, Graphics2D g, int rotate) {
        position = new Point(position);
        g.setFont(new Font("Monospaced", Font.PLAIN, 12));
        FontMetrics m = g.getFontMetrics();
        int baseX = position.x + 4;
        g.rotate(rotate, position.x + (getWidth()+1) * m.stringWidth(" ")/2, position.y + (getHeight() * m.getHeight() + m.getHeight()/2)/2);
        g.setColor(background);
        g.fillOval(position.x, position.y, (getWidth()+1) * m.stringWidth(" "), getHeight() * m.getHeight() + m.getHeight()/2);
        for (ArrayList<ASCIIChar> list : chars) {
            position.y += m.getHeight();
            position.x = baseX;
            for (ASCIIChar character : list) {
                character.draw(position, g);
                position.x += m.stringWidth("" + character.character);
            }
        }
    }
}
