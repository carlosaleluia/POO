package simulator;

import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

public class PEC implements EventContainer{

	PriorityQueue<Event> events;
	
	public PEC(int size_estimate) {
		events=new PriorityQueue<Event>(size_estimate, new EventComparatorByTime());
	}
	
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
