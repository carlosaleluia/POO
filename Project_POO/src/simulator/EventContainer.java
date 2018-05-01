package simulator;

public interface EventContainer {

	public void addEvent(Event e);
	public void removeEvent(Event e);
	public boolean isempty();
	public Event nextEvent();
}
