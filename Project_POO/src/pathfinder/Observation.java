package pathfinder;

import simulator.Event;

import java.util.LinkedList;
import java.util.List;

public class Observation extends Event{
	
	int observation_number;
	MainSimulator simulator;
	List<Individual> individual_list;
	Individual the_best;

	
	public Observation(int nb, MainSimulator s, List<Individual> l) {
		observation_number=nb;
		simulator=s;
		individual_list=l;
	}
	
	@Override
	protected List<Event> doEvent() {
		individual_list.sort(new IndividualComparatorByComfort());
		if(!individual_list.isEmpty()) {
			
		}
		return null;
	}

}
