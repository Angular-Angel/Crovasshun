package crovasshun;

public class PlayerUnit implements Actor {

	private final Body body;
	
	public PlayerUnit(Body body) {
		this.body = body;
	}
	
	@Override
	public void update(long deltaTime) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean ready(long deltaTime) {
		// TODO Auto-generated method stub
		return false;
	}

}
