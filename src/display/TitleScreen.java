/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package display;

import crovasshun.Game;
import crovasshun.Game;
import display.ASCIIButton;
import display.Screen;
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
public class TitleScreen extends Screen {
    public Game game;
    
    public TitleScreen(Game game) {
        super();
        this.game = game;
        //centers the menus via GridBagLayout.
        setLayout(new GridBagLayout());
        
        //Creates invisible panel to hold our buttons, letting them line up vertically for us.
        JPanel invisiblePanel = new JPanel();
        invisiblePanel.setLayout(new BoxLayout(invisiblePanel, BoxLayout.Y_AXIS));
        invisiblePanel.setBackground(Color.BLACK);
        add(invisiblePanel);
        
        //adds the buttons.
        JButton button = new ASCIIButton("NEW GAME");
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setAction(new AbstractAction("NEW GAME") {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.newGame();
            }
        });
        invisiblePanel.add(button);
        
        button = new ASCIIButton("LOAD GAME");
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        invisiblePanel.add(button);
       
        button = new ASCIIButton("ABOUT");
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        invisiblePanel.add(button);
        
        button = new ASCIIButton("QUIT");
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setAction(new AbstractAction("QUIT") {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        invisiblePanel.add(button);
    }
    
}
