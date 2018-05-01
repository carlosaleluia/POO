package especific;

import simulator.Event;

import java.util.LinkedList;
import java.util.List;

public class Reproduction extends Event{

	Individual individual;
	static double[] reproduction_param;
	
	List<Individual> individual_list;
	SegmentList grid;
	
	public Reproduction(Individual i, List<Individual> l, SegmentList g) {
		individual=i;
		individual_list=l;
		grid=g;
		//time!!
	}

	@Override
	protected Event[] doEvent() {
		Individual newborn=null;
		
		individual_list.add(newborn);
		Event[] next_events=new Event[4];
		next_events[0]=new Reproduction(individual,individual_list,grid);
		next_events[1]=new Death(newborn,individual_list);
		next_events[2]=new Move(newborn,grid);
		next_events[3]=new Reproduction(newborn,individual_list,grid);
		return next_events;
	}

}
