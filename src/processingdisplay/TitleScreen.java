package processingdisplay;

import crovasshun.Body;
import crovasshun.Game;
import crovasshun.LocalArea;
import crovasshun.LocalMapGenerator;
import crovasshun.Terrain;
import crovasshun.TerrainType;
import g4p_controls.GButton;
import g4p_controls.GEvent;
import geomerative.RG;
import geomerative.RShape;

public class TitleScreen extends Screen {

	public TitleScreen(Game game) {
		super(game);
		
		GButton newGame = new GButton(game, 750, 425, 100, 50, "New Game");
		newGame.setLocalColorScheme(11);
		newGame.addEventHandler(this, "newGame");
		
		GButton load = new GButton(game, 750, 475, 100, 50, "Load Game");
		load.setLocalColorScheme(11);
		
		GButton about = new GButton(game, 750, 525, 100, 50, "About");
		about.setLocalColorScheme(11);
		
		controller.addControl(newGame);
		controller.addControl(load);
		controller.addControl(about);
	}
	
	public void newGame(GButton button, GEvent event) {

		LocalMapGenerator.initialize(game);
		
    	LocalArea localArea = LocalMapGenerator.getStoneTriangles(1000, 1000);
		
		RShape spriteShape = RG.getEllipse(0, 0, 60, 60);
		
		ASCIISprite mapSprite = new ASCIISprite(spriteShape, game.color(199), game.font, "_ |\n" +
																						 "-0-");
		
		localArea.addBody(new Body("Jimmy", mapSprite, mapSprite, 100, 100));
		
    	game.setScreen(new CommandScreen(game, localArea));
    }

}
