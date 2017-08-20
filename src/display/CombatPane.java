/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package display;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.JLayeredPane;

/**
 *
 * @author angle
 */
public class CombatPane extends JLayeredPane {
    
    public CombatPane() {
        super();
        
        TerrainScreen terrainScreen = new TerrainScreen();
        terrainScreen.setHeight(80);
        add(terrainScreen, new Integer(0));
        
        TextLogScreen textLog = new TextLogScreen();
        add(textLog, new Integer(1));
        
        //Add a listener to adjust the sizes of things when the window is resized.
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                terrainScreen.setSize(getWidth(), getHeight());
                textLog.setBounds(10, getHeight()*3/4 +10, getWidth()-20, getHeight()/4 -20 );
            }
        });
    }
    
}
