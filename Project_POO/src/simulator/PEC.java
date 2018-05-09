package simulator;

import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * This class implements {@link simulator.EventContainer}, specifying criteria for sorting the Events and which is the next in line.<p>
 * In this PEC case, Events are ordered in ascending time order, using a {@link java.util.PriorityQueue}, providing a Comparator.
 * @see simulator.PEC
 * @see simulator.EventComparatorByTime
 *
 */
public class PEC implements EventContainer{

	/**
	 * Data structure used to store events by ascending order of time.
	 * @see Event
	 */
	PriorityQueue<Event> events;
	
	/**
	 * Constructor of Pending Event Container (PEC).
	 * @param initial estimate of priority queue size.
	 */
	public PEC(int size_estimate) {
		events=new PriorityQueue<Event>(size_estimate, new EventComparatorByTime());
	}
	
	/**
	 * Default constructor of Pending Event Container (PEC) with estimate of 1000 events.
	 */
	public PEC() {
		this(1000);
	}
	
	@Override
	public void addEvent(Event e) {
		if(e==null) return;
		else events.add(e);
	}
	
	@Override
	public void addEvent(List<Event> e) {
		if(e==null) return;
		else {
			for(Iterator<Event> i=e.iterator();i.hasNext();) {
				events.add(i.next());
			}
		}
		
	}

	@Override
	public void removeEvent(Event e) {
		events.remove(e);
		
	}

	
	@Override
	public boolean isempty() {
		return events.isEmpty();
	}

	
	@Override
	public int numberEvents() {
		return events.size();
	}
	
	@Override
	public Event nextEvent() {
		//System.out.println("TIME:"+events.peek().time());
		return events.poll();
	}
	
	

}
