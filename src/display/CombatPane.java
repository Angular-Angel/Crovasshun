/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package display;

import crovasshun.Terrain;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.BoxLayout;
import javax.swing.JLayeredPane;

/**
 *
 * @author angle
 */
public class CombatPane extends JLayeredPane {
    
    private Screen tileScreen;
    
    public CombatPane() {
        super();
        
        LocalAreaScreen localAreaScreen = new LocalAreaScreen(this);
        localAreaScreen.setHeight(80);
        add(localAreaScreen, new Integer(0));
        
        TextLogScreen textLog = new TextLogScreen();
        add(textLog, new Integer(1));
        
        tileScreen = new Screen();
        tileScreen.setLayout(new BoxLayout(tileScreen, BoxLayout.Y_AXIS));
        //add(tileScreen, new Integer(1));
        
        //Add a listener to adjust the sizes of things when the window is resized.
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                localAreaScreen.setSize(getWidth(), getHeight());
                textLog.setBounds(10, getHeight()*3/4 +10, getWidth()-20, getHeight()/4 -20 );
                //tileScreen.setBounds(getWidth()*5/6 -20, 10, getWidth()/6 -10, getHeight()/8);
                tileScreen.setLocation(getWidth()*5/6 -20, 10);
                tileScreen.setSize(tileScreen.getPreferredSize());
                tileScreen.revalidate();
            }
        });
    }
    
    public void showTerrainReadout(Terrain terrain) {
        tileScreen.removeAll();
        TerrainScreen terrainScreen = new TerrainScreen(terrain);
        terrainScreen.setBounds(getWidth()*5/6 -20, 10, getWidth()/6 -10, getHeight()/8);
        tileScreen.add(terrainScreen);
        tileScreen.setSize(tileScreen.getPreferredSize());
        tileScreen.revalidate();
        remove(tileScreen);
        add(tileScreen, new Integer(1));
    }
    
    public void hideTerrainReadout() {
        remove(tileScreen);
    }
    
}
