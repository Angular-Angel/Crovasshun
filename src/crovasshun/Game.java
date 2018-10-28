/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crovasshun;

import java.awt.Color;
import java.awt.geom.Ellipse2D;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import display.ASCIIDisplay;
import display.ASCIISprite;
import display.CombatScreen;
import display.TitleScreen;
import g4p_controls.G4P;
import g4p_controls.GCScheme;
import g4p_controls.GPanel;
import geomerative.RFont;
import geomerative.RG;
import geomerative.RShape;
import processing.core.PApplet;
import processingdisplay.ASCIIShape;
import processingdisplay.ASCIITexture;
/**
 *
 * @author angle
 */
public class Game extends PApplet{
    
    public Random random;
    public ASCIIDisplay display;
    ASCIIShape shape;
    
    public boolean running, startGame;
    
    public Game() {
        //random = new Random();
        //display = new ASCIIDisplay("Crovasshun: Tales of Blood and Steel");
        //running = true;
        //startGame = false;
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
        //Game game = new Game();
        //game.run();
    	PApplet.main("crovasshun.Game");
    }
    
    public void settings(){
        size(1606, 975);
        smooth();
    }

    public void setup(){
		RG.init(this);
		random = new Random();
		running = true;
		startGame = false;
		
		RFont font = RG.loadFont("./go-mono/Go-Mono.ttf");
		
		font.setSize(15);
		//font.setAlign(font.CENTER);
		
		G4P.messagesEnabled(false);
		G4P.setGlobalColorScheme(GCScheme.SCHEME_9);
		G4P.setCursor(ARROW);
		surface.setTitle("Crovasshun: Tales of Blood and Steel");
		//GPanel panel1 = new GPanel(this, 0, 0, 480, 320, "Tab bar text");
		//panel1.setText("Tab bar text");
		//panel1.setLocalColorScheme(GCScheme.SCHEME_8);
		//panel1.setOpaque(true);
		//panel1.addEventHandler(this, "panel1_Click1");
		
		int[] grassColors = new int[3];
		grassColors[0] = color(0, 255, 0);
		grassColors[1] = color(0, 130, 0);
		grassColors[2] = color(30, 180, 0);
		RShape rShape = RG.getEllipse(200,200,300,200);
		rShape = rShape.diff(RG.getRect(100, 150, 120, 70));
		rShape = rShape.diff(RG.getRect(240, 150, 20, 40));
		shape = new ASCIIShape(rShape, new ASCIITexture("Grass Texture", font, grassColors, color(0, 70, 0), ',', '\'', '"', '`', '.'));
    }

    public void draw(){
    	background(0);
    	//texture.fillShape(shape, this);
    	shape.draw(this);
    }
    
}
