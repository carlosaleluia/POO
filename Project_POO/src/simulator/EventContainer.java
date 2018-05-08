package simulator;

import java.util.List;

public interface EventContainer {

	/**
	 * This method adds a new Event to the container.
	 * @param new Event to be added.
	 */
	public void addEvent(Event e);
	
	
	/**
	 * This method adds multiple new Events to the container.
	 * @param list of new Events to be added.
	 */
	public void addEvent(List<Event> e);
	
	
	/**
	 * This method adds multiple new Events to the container.
	 * @param list of new Events to be added.
	 */
	public void removeEvent(Event e);
	
	
	/**
	 * This method removes an Event in the container, if present.
	 * @param Event to be removed.
	 */
	public int numberEvents();
	
	
	/**
	 * This method checks if the container is empty.
	 * @return true if EventContainer has no events and false otherwise.
	 */
	public boolean isempty();
	
	
	/**
	 * This method polls the next Event in line to be simulated.
	 * @return next event in line.
	 */
	public Event nextEvent();
	
	
}
