/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package display;

import crovasshun.Player;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author angle
 */
public class CommandScreen extends Screen {
    
    private TextLogScreen textLog;
    
    public CommandScreen() {
        
        super();
        
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        //Creates invisible panel to hold our buttons, letting them line up horizontally for us.
        JPanel invisiblePanel = new JPanel();
        invisiblePanel.setLayout(new BoxLayout(invisiblePanel, BoxLayout.X_AXIS));
        invisiblePanel.setBackground(Color.BLACK);
        add(invisiblePanel);
        
        //adds the buttons.
        JButton button = new ASCIIButton("Move");
        button.setAlignmentY(Component.CENTER_ALIGNMENT);
        button.setAction(new AbstractAction("Move") {
            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
        });
        invisiblePanel.add(button);
        
        textLog = new TextLogScreen();
        
        add(textLog);
        
        
        
    }
    
}