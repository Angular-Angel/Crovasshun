/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package display;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;

/**
 *
 * @author angle
 */
public class Screen extends JPanel {
    
    
    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        paintBackground(g);

        drawBorder(g);
    }
    
    public void paintBackground(Graphics g) {
        //draw the background, hiding anything behind this pane.
        g.setColor(Color.black);
        g.fillRect(0, 0, getWidth()-1, getHeight()-1);
        
    }
    
    public void drawBorder(Graphics g) {
        //draw the border, covering up anything along the side of this pane.
        g.setColor(Color.WHITE);
        g.drawRect(0, 0, getWidth()-1, getHeight()-1);
    }
    
    public void start() {
        
    }
    
}
