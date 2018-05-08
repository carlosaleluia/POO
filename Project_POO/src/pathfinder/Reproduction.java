package pathfinder;

import simulator.Event;

import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Reproduction extends Event{

	/**
	 * Individual with this Reproduction Event.
	 */
	Individual individual;
	/**
	 * List of alive individuals.
	 */
	LinkedList<Individual> individual_list;
	/**
	 * Grid where individuals move.
	 */
	Grid grid;
	
	/**
	 * Constructor used during simulation, each time an existing individual reproduces. <p>
	 * Reproduction time is generated according to simulator generator and parameters given.
	 * @param i individual to link this move event to.
	 * @param l list of alive individuals.
	 * @param g grid where individual will move.
	 * @param par reproduction parameters to be used in generating event time.
	 * @param present time to be added to generated time.
	 */
	public Reproduction(Individual i, LinkedList<Individual> l, Grid g, double[] par, double present) {
		individual=i;
		individual_list=l;
		grid=g;
		individual.reproduction_event=this;
		time=individual.simulator.Generator(par)+present;
	}
	/**
	 * Constructor used in the beginning of the simulation.<p>
	 * Reproduction time is generated according to simulator generator and parameters given.
	 * @param i individual to link this move event to.
	 * @param l list of alive individuals.
	 * @param g grid where individual will move.
	 * @param par reproduction parameters to be used in generating event time.
	 */
	public Reproduction(Individual i, LinkedList<Individual> l, Grid g, double[] par) {
		this(i,l,g,par,0);
	}

	/** This method reproduces the individual.<p>
	 * First, it sees the percentage of parent path that newborn will inherit. Then, it creates 
	 * an Individual with that prefix, adds him to the alive individual list and generates death, first reproduction and movement of newborn.
	 * Also, generates next reproduction of parent.
	 * @see simulator.Event#doEvent()
	 * @return next reproduction of existing Individual and the death, reproduction and move of 
	 * newborn, if Event time is lower than individual death time and simulation final instant.
	 */
	@Override
	protected List<Event> doEvent() {		
		int length_new=(int)Math.ceil(individual.list_segments.size()*(0.9+0.1*individual.comfort));
		LinkedList<Segment> newborn_list=new LinkedList<Segment>();
		int k=0;
		if(!individual.list_segments.isEmpty()) {		
			Iterator<Segment> i=individual.list_segments.iterator();
			while(i.hasNext()) {				
				newborn_list.add(i.next());
				if(k == length_new) {
					break;
				}
				k++;
			}
		}
		Individual newborn=new Individual(individual.simulator, newborn_list);
		
		individual_list.add(newborn);
		List<Event> next_events=new ArrayList<Event>(4);
		double[] aux1 = new double[1];
		aux1[0] = individual.reproduce_p;
		Event aux=new Reproduction(individual,individual_list,grid,aux1,time);
		if(aux.time()<individual.death_event.time() && aux.time()<individual.simulator.GetFinalInstant())
			next_events.add(aux);
		aux1[0] = newborn.death_p;
		aux=new Death(newborn,individual_list,aux1,time);
		if(aux.time()<individual.simulator.GetFinalInstant())
			next_events.add(aux);
		aux1[0] = newborn.reproduce_p;
		aux=new Reproduction(newborn,individual_list,grid,aux1,time);
		if(aux.time()<newborn.death_event.time() && aux.time()<individual.simulator.GetFinalInstant())
			next_events.add(aux);
		aux1[0] = newborn.move_p;
		aux=new Move(newborn,grid,aux1,time);
		if(aux.time()<newborn.death_event.time() && aux.time()<individual.simulator.GetFinalInstant())
			next_events.add(aux);
		return next_events;
	}

}
