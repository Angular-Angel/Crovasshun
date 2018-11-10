package crovasshun;

import java.util.ArrayList;

public abstract class Stat {
	private float score;
	private boolean dirty;
	private ArrayList<Stat> dependencies = new ArrayList<>();

	public float getScore() {
		if (isDirty()) update();
		return score;
	}
	
	public abstract void update();

	protected boolean isDirty() {
		return dirty;
	}
	
	protected void clean() {
		dirty = false;
	}

	public void makeDirty() {
		this.dirty = true;
		for (Stat stat : dependencies) stat.makeDirty();
	}
	
	public void addDependency(Stat stat) {
		dependencies.add(stat);
	}
	
	public void removeDependency(Stat stat) {
		dependencies.remove(stat);
	}
}
