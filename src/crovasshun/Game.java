/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crovasshun;

import java.util.ArrayList;
import java.util.Random;

import geomerative.RFont;
import geomerative.RG;
import interfascia.IFLookAndFeel;
import processing.core.PApplet;
import processingdisplay.ASCIIFont;
import processingdisplay.Screen;
import processingdisplay.TitleScreen;

/**
 *
 * @author angle
 */
public class Game extends PApplet{
    
    public Random random;
    public IFLookAndFeel look;
    public ArrayList<Updatable> updatables = new ArrayList<>();
    public boolean mousePresent = true;
    public ASCIIFont font;
    
    private Screen screen;
    private long lastFrameTime;
    
    public void setScreen(Screen screen) {
        if (this.screen != null) {
            this.screen.hide();
        }
        this.screen = screen;
        screen.show();
    }
    
    public void mouseExited() {
    	mousePresent = false;
    }
    
    public void mouseEntered() {
    	mousePresent = true;
    }
    
    public void newGame() {
        /*LocalArea area = LocalMapGenerator.getObelisk(1600, 800);
        
        Body body = new Body("Player", new ASCIISprite(new Color(30, 30, 30, 255), new Color(255, 182, 193), "_ |\n" +
                "-0-"), 
                new ASCIISprite(new Color(0, 0, 0), new Color(255, 182, 193), "   0\n" +
                        "1_/|\\_O\n" +
                        "   |\n" +
                        "  / \\\n" +
                        "  | |"), new Ellipse2D.Float(106, 150, 28, 36));
        
        body.setActor(new PlayerUnit(body));
        
        area.bodies.add(body);
        
        CombatScreen combatScreen = new CombatScreen(area);
        display.startScreen(combatScreen);*/
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    	PApplet.main("crovasshun.Game");
    }
    
    public void settings(){
        size(1600, 1000);
        smooth();
    }

    public void setup(){
		RG.init(this);
		random = new Random();
		
		surface.setTitle("Crovasshun: Tales of Blood and Steel");
		surface.setResizable(true);
		
		look = new IFLookAndFeel(this, IFLookAndFeel.DEFAULT);
		look.baseColor = color(0);
		look.textColor = color(255);
		look.highlightColor = color(144);
		
		font = new ASCIIFont("./go-mono/Go-Mono.ttf", 15);
		
		setScreen(new TitleScreen(this));
		
		lastFrameTime = System.nanoTime();
    }

    public void draw(){
    	long currentFrameTime = System.nanoTime();
    	long deltaTime = currentFrameTime - lastFrameTime;
    	
    	update(deltaTime);
    	
    	render();
    	lastFrameTime = currentFrameTime;
    }
    
    public void update(long deltaTime) {
    	for (Updatable updatable: updatables) updatable.update(deltaTime);
    }
    
    public void render() {
    	background(0);
    	screen.draw();
    }
    
}
