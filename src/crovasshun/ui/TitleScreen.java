package crovasshun.ui;

import crovasshun.Body;
import crovasshun.Game;
import crovasshun.MoveAction;
import crovasshun.PlayerUnit;
import crovasshun.map.LocalArea;
import crovasshun.map.LocalMapGenerator;
import g4p_controls.GButton;
import g4p_controls.GEvent;
import geomerative.RG;
import geomerative.RPoint;
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
		Body body = new Body("Jimmy", mapSprite, mapSprite, 500, 500);
		
		localArea.addBody(body);
		
		PlayerUnit playerUnit = new PlayerUnit(body);
		
		MoveAction moveAction = new MoveAction(body, new RPoint(300, 400));
		
		playerUnit.addAction(moveAction);
		
    	game.setScreen(new CommandScreen(game, localArea));
    }

}
