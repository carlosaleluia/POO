package pathfinder;

import simulator.Event;

import java.util.LinkedList;
import java.util.List;

public class Death extends Event{

	Individual individual;
	static double[] death_param;
	
	LinkedList<Individual> individual_list;
	
	public Death(Individual i, LinkedList<Individual> l, double[] par, double present) {
		individual=i;
		individual_list=l;
		individual.death_event=this;
		time=individual.simulator.Generator(par)+present;
	}
	public Death(Individual i, LinkedList<Individual> l, double[] par) {
		this(i,l,par,0);
	}
	
	@Override
	protected List<Event> doEvent() {
		individual_list.remove(individual);
		return null;
	}

}
