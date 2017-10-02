/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package display;

import crovasshun.Body;
import crovasshun.GamePoint;
import crovasshun.Player;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JLayeredPane;

/**
 *
 * @author angle
 */
public class CombatScreen extends Screen {
    
    private Screen tileScreen;
    private LocalAreaScreen localAreaScreen;
    private JLayeredPane layeredPane;
    
    public CombatScreen() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        layeredPane = new JLayeredPane();
        add(layeredPane);
        
        localAreaScreen = new LocalAreaScreen(this);
        layeredPane.add(localAreaScreen, new Integer(0));
        
        CommandScreen commandScreen = new CommandScreen();
        add(commandScreen);
        
        tileScreen = new Screen();
        tileScreen.setLayout(new FlowLayout());
    }
    
    @Override
    public void paintComponent(Graphics g) {
        layeredPane.setPreferredSize(new Dimension(getWidth(), getHeight() * 3/4));
        localAreaScreen.setSize(layeredPane.getWidth(), layeredPane.getHeight());
        tileScreen.setLocation(layeredPane.getWidth()*5/6 -20, 10);
        revalidate();
        super.paintComponent(g);
    }
    
    public void showTileReadout(ArrayList<Screen> details) {
        if (details.isEmpty()) {
            hideTileReadout();
            return;
        }
        
        tileScreen.removeAll();
        
        for (Screen s : details) {
            tileScreen.add(s);
        }
        
        tileScreen.setSize(tileScreen.getPreferredSize());
        layeredPane.remove(tileScreen);
        layeredPane.add(tileScreen, new Integer(1));
    }
    
    public void hideTileReadout() {
        layeredPane.remove(tileScreen);
        repaint();
    }
    
}
