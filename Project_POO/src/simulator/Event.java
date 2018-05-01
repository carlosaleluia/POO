package simulator;

public abstract class Event {

	protected double time;
	
	protected abstract Event[] doEvent();
	
	public double time() {
		return time;
	}
}
