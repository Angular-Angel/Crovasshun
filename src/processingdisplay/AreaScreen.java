package processingdisplay;

import crovasshun.Game;
import crovasshun.LocalArea;
import crovasshun.Terrain;
import crovasshun.Updatable;
import crovasshun.View;
import geomerative.RG;

public class AreaScreen extends Screen implements Updatable {
	
	private LocalArea localArea;
	private View view;
	private final int mouseRadius = 100; //How far from the edge to start panning.
	private final float panSpeedDivisor = 1000000; // How slow to pan.
	private ASCIISprite sprite;

	public AreaScreen(Game game, LocalArea localArea) {
		super(game);
		this.localArea = localArea;
		this.view = new View();
		game.updatables.add(this);
		sprite = new ASCIISprite(RG.getEllipse(100, 100, 70, 120), game.color(199), game.font, "_ |\n" +
                																		   "-0-\n");
	}
	
	public void update(float deltaTime) {
		if (!game.mousePresent) return;
			
		if (game.mouseX < mouseRadius) {
			view.x += deltaTime / panSpeedDivisor;
		} else if (game.mouseX > game.width - mouseRadius) {
			view.x -= deltaTime / panSpeedDivisor;
		}

		if (game.mouseY < mouseRadius) {
			view.y += deltaTime / panSpeedDivisor;
		} else if (game.mouseY > game.height - mouseRadius) {
			view.y -= deltaTime / panSpeedDivisor;
		}
	}

	@Override
	public void draw() {
		view.apply(game);
		for (Terrain terrain : localArea.terrain) {
			terrain.shape.draw(game);
		}
		sprite.shape.draw(game);
	}

}
