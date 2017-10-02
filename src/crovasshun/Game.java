/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crovasshun;

import display.CombatScreen;
import display.TitleScreen;
import display.ASCIIDisplay;
import java.util.Random;

/**
 *
 * @author angle
 */
public class Game {
    
    public Random random;
    public ASCIIDisplay display;
    
    public Game() {
        random = new Random();
        display = new ASCIIDisplay("Crovasshun: Tales of Blood and Steel");
    }
    
    public void newGame() {
        CombatScreen combatScreen = new CombatScreen();
        display.startScreen(combatScreen);
    }
    
    public void run() {
        TitleScreen titleScreen = new TitleScreen(this);
        display.startScreen(titleScreen);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Game game = new Game();
        game.run();
    }
    
}
