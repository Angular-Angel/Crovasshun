package crovasshun.ui;

import crovasshun.Body;
import crovasshun.Game;
import crovasshun.Updatable;
import crovasshun.map.LocalArea;
import crovasshun.map.MapObject;
import crovasshun.map.Terrain;
import g4p_controls.GButton;
import g4p_controls.GCScheme;
import g4p_controls.GEvent;
import geomerative.RPoint;
import processing.event.MouseEvent;

public class AreaScreen extends Screen implements Updatable {
	
	protected LocalArea localArea;
	protected View view;
	protected final int mouseRadius = 100; //How far from the edge to start panning.
	protected final float panSpeedDivisor = 1000000; // How slow to pan.
	protected boolean panningDisabled = false;
	protected GButton resetView;
	public final int commandboxHeight = 80;

	public AreaScreen(Game game, LocalArea localArea) {
		super(game);
		this.localArea = localArea;
		this.view = new View();
		game.updatables.add(this);
		
		resetView = new GButton(game, 20, game.height - 60, 100, 50, "Reset View");
		resetView.setLocalColorScheme(11);
		resetView.addEventHandler(this, "resetView");
	}
	
	public void update(long deltaTime) {
		updatePanning(deltaTime);
	}
	
	public void updatePanning(long deltaTime) {
		if (!game.mousePresent || panningDisabled) return;
		
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
	
	public void resetView(GButton button, GEvent event) {
		view = new View();
	}

	@Override
	public void draw() {
		drawArea(localArea);
		drawFrame();
	}
	
	public void drawArea(LocalArea localArea) {
		game.pushMatrix();
		
		view.apply(game);
		
		for (Terrain terrain : localArea.terrain) {
			terrain.draw(game);
		}
		
		for (MapObject mapObject : localArea.mapObjects) {
			mapObject.draw(game);
		}
		
		for (Body body : localArea.bodies) {
			body.draw(game);
		}
		
		game.popMatrix();
	}
	
	public void drawFrame() {
		int[] pallette = GCScheme.getPalette(11);
		game.fill(pallette[4]);
		game.stroke(pallette[3]);
		//draw the main command box
		game.rect(0, game.height - commandboxHeight, game.width - 1, 199);
		
		//resetView.moveTo(game.width / 12, game.height - 60);
	}
	
	public void mouseClicked(MouseEvent event) {
		if (event.getY() > game.height - commandboxHeight) //If we're in the command box
			panningDisabled = true;
		else panningDisabled = false; //if we're in the area box
	}
	
	@Override
	public void mouseWheel(MouseEvent event) {
		float oldscale = view.scale;
		
		RPoint point = view.adjustPoint(event.getX(), event.getY());
		
    	view.scale *= Math.pow(0.9, event.getCount());
		
		float scalechange = view.scale - oldscale;
		
		view.x -= point.x * scalechange;
		view.y -= point.y * scalechange;
    }
}
