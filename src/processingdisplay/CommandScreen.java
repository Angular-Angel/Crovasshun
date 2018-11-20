package processingdisplay;

import crovasshun.Game;
import crovasshun.LocalArea;
import processing.event.MouseEvent;

public class CommandScreen extends AreaScreen {
	
	private int ordersBoxHeight = 120;

	public CommandScreen(Game game, LocalArea localArea) {
		super(game, localArea);
	}
	
	public void drawFrame() {
		super.drawFrame();
		
		//Draw the play/pause command box.
		game.rect(0, game.height - 230, 60, 30);
		
		//Draw the orders box.
		game.rect(0, game.height - (commandboxHeight + ordersBoxHeight), game.width - 1, ordersBoxHeight);
	}
	
	public void mouseClicked(MouseEvent event) {
		if (event.getY() > game.height - (commandboxHeight + ordersBoxHeight)) //If we're in the command box
			panningDisabled = true;
		else if (event.getY() > game.height - 230 && event.getX() < 60) //If we're in the play/pause box
			panningDisabled = true;
		else panningDisabled = false; //if we're in the area box
	}
}
