package processingdisplay;

import crovasshun.Game;
import crovasshun.LocalArea;
import crovasshun.Terrain;
import crovasshun.TerrainType;
import geomerative.RFont;
import geomerative.RG;
import geomerative.RShape;
import interfascia.GUIEvent;
import interfascia.IFButton;

public class TitleScreen extends Screen {

	public TitleScreen(Game game) {
		super(game);
		
		IFButton newGame = new IFButton("New Game", 750, 425, 100, 50);
		newGame.addActionListener(this);
		
		IFButton load = new IFButton("Load Game", 750, 475, 100, 50);
		
		IFButton about = new IFButton("About", 750, 525, 100, 50);
		
		controller.add(newGame);
		controller.add(load);
		controller.add(about);
	}
	
	public void actionPerformed (GUIEvent e) {
		
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
