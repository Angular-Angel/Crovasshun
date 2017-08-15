/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package display;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;

/**
 *
 * @author angle
 */
public class ASCIIButton extends JButton {
    
    public ASCIIButton(String string) {
        super(string);
        setForeground(Color.WHITE);
        setBackground(Color.BLACK);
        setContentAreaFilled(false);
        setFont(new Font("Monospaced", Font.PLAIN, 12));
    }
    
}
