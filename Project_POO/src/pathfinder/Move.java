package pathfinder;

import simulator.Event;

import java.util.LinkedList;
import java.util.ArrayList;
import java.util.List;

import generator.GenerateNumber;
import generator.UnifRandom;


/**
 * This class extends Event, providing a specific implementation of {@link simulator.Event#doEvent()}: moving a certain Individual in the provided grid.
 * @see simulator.Event
 * @see pathfinder.Individual
 * @see pathfinder.Grid
 *
 */
public class Move extends Event{

	/**
	 * Individual with this Move Event.
	 */
	Individual individual;
	
	//check if reached final point, if so compare with THE BEST, if better than the BEST update THE BEST
	
	/**
	 * Grid where individual will move.
	 */
	Grid grid;
	
	/**
	 * Constructor used during simulation, each time an existing individual moves or a new one is born. <p>
	 * Move time is generated according to simulator generator and parameters given.
	 * @param i individual to link this move event to.
	 * @param g grid where individual will move.
	 * @param par move parameters to be used in generating event time.
	 * @param present time to be added to generated time.
	 */
	public Move(Individual i, Grid g, double[] par, double present) {
		individual=i;
		grid=g;
		individual.move_event=this;
		time=individual.simulator.Generator(par,0)+present;
	}
	/**
	 * Constructor used in the beginning of the simulation.<p>
	 * Move time is generated according to simulator generator and parameters given.
	 * @param i individual to link this move event to.
	 * @param g grid where individual will move.
	 * @param par move parameters to be used in generating event time.
	 */
	public Move(Individual i, Grid g, double[] par) {
		this(i,g,par,0);
	}
	
	/** 
	 * This method moves the individual in the grid. First, sees in how many directions it can go and effectively
	 * where does the individual move (by generating a uniform random). Then, it checks whether the given 
	 * segment closes a cycle or not, adding it to the Individual path if it doesn't. <p>
	 * After it moves, it checks if the individual has become the overall best, comparing him, by Path,
	 * with {@link MainSimulator#the_best}. If the individual has become better, copies it.<p>
	 * Lastly, it generates the individual next move and returns it if its time is lower than 
	 * individual death time and simulation final instant.
	 * @see simulator.Event#doEvent()
	 * @see generator.UnifRandom#Generate(double[])
	 * @see pathfinder.Individual#checkcycles(Segment)
	 * @see pathfinder.Individual#copyIndividual(Individual)
	 * @see pathfinder.IndividualComparatorByPath#compare(Individual,Individual)
	 * @return next move of Individual if its time is lower than 
	 * individual death time and simulation final instant.
	 */
	@Override
	public List<Event> doEvent() {
		Point position=individual.current;
		LinkedList<Segment> seglist=grid.ValidSegments(position);
		GenerateNumber unif=new UnifRandom();
		double[] range=new double[2];
		range[0]=0;
		range[1]=1;
		double m = unif.Generate(range);
		Segment new_segment;
		for(int i=0;i<seglist.size();i++) {
			if (m<(double)(1+i)/seglist.size()) {
				new_segment=seglist.get(i);
				individual.checkcycles(new_segment);
				break;
			}
		}
		individual.updatecomfort();
		
		if(individual.list_segments.isEmpty()) {
			individual.current = individual.simulator.initial;
		}
		else {
			individual.current=individual.list_segments.peekLast().end;	
			if(individual.list_segments.peekLast().end.equals(individual.simulator.destination)) {
				individual.has_reached= true;
				individual.simulator.final_point_hit = true;
			}
		}	

		IndividualComparatorByPath comp=new IndividualComparatorByPath();
		if(comp.compare(individual,individual.simulator.the_best)>0) {
			individual.simulator.the_best.copyIndividual(individual);
		}

		List<Event> next_events=new ArrayList<Event>(1);
		//double[] aux1;
		//aux1 = new double[1];
		//aux1[0] = individual.move_p;
		Event aux=new Move(individual,grid,individual.move_p,time);
		if(aux.time()<individual.death_event.time() && aux.time()<individual.simulator.GetFinalInstant()) {
			next_events.add(aux);
		}
		return next_events;
	}

}
