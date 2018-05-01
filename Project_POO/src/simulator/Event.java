package simulator;

import java.util.List;

public abstract class Event {

	protected double time;
	
	protected abstract List<Event> doEvent();
	
	public double time() {
		return time;
	}
}
