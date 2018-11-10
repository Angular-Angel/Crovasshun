package processingdisplay;

import crovasshun.Game;
import g4p_controls.GGroup;
import processing.event.MouseEvent;

public abstract class Screen {
	public final Game game;
	protected final GGroup controller;
	
	public Screen(Game game) {
		this.game = game;
		
		controller = new GGroup(game);
		//controller.setLookAndFeel(game.look);
	}
	
	public void draw() {
		
	}
	
	public void hide() {
		controller.setVisible(false);
	}
	
	public void show() {
		controller.setVisible(true);
	}
	
	public void mouseWheel(MouseEvent event) {
    	
    }
}
