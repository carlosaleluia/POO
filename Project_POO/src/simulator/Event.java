package simulator;

import java.util.List;

public abstract class Event {

	/**
	 * Time of Event.
	 */
	protected double time;
	
	/**
	 * This method does the Event and returns new Events that should happen afterwards as a consequence.
	 * @return list of Events that are consequence of doing present Event.
	 */
	protected abstract List<Event> doEvent();
	
	/**
	 * Getter for Event time.
	 * @return time of Event.
	 */
	public double time() {
		return time;
	}
}
