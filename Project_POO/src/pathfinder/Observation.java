package pathfinder;

import simulator.Event;

import java.util.LinkedList;
import java.util.ArrayList;
import java.util.List;

public class Observation extends Event{
	
	int observation_number;
	MainSimulator simulator;
	LinkedList<Individual> individual_list;
	Individual the_best;

	
	public Observation(int nb, MainSimulator s, LinkedList<Individual> l) {
		observation_number=nb;
		simulator=s;
		individual_list=l;
		time=(double)(observation_number*simulator.GetFinalInstant()/20);
	}
	
	public Observation(MainSimulator s, LinkedList<Individual> l) {
		this(1,s,l);
	}
	
	@Override
	protected List<Event> doEvent() {
		individual_list.sort(new IndividualComparatorByComfort());
		IndividualComparatorByComfort comp=new IndividualComparatorByComfort();
		if(comp.compare(simulator.the_best, individual_list.getFirst())>0) {
			the_best=individual_list.getFirst();
		}
		else the_best=simulator.the_best;
		
		String y_n;
		if(simulator.final_point_hit) y_n="yes";
		else y_n="no";
		
		System.out.println("Observation "+observation_number);
		System.out.println("\tPresent instant:                 "+time);
		System.out.println("\tNumber of realized events:       "+simulator.GetNbEventsDone());
		System.out.println("\tPopulation size:                 "+individual_list.size());
		System.out.println("\tFinal point has been hit:        "+y_n);
		System.out.println("\tPath of the best fit individual: "+the_best.printpath());
		System.out.println("\tCost/Comfort:                    "+the_best.current_cost+"/"+the_best.comfort);
		List<Event> next_events=new ArrayList<Event>(1);
		next_events.add(new Observation(observation_number+1,simulator,individual_list));
		return next_events;
	}

}
