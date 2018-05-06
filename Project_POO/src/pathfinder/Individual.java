package pathfinder;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;



class Individual {
	
	MainSimulator simulator;
	float comfort;
	int current_cost = 0;
	int current_nbsegments = 0;
	Point current;
	LinkedList<Segment> list_segments;
	Death death_event;
	Reproduction reproduction_event;
	Move move_event;
	boolean has_reached;
	
	double death_p;
	double move_p;
	double reproduce_p;
	
	Individual(MainSimulator m, LinkedList<Segment> list_segments) {		
		this.simulator = m;
		this.list_segments = list_segments;
		calculatecostnbsegments();
		
		if(list_segments.isEmpty()) {
			current = new Point(simulator.initial.x,simulator.initial.y);
		}
		else {
			current = new Point(list_segments.getLast().end.x,list_segments.getLast().end.y);
		}
		updatecomfort();		
	}
	
	
	void updatecomfort(){
		double a,b;
		a = Math.pow(1 - (1.0*(current_cost - current_nbsegments + 2)/((simulator.max_cost_edge - 1)*current_nbsegments + 3)),simulator.comfortsens);
		b = Math.pow(1-(this.findpath()/ (simulator.n + simulator.m + 1)),simulator.comfortsens);
		this.comfort = (float) (a*b);
		if(this.comfort < 0) {
			this.comfort = 0;
		}		
		this.death_p = (1 - Math. log(1 - this.comfort))*this.simulator.param_death;
		this.move_p = (1 - Math. log(1 - this.comfort))*this.simulator.param_move;
		this.reproduce_p = (1 - Math. log(1 - this.comfort))*this.simulator.param_reproduce;
	}
	
	
	double findpath() {
	  double dist = 0;
	  if(this.current.equals(this.simulator.destination)) {
		  return 0;
	  }
	  else {
		  dist = Math.abs(current.x-simulator.destination.x) + Math.abs(current.y-simulator.destination.y) ;
		  return dist;
	  }
	}
	
	//Checks if there is a cycle, if there isn't insert and update comfort and nb_segments and cost
	//If there is a cycle, delete everything and update everything
	public boolean checkcycles(Segment nextseg) {		
		
		ListIterator<Segment> listIt = list_segments.listIterator(0);
		boolean cycle = false;
				
		while(listIt.hasNext())
		{
			Segment temp = listIt.next();			
			if(temp.end.equals(nextseg.end)) {
				cycle = true;
				continue;
			}
			if(cycle == true) {				
				listIt.remove();
			}			
		}				
		if(cycle == false) {			
			this.list_segments.add(nextseg);		
		}
		if (nextseg.end.equals(this.simulator.initial)) {
			this.list_segments.removeAll(list_segments);		
		}
		this.current = nextseg.end;		
		calculatecostnbsegments();
		return cycle;
	}
	
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
			if(temp.end.equals(this.simulator.destination)) {
				this.has_reached = true;
			}
		}
	}

	public String printpath() {
	Iterator<Segment> listIt = list_segments.iterator();
	String result = "{";
	Segment temp = null;
	while(listIt.hasNext())
	{
		temp = listIt.next();	
		result=result.concat(temp.start.toString() + ",");
	}
	/*if(temp == null) {
		result=result.concat(current.toString());
		//result = result + current;
	}*/
	result=result.concat(current.toString() + "}"); 
	/*System.out.println("PRINTING2");
	System.out.println(current.toString());
	System.out.println(result.concat(current.toString()));
	result=result.concat(current.toString());
	System.out.println(result);*/
	return result;
}


@Override
public String toString() {
	return "Individual [comfort=" + comfort + ", current_cost=" + current_cost + ", current_nbsegments="
			+ current_nbsegments + ", current=" + current + ", list_segments=" + list_segments  + "]";
}

	
	void copyIndividual(Individual i) {	
		this.current = i.current;
		this.current_cost = i.current_cost;
		this.current_nbsegments = i.current_nbsegments;
		this.list_segments = new LinkedList<Segment>(i.list_segments);
		this.has_reached = i.has_reached;
		this.comfort = i.comfort;				
	}



}
