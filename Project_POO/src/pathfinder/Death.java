package pathfinder;

import simulator.Event;

import java.util.LinkedList;
import java.util.List;

public class Death extends Event{

	Individual individual;
	static double[] death_param;
	
	List<Individual> individual_list;
	
	public Death(Individual i, List<Individual> l) {
		individual=i;
		individual_list=l;
		//time!!
	}
	
	@Override
	protected List<Event> doEvent() {
		individual_list.remove(individual);
		return null;
	}

}
