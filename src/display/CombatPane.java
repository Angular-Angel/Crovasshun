/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package display;

import crovasshun.Body;
import crovasshun.GamePoint;
import java.awt.FlowLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.JLayeredPane;

/**
 *
 * @author angle
 */
public class CombatPane extends JLayeredPane {
    
    private Screen tileScreen;
    private LocalAreaScreen localAreaScreen;
    
    public CombatPane() {
        super();
        
        localAreaScreen = new LocalAreaScreen(this);
        add(localAreaScreen, new Integer(0));
        
        TextLogScreen textLog = new TextLogScreen();
        add(textLog, new Integer(1));
        
        tileScreen = new Screen();
        tileScreen.setLayout(new FlowLayout());
        
        //Add a listener to adjust the sizes of things when the window is resized.
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                localAreaScreen.setSize(getWidth(), getHeight());
                textLog.setBounds(10, getHeight()*3/4 +10, getWidth()-20, getHeight()/4 -20 );
                tileScreen.setLocation(getWidth()*5/6 -20, 10);
            }
        });
    }
    
    public void showTileReadout(GamePoint hex) {
        tileScreen.removeAll();
        
        TerrainScreen terrainScreen = new TerrainScreen(hex.terrain);
        tileScreen.add(terrainScreen);
        
        for (Body b : hex.bodies) {
            BodyScreen bodyScreen = new BodyScreen(b);
            tileScreen.add(bodyScreen);
        }
        
        tileScreen.setSize(tileScreen.getPreferredSize());
        remove(tileScreen);
        add(tileScreen, new Integer(1));
    }
    
    public void hideTerrainReadout() {
        remove(tileScreen);
    }
    
}
