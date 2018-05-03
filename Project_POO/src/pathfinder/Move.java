package pathfinder;

import simulator.Event;

import java.util.LinkedList;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Move extends Event{

	Individual individual;
	static double[] move_param;
	
	//check if reached final point, if so compare with THE BEST, if better than the BEST update THE BEST
	
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
		LinkedList<Segment> seglist=grid.ValidSegments(position);
		Random random=new Random();
		Segment new_segment=seglist.get(random.nextInt(seglist.size()));
		individual.list_segments.add(new_segment);
		individual.updatecomfort();
		IndividualComparatorByComfort comp=new IndividualComparatorByComfort();
		if(comp.compare(individual.simulator.the_best, individual)>0) {
			//CLONE
			//individual.simulator.the_best=individual;
		}
		
		
		List<Event> next_events=new ArrayList<Event>(1);
		Event aux=new Move(individual,grid,individual.simulator.move_param);
		if(aux.time()<individual.death_event.time() && aux.time()<individual.simulator.GetFinalInstant())
			next_events.add(aux);
		return next_events;
	}

}
