package simulator;

import java.util.List;

public interface EventContainer {

	public void addEvent(Event e);
	public void addEvent(List<Event> e);
	public void removeEvent(Event e);
	public boolean isempty();
	public Event nextEvent();
}
