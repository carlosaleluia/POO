package simulator;

import java.util.List;

import pathfinder.MainSimulator;

/**
 * This abstract class provides the general case of every time Event. <p>
 * Should be extended by any specific type of Event. It's main method, {@link simulator.Event#doEvent()}, returns a List of Events that must happen as a consequence. 
 *
 */
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
