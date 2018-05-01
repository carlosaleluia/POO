package pathfinder;

import simulator.Event;

import java.util.LinkedList;
import java.util.List;

public class Reproduction extends Event{

	Individual individual;
	static double[] reproduction_param;
	
	LinkedList<Individual> individual_list;
	Grid grid;
	
	public Reproduction(Individual i, LinkedList<Individual> l, Grid g) {
		individual=i;
		individual_list=l;
		grid=g;
	}

	@Override
	protected List<Event> doEvent() {
		Individual newborn=null;
		
		individual_list.add(newborn);
		Event[] next_events=new Event[4];
		next_events[0]=new Reproduction(individual,individual_list,grid);
		next_events[1]=new Death(newborn,individual_list);
		next_events[2]=new Move(newborn,grid);
		next_events[3]=new Reproduction(newborn,individual_list,grid);
		return null;
	}

}
