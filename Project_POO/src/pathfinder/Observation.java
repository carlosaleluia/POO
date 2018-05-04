package pathfinder;

import simulator.Event;

import java.util.LinkedList;
import java.util.ArrayList;
import java.util.List;

public class Observation extends Event{
	
	int observation_number;
	MainSimulator simulator;
	LinkedList<Individual> individual_list;

	
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
		//System.out.println("OBSER");
		individual_list.sort(new IndividualComparatorByComfort());
		IndividualComparatorByPath comp=new IndividualComparatorByPath();
		if(!individual_list.isEmpty()) {
			if(comp.compare(individual_list.getFirst(),simulator.the_best)>0) {
				simulator.the_best.copyIndividual(individual_list.getFirst());
			}
		}
		
		String y_n;
		if(simulator.final_point_hit) y_n="yes";
		else y_n="no";
		double c_c;
		if(simulator.the_best.has_reached) c_c=simulator.the_best.current_cost;
		else c_c=simulator.the_best.comfort;
		
		System.out.println("Observation "+observation_number);
		System.out.println("\tPresent instant:                 "+time);
		System.out.println("\tNumber of realized events:       "+simulator.GetNbEventsDone());
		System.out.println("\tPopulation size:                 "+individual_list.size());
		System.out.println("\tFinal point has been hit:        "+y_n);
		System.out.println("\tPath of the best fit individual: "+simulator.the_best.printpath());
		System.out.println("\tCost/Comfort:                    "+c_c);
		List<Event> next_events=new ArrayList<Event>(1);
		if(observation_number<19) {
			next_events.add(new Observation(observation_number+1,simulator,individual_list));
		}
		return next_events;
	}

}
