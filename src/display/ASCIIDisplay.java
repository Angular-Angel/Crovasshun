/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package display;

import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JFrame;

/**
 *
 * @author angle
 */
public class ASCIIDisplay extends JFrame {
    
    private JComponent screen;
    
    
    public ASCIIDisplay(String title) {
        super(title);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
        
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1606, 975);
        setVisible(true);
        
    }
    
    public void startScreen(Screen screen) {
        if (this.screen != null) {
            remove(this.screen);
        }
        add(screen);
        this.screen = screen;
        revalidate();
        screen.start();
    }
}
