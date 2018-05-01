package pathfinder;

import simulator.Event;

import java.util.LinkedList;
import java.util.ArrayList;
import java.util.List;

public class Reproduction extends Event{

	Individual individual;
	static double[] reproduction_param;
	
	LinkedList<Individual> individual_list;
	Grid grid;
	
	public Reproduction(Individual i, LinkedList<Individual> l, Grid g, double[] par) {
		individual=i;
		individual_list=l;
		grid=g;
		time=individual.simulator.Generator(par);
	}

	@Override
	protected List<Event> doEvent() {		
		
		//Individual(float c, int c_c, int c_n, Point d, Point current)
		Individual newborn=null;
		
		individual_list.add(newborn);
		List<Event> next_events=new ArrayList<Event>(4);
		Event aux=new Reproduction(individual,individual_list,grid,individual.simulator.reproduction_param);
		if(aux.time()<individual.death_event.time() && aux.time()<individual.simulator.GetFinalInstant())
			next_events.add(aux);
		aux=new Death(newborn,individual_list,individual.simulator.death_param);
		if(aux.time()<individual.simulator.GetFinalInstant())
			next_events.add(aux);
		aux=new Reproduction(newborn,individual_list,grid,individual.simulator.reproduction_param);
		if(aux.time()<newborn.death_event.time() && aux.time()<individual.simulator.GetFinalInstant())
			next_events.add(aux);
		aux=new Move(newborn,grid,individual.simulator.move_param);
		if(aux.time()<newborn.death_event.time() && aux.time()<individual.simulator.GetFinalInstant())
			next_events.add(aux);
		return next_events;
	}

}
