package pathfinder;

import simulator.Event;

import java.util.LinkedList;
import java.util.ArrayList;
import java.util.List;

/**
 * This class extends Event, providing a specific implementation of {@link simulator.Event#doEvent()}: making an observation about present instant.<p>
 * Prints information about Individual with best path so far, adding other info.
 * @see simulator.Event
 * @see pathfinder.MainSimulator
 * @see pathfinder.Individual
 *
 */
public class Observation extends Event{
	
	/**
	 * Observation number (1-19)
	 */
	int observation_number;
	/**
	 * MainSimulator where simulation is being run.
	 */
	MainSimulator simulator;
	/**
	 * List of alive individuals.
	 */
	LinkedList<Individual> individual_list;

	
	/**
	 * Constructor of next observation during simulation.
	 * @param nb observation number
	 * @param s MainSimulator where simulation is being run.
	 * @param l list of alive individuals.
	 */
	public Observation(int nb, MainSimulator s, LinkedList<Individual> l) {
		observation_number=nb;
		simulator=s;
		individual_list=l;
		time=(double)(observation_number*simulator.GetFinalInstant()/20);
	}
	
	/**
	 * Constructor of first observation.
	 * @param s MainSimulator where simulation is being run.
	 * @param l list of alive individuals.
	 */
	public Observation(MainSimulator s, LinkedList<Individual> l) {
		this(1,s,l);
	}
	
	/** 
	 * This method does the observation.
	 * First, it sorts the alive individual list by comfort, comparing the one with greatest
	 * comfort with the best individual recorded in MainSimulator by Path. If the alive individual is better,
	 * then it is copied to the {@link MainSimulator#the_best}.<p>
	 * Afterwards, current time information of simulated is printed according to specifications.  
	 * @see simulator.Event#doEvent()
	 * @see pathfinder.Individual#copyIndividual(Individual)
	 * @return next observation
	 */
	@Override
	public List<Event> doEvent() {
		individual_list.sort(new IndividualComparatorByComfort());
		IndividualComparatorByPath comp=new IndividualComparatorByPath();
		if(!individual_list.isEmpty()) {
			if(comp.compare(individual_list.getFirst(),simulator.the_best)>0) {
				simulator.the_best.copyIndividual(individual_list.getFirst());
			}
		}
		
		String y_n, spec;
		if(simulator.final_point_hit) { y_n="yes"; spec = "Cost:";}
		else {y_n="no"; spec = "Comfort:";}
		double c_c;
		if(simulator.the_best.has_reached) c_c=simulator.the_best.current_cost;
		else c_c=simulator.the_best.comfort;
		
		System.out.println("Observation "+observation_number);
		System.out.println("\tPresent instant:                 "+time);
		System.out.println("\tNumber of realized events:       "+simulator.GetNbEventsDone());
		System.out.println("\tPopulation size:                 "+individual_list.size());
		System.out.println("\tFinal point has been hit:        "+y_n);
		System.out.println("\tPath of the best fit individual: "+simulator.the_best.printpath());
		System.out.println("\t"+spec+"                           "+c_c);
		List<Event> next_events=new ArrayList<Event>(1);
		next_events.add(new Observation(observation_number+1,simulator,individual_list));
		return next_events;
	}

}
