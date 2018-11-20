package crovasshun.stat;

import java.util.ArrayList;

import crovasshun.Dirtyable;

public abstract class Stat implements Dirtyable {
	private float score;
	private boolean dirty;
	private ArrayList<Stat> dependencies = new ArrayList<>();

	public float getScore() {
		if (isDirty()) update();
		return score;
	}
	
	public abstract void update();

	public boolean isDirty() {
		return dirty;
	}

	public void dirty() {
		this.dirty = true;
		for (Stat stat : dependencies) stat.dirty();
	}
	
	public void addDependency(Stat stat) {
		dependencies.add(stat);
	}
	
	public void removeDependency(Stat stat) {
		dependencies.remove(stat);
	}
}
