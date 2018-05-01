package pathfinder;

import simulator.Event;

import java.util.LinkedList;
import java.util.List;

public class Death extends Event{

	Individual individual;
	static double[] death_param;
	
	LinkedList<Individual> individual_list;
	
	public Death(Individual i, LinkedList<Individual> l, double[] par) {
		individual=i;
		individual_list=l;
		individual.death_event=this;
		time=individual.simulator.Generator(par);
	}
	
	@Override
	protected List<Event> doEvent() {
		IndividualComparatorByComfort comp=new IndividualComparatorByComfort();
		if(comp.compare(individual.simulator.the_best, individual)>0) {
			//CLONE
			//individual.simulator.the_best=individual;
		}
		individual_list.remove(individual);
		return null;
	}

}
