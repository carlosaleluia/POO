package simulator;

import java.util.PriorityQueue;

public class PEC implements EventContainer{

	PriorityQueue<Event> events;
	
	public PEC() {
		events=new PriorityQueue<Event>(1000, new EventComparatorByTime());
	}
	
	@Override
	public void addEvent(Event e) {
		events.add(e);
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
	public Event nextEvent() {
		return events.poll();
	}
	
	

}
