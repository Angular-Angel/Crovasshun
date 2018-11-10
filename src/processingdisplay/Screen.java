package processingdisplay;

import crovasshun.Game;
import interfascia.GUIController;

public abstract class Screen {
	public final Game game;
	protected final GUIController controller;
	
	public Screen(Game game) {
		this.game = game;
		
		controller = new GUIController(game);
		controller.setLookAndFeel(game.look);
	}
	
	public void draw() {
		
	}
	
	public void hide() {
		controller.setVisible(false);
	}
	
	public void show() {
		controller.setVisible(true);
	}
}
