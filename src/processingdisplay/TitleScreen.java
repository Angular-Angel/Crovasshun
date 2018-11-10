package processingdisplay;

import crovasshun.Game;
import crovasshun.LocalArea;
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
		
		int[] grassColors = new int[3];
		grassColors[0] = game.color(0, 255, 0);
		grassColors[1] = game.color(0, 130, 0);
		grassColors[2] = game.color(30, 180, 0);
		RShape rShape = RG.getEllipse(200,200,300,200);
		rShape = rShape.diff(RG.getRect(100, 150, 120, 70));
		rShape = rShape.diff(RG.getRect(240, 150, 20, 40));
		Terrain terrain = new Terrain(rShape, new TerrainType("Grass", new ASCIITexture("Grass Texture", game.font, 
				grassColors, game.color(0, 70, 0), ',', '\'', '"', '`', '.')));
		LocalArea localArea = new LocalArea(1000.0f, 1000.0f);
		localArea.addTerrain(terrain);
		
    	game.setScreen(new AreaScreen(game, localArea));
    	//this.hide();
    }

}
