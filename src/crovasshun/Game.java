/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crovasshun;

import java.util.ArrayList;
import java.util.Random;

import crovasshun.ui.ASCIIFont;
import crovasshun.ui.Screen;
import crovasshun.ui.TitleScreen;
import g4p_controls.GCScheme;
import geomerative.RG;
import processing.core.PApplet;
import processing.event.MouseEvent;

/**
 *
 * @author angle
 */
public class Game extends PApplet{
    
    public Random random;
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

    @Override
    public void mouseExited() {
    	mousePresent = false;
    }
    
    @Override
    public void mouseEntered() {
    	mousePresent = true;
    }
    
    @Override
    public void mouseClicked(MouseEvent event) {
    	super.mouseClicked(event);
    	screen.mouseClicked(event);
    }
    
    @Override
    public void mouseWheel(MouseEvent event) {
    	super.mouseWheel(event);
    	screen.mouseWheel(event);
    }

    @Override
    public void frameResized(int w, int h) {
    	super.frameResized(w, h);
    	screen.frameResized(w, h);
    }
    
    @Override
    public void frameMoved(int x, int y) {
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

    @Override
    public void settings(){
        size(1600, 1000, P2D);
        smooth();
    }
    
    @Override
    public void setup(){
		RG.init(this);
		
		random = new Random();
		
		surface.setTitle("Crovasshun: Tales of Blood and Steel");
		surface.setResizable(true);
		
		
		GCScheme.makeColorSchemes(this); // This statement is rarely needed
		GCScheme.copyPalette(10, 11);
		GCScheme.changePaletteColor(11, 4, color(0));
		GCScheme.changePaletteColor(11, 6, color(144));
		GCScheme.changePaletteColor(11, 14, color(200));
		
		//font = new ASCIIFont("./DejaVu Serif/DejaVuSerif.ttf", 15);
		font = new ASCIIFont("./go-mono/Go-Mono.ttf", 15);
		
		setScreen(new TitleScreen(this));
		
		lastFrameTime = System.nanoTime();
    }

    @Override
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
