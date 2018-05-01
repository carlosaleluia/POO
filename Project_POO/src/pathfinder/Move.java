package pathfinder;

import simulator.Event;

import java.util.LinkedList;
import java.util.ArrayList;
import java.util.List;

public class Move extends Event{

	Individual individual;
	static double[] move_param;
	
	Grid grid;
	
	public Move(Individual i, Grid g, double[] par) {
		individual=i;
		grid=g;
		individual.move_event=this;
		time=individual.simulator.Generator(par);
	}
	
	@Override
	protected List<Event> doEvent() {
		Point position=individual.list_segments.peekLast().end;
		
		
		List<Event> next_events=new ArrayList<Event>(1);
		Event aux=new Move(individual,grid,individual.simulator.move_param);
		if(aux.time()<individual.death_event.time() && aux.time()<individual.simulator.GetFinalInstant())
			next_events.add(aux);
		return next_events;
	}

}
