package pathfinder;

import simulator.Event;

import java.util.LinkedList;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Move extends Event{

	Individual individual;
	static double[] move_param;
	
	//check if reached final point, if so compare with THE BEST, if better than the BEST update THE BEST
	
	Grid grid;
	
	public Move(Individual i, Grid g, double[] par, double present) {
		individual=i;
		grid=g;
		individual.move_event=this;
		time=individual.simulator.Generator(par)+present;
	}
	public Move(Individual i, Grid g, double[] par) {
		this(i,g,par,0);
	}
	
	@Override
	protected List<Event> doEvent() {
		Point position=individual.current;
		LinkedList<Segment> seglist=grid.ValidSegments(position);
		Random random=new Random();
		Segment new_segment=seglist.get(random.nextInt(seglist.size()));
		//System.out.println("MOVE:");
		//System.out.println("Point:"+position.x+","+position.y);
		//System.out.println("Path before move:"+individual.printpath());
		//System.out.println(new_segment);
		individual.checkcycles(new_segment);
		//System.out.println("Path after move:"+individual.printpath());
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
			/*System.out.println("SWITCHED BEST");
			System.out.println(individual.comfort);
			System.out.println(individual.printpath());
			System.out.println(individual.simulator.the_best.comfort);
			System.out.println(individual.simulator.the_best.printpath());*/
			individual.simulator.the_best.copyIndividual(individual);
		}

		List<Event> next_events=new ArrayList<Event>(1);
		double[] aux1;
		aux1 = new double[2];
		aux1[0] = individual.move_p;
		aux1[1] = 0;
		Event aux=new Move(individual,grid,aux1,time);
		if(aux.time()<individual.death_event.time() && aux.time()<individual.simulator.GetFinalInstant()) {
			next_events.add(aux);
	//		System.out.println("TIME D:"+individual.death_event.time());
	//		System.out.println("TIME M:"+aux.time());
		}
	//	System.out.println("Path: "+ individual.printpath() + "\n");
		return next_events;
	}

}
