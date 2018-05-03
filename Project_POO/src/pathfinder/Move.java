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
		Point position=individual.current;
		LinkedList<Segment> seglist=grid.ValidSegments(position);
		Random random=new Random();
		//System.out.println("Point:"+position.x+","+position.y);
		//System.out.println("POSS SIZE:"+seglist.size());
		Segment new_segment=seglist.get(random.nextInt(seglist.size()));
		individual.checkcycles(new_segment);
		individual.updatecomfort();
		IndividualComparatorByPath comp=new IndividualComparatorByPath();
		if(comp.compare(individual,individual.simulator.the_best)>0) {
			individual.simulator.the_best.copyIndividual(individual);
		}
		individual.current=individual.list_segments.peekLast().end;
		List<Event> next_events=new ArrayList<Event>(1);
		Event aux=new Move(individual,grid,individual.simulator.move_param);
		if(aux.time()<individual.death_event.time() && aux.time()<individual.simulator.GetFinalInstant())
			next_events.add(aux);
		return next_events;
	}

}
