/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crovasshun;

import display.CombatScreen;
import display.TitleScreen;
import display.ASCIIDisplay;
import display.ASCIISprite;
import java.awt.Color;
import java.awt.geom.Ellipse2D;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author angle
 */
public class Game {
    
    public Random random;
    public ASCIIDisplay display;
    
    public boolean running, startGame;
    
    public Game() {
        random = new Random();
        display = new ASCIIDisplay("Crovasshun: Tales of Blood and Steel");
        running = true;
        startGame = false;
    }
    
    public void newGame() {
        startGame = false;
        LocalArea area = LocalMapGenerator.getObelisk(1600, 800);
        
        Body body = new Body("Player", new ASCIISprite(new Color(30, 30, 30, 255), new Color(255, 182, 193), "_ |\n" +
                "-0-"), 
                new ASCIISprite(new Color(0, 0, 0, 0), new Color(255, 182, 193), "   0\n" +
                        "1_/|\\_O\n" +
                        "   |\n" +
                        "  / \\\n" +
                        "  | |"), new Ellipse2D.Float(106, 150, 28, 36));
        
        body.setActor(new PlayerUnit(body));
        
        area.bodies.add(body);
        
        CombatScreen combatScreen = new CombatScreen(area);
        display.startScreen(combatScreen);
    }
    
    public void run() {
        TitleScreen titleScreen = new TitleScreen(this);
        display.startScreen(titleScreen);
        while (running) {
            if (startGame) newGame();
            else try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Game game = new Game();
        game.run();
    }
    
}
