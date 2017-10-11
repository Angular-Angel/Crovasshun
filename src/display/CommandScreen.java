/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package display;

import crovasshun.MoveAction;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashSet;
import javax.swing.AbstractAction;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author angle
 */
public class CommandScreen extends Screen {
    
    private final TextLogScreen textLog;
    private final CombatScreen combatScreen;
    private final ArrayList<ButtonScreen> buttonScreens;
    private final JPanel invisiblePanel;
    
    public CommandScreen(CombatScreen combatScreen) {
        
        super();
        
        this.combatScreen = combatScreen;
        
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        //Creates invisible panel to hold our button screen
        invisiblePanel =  new JPanel();
        invisiblePanel.setLayout(new BoxLayout(invisiblePanel, BoxLayout.X_AXIS));
        invisiblePanel.setBackground(Color.BLACK);
        add(invisiblePanel);
        
        //Create a list so we can keep track of screens and return to previous 
        //ones, and create a starting screen to hold our buttons, letting them 
        //line up horizontally for us.
        buttonScreens = new ArrayList<>();
        ButtonScreen buttonScreen = new ButtonScreen();
        pushButtonScreen(buttonScreen);
        
        //adds the buttons.
        ASCIIButton button = new ASCIIButton("Move");
        button.setAlignmentY(Component.CENTER_ALIGNMENT);
        button.setAction(new AbstractAction("Move") {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MouseMovePlotting().init(combatScreen);
            }
        });
        buttonScreen.add(button);
        
        textLog = new TextLogScreen();
        
        add(textLog);
    }
    
    public void pushButtonScreen(ButtonScreen buttonScreen) {
        if (buttonScreens.contains(buttonScreen)) throw new IllegalArgumentException("Trying to add Duplicate buttonScreen.");
        if (buttonScreens.size() > 0)
            invisiblePanel.removeAll();
        buttonScreens.add(buttonScreen);
        invisiblePanel.add(buttonScreen);
        revalidate();
    }
    
    public void popButtonScreen() {
        if (buttonScreens.size() > 0){
            invisiblePanel.removeAll();
            buttonScreens.remove(buttonScreens.size() - 1);
        }
        if (buttonScreens.size() > 0)
            invisiblePanel.add(buttonScreens.get(buttonScreens.size() - 1));
        revalidate();
    }
    
}
