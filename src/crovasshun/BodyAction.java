package crovasshun;

public abstract class BodyAction implements Updatable {
	public final Body body;
	
	public BodyAction(Body body) {
		this.body = body;
	}
	
	public abstract void update (long deltaTime);
	
	public abstract boolean isFinished();
}
