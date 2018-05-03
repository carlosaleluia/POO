package pathfinder;

import simulator.Event;

import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Reproduction extends Event{

	Individual individual;
	static double[] reproduction_param;
	
	LinkedList<Individual> individual_list;
	Grid grid;
	
	public Reproduction(Individual i, LinkedList<Individual> l, Grid g, double[] par, double present) {
		individual=i;
		individual_list=l;
		grid=g;
		individual.reproduction_event=this;
		time=individual.simulator.Generator(par)+present;
	}
	public Reproduction(Individual i, LinkedList<Individual> l, Grid g, double[] par) {
		this(i,l,g,par,0);
	}

	@Override
	protected List<Event> doEvent() {		
		int length_new=(int)Math.ceil(individual.list_segments.size()*(0.9+0.1*individual.comfort));
		LinkedList<Segment> newborn_list=new LinkedList<Segment>();
		int k=0;
		/*System.out.println("1:");
		System.out.println((int)Math.ceil(individual.list_segments.size()*(0.9+0.1*individual.comfort)));
		System.out.println(individual.comfort);
		System.out.println(individual.list_segments.size());*/
		for(Iterator<Segment> i=individual.list_segments.iterator();k<length_new;++k) {
			newborn_list.add(i.next());
		}
		Individual newborn=new Individual(individual.simulator, newborn_list);
		individual_list.add(newborn);
		List<Event> next_events=new ArrayList<Event>(4);
		Event aux=new Reproduction(individual,individual_list,grid,individual.simulator.reproduction_param,time);
		if(aux.time()<individual.death_event.time() && aux.time()<individual.simulator.GetFinalInstant())
			next_events.add(aux);
		aux=new Death(newborn,individual_list,individual.simulator.death_param,time);
		if(aux.time()<individual.simulator.GetFinalInstant())
			next_events.add(aux);
		aux=new Reproduction(newborn,individual_list,grid,individual.simulator.reproduction_param,time);
		if(aux.time()<newborn.death_event.time() && aux.time()<individual.simulator.GetFinalInstant())
			next_events.add(aux);
		aux=new Move(newborn,grid,individual.simulator.move_param,time);
		if(aux.time()<newborn.death_event.time() && aux.time()<individual.simulator.GetFinalInstant())
			next_events.add(aux);
		return next_events;
	}

}
