package crovasshun;

import java.util.ArrayList;

import geomerative.RPoint;

public class PlayerUnit implements Controller {

	private final Body body;
	public ArrayList<BodyAction> actions = new ArrayList<>();
	
	public PlayerUnit(Body body) {
		this.body = body;
		body.setController(this);
		actions.add(new MoveAction(body, new RPoint(200, 200)));
	}
	
	@Override
	public void update(long deltaTime) {
		
	}

	@Override
	public boolean ready(long deltaTime) {
		return actions.size() > 0;
	}

	@Override
	public BodyAction getNextAction() {
		if (actions.size() == 0)
			return null;
		
		if (actions.get(0).isFinished()) {
			actions.remove(0);
			return getNextAction();
		}
		else return actions.get(0);
	}
	
	public void addAction(BodyAction bodyAction) {
		actions.add(bodyAction);
	}

}
