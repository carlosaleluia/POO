package pathfinder;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;



/**
 * This class stores everything that represents each individual, and implements methods needed to
 * update its comfort, number of segments traversed, cost, etc.<p>
 * Also has more complex methods that check if its path contains a cycle, 
 * {@link pathfinder.Individual#checkcycles(Segment)}, or that copy Individual 
 * content to another one, {@link pathfinder.Individual#copyIndividual(Individual)},to update the best recorded in simulator.
 *
 */
class Individual {
	
	/**
	 * Associated MainSimulator.
	 */
	MainSimulator simulator;
	/**
	 * Individual comfort.
	 */
	float comfort;
	/**
	 * Sum cost of traversed segments.
	 */
	int current_cost = 0;
	/**
	 * Number of traversed segments so far.
	 */
	int current_nbsegments = 0;
	/**
	 * Current Point in grid.
	 */
	Point current;
	/**
	 * List of traversed segments.
	 */
	LinkedList<Segment> list_segments;
	/**
	 * Its associated Death Event.
	 */
	Death death_event;
	/**
	 * Its associated Reproduction Event.
	 */
	Reproduction reproduction_event;
	/**
	 * Its associated Move Event.
	 */
	Move move_event;
	/**
	 * Whether or not the Individual has reached final point.
	 */
	boolean has_reached;
	
	double[] death_p;
	double[] move_p;
	double[] reproduce_p;
	
	/**
	 * This method is the constructor of the Individual Class. <p>
	 * The list of segments is attached to the segment, and the cost and number of segments are updated.
	 * If the list is empty the current point of the individual is defined as the initial point of the simulation, otherwise is defined 
	 * as the end point of the last segment of the list.
	 * Finally, the comfort is updated.
	 * @param  m   MainSimulator attached to the individual
	 * @param list_segments Linked List of Segments to attach to the individual
	 * @see MainSimulator
	 * @see LinkedList
	 */
	public Individual(MainSimulator m, LinkedList<Segment> list_segments) {		
		this.simulator = m;
		this.list_segments = list_segments;
		calculatecostnbsegments();
		death_p=new double[1];
		move_p=new double[1];
		reproduce_p=new double[1];
		
		if(list_segments.isEmpty()) {current = new Point(simulator.initial.x,simulator.initial.y);}
		else {current = new Point(list_segments.getLast().end.x,list_segments.getLast().end.y);}
		updatecomfort();		
	}
	
	
	/**
	 * This method calculates the comfort of the Individual according to the provided equation. <p>
	 * The death, move and reproduce parameters are also updated.
	 * @see Move
	 * @see Reproduction
	 * @see Death 
	 */
	void updatecomfort(){
		double a,b;
		a = Math.pow(1 - (1.0*(current_cost - current_nbsegments + 2)/((simulator.max_cost_edge - 1)*current_nbsegments + 3)),simulator.comfortsens);
		b = Math.pow(1-(this.findpath()/ (simulator.n + simulator.m + 1)),simulator.comfortsens);
		this.comfort = (float) (a*b);
		if(this.comfort < 0) {this.comfort = 0;}
		if(this.comfort == 1.0) {this.comfort = (float) 0.9999;}		
		this.death_p[0] = (1 - Math. log(1 - this.comfort))*this.simulator.param_death;
		this.move_p[0] = (1 - Math. log(this.comfort))*this.simulator.param_move;
		this.reproduce_p[0] = (1 - Math. log(this.comfort))*this.simulator.param_reproduce;
	}
	
	/**
	 * This method calculates the distance between the current point and the destination point, desregarding special cost zones
	 * and obstacles.
	 * @return Calculated distance
	 */
	double findpath() {
	  double dist = 0;
	  if(this.current.equals(this.simulator.destination)) {return 0;}
	  else {
		  dist = Math.abs(current.x-simulator.destination.x) + Math.abs(current.y-simulator.destination.y) ;
		  return dist;
	  }
	}	
	
	
	/**
	 * This method receives a segment and checks if there is a cycle in the path. <p>
	 * 	Checks if there is a cycle in the path, if there isn't insert the segment and update comfort, number of segments and total cost.
	 *  If there is a cycle, delete every segment from the first instance of the segment until the end of the list and update
	 *  comfort, number of segments and total cost. <p>
	 *  If the cycle is done to the initial point of the simulation, delete every segment from list.
	 * @param nextseg Possible next segment in the path of the individual
	 * @return Boolean that is true in case of a cycle in the path, false otherwise.
	 * @see LinkedList
	 * @see Segment
	 */
	 boolean checkcycles(Segment nextseg) {		
		
		ListIterator<Segment> listIt = list_segments.listIterator(0);
		boolean cycle = false;
				
		while(listIt.hasNext())
		{
			Segment temp = listIt.next();			
			if(temp.end.equals(nextseg.end)) {
				cycle = true;
				continue;
			}
			if(cycle == true) {	listIt.remove();}			
		}				
		if(cycle == false) {this.list_segments.add(nextseg);}
		if (nextseg.end.equals(this.simulator.initial)) {
			this.list_segments.removeAll(list_segments);		
		}
		this.current = nextseg.end;		
		calculatecostnbsegments();
		return cycle;
	}
		
	/**
	 * This method calculates the cost and number of segments of a path. <p>
	 * 	Using a LinkedList iterator, goes through all of the segments in the individual's list and increments the number of segments
	 * and updates the cost adding the cost of each segment.
	 * Also checks if the individual has reached the final point and updates the has_reached parameter.
	 * @see LinkedList
	 * @see Segment
	 */
	void calculatecostnbsegments() {		
		ListIterator<Segment> listIt = list_segments.listIterator(0);
		this.current_cost = 0;
		this.current_nbsegments = 0;
		this.has_reached = false;
		while(listIt.hasNext())
		{
			Segment temp = listIt.next();						
			this.current_cost = this.current_cost + temp.cost;
			current_nbsegments++;
			if(temp.end.equals(this.simulator.destination)) {this.has_reached = true;}
		}
	}

	
	/**
	 * This method prints the path of an Individual. <p>
	 * 	Using a LinkedList iterator, adds to a string all the segments.
	 * @return String to be printed
	 * @see LinkedList
	 * @see Segment
	 */
	public String printpath() {
	Iterator<Segment> listIt = list_segments.iterator();
	String result = "{";
	Segment temp = null;
	while(listIt.hasNext())
	{
		temp = listIt.next();	
		result=result.concat(temp.start.toString() + ",");
	}
	result=result.concat(current.toString() + "}"); 
	return result;
}

	/**
	 * This method is an override of the toString method() to print the contents of the individual.
	 * @return  Individual information
	 */
@Override
public String toString() {
	return "Individual [comfort=" + comfort + ", current_cost=" + current_cost + ", current_nbsegments="
			+ current_nbsegments + ", current=" + current + ", list_segments=" + list_segments  + "]";
}

	
/**
 * This method copies the contents of an individual i to this individual.
 * It's used to update the best individual in the simulation.
 * @param i Individual with specifications to be copied
 */
	void copyIndividual(Individual i) {	
		this.current = i.current;
		this.current_cost = i.current_cost;
		this.current_nbsegments = i.current_nbsegments;
		this.list_segments = new LinkedList<Segment>(i.list_segments);
		this.has_reached = i.has_reached;
		this.comfort = i.comfort;				
	}



}
