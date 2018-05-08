package pathfinder;

import simulator.Event;

import java.util.LinkedList;
import java.util.List;

public class Death extends Event{

	/**
	 * Individual with this Death Event.
	 */
	Individual individual;
	
	/**
	 * List of alive individuals.
	 */
	LinkedList<Individual> individual_list;
	
	/**
	 * Constructor used during simulation, each time a new individual is born. <p>
	 * Death time is generated according to simulator generator and parameters given.
	 * @param i individual to link this death event to.
	 * @param l list of alive individuals.
	 * @param par death parameters to be used in generating event time.
	 * @param present time to be added to generated time.
	 */
	public Death(Individual i, LinkedList<Individual> l, double[] par, double present) {
		individual=i;
		individual_list=l;
		individual.death_event=this;
		time=individual.simulator.Generator(par)+present;
	}
	/**
	 * Constructor used in the beginning of the simulation.<p>
	 * Death time is generated according to simulator generator and parameters given.
	 * @param i individual to link this death event to.
	 * @param l list of alive individuals.
	 * @param par death parameters to be used in generating event time.
	 */
	public Death(Individual i, LinkedList<Individual> l, double[] par) {
		this(i,l,par,0);
	}
	
	/** 
	 * Removes dead individual from alive individual list.
	 * @see simulator.Event#doEvent()
	 * @return null (no Event happens as consequence)
	 */
	@Override
	protected List<Event> doEvent() {
		individual_list.remove(individual);
		return null;
	}

}
