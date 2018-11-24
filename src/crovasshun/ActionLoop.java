package crovasshun;

import java.util.ArrayList;

public class ActionLoop implements Updatable {
    public ArrayList<Actor> actors = new ArrayList<>();

	@Override
	public void update(long deltaTime) {
		
		if (updating(deltaTime)) {
			for (Actor actor : actors) {
				actor.update(deltaTime);
			}
		}
	}
	
	public boolean updating(long deltaTime) {
		for (Actor actor : actors) {
			if (!actor.ready(deltaTime)) return false;
		}
		return true;
	}
    
    
}
