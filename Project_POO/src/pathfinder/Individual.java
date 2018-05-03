package pathfinder;
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
	
	Individual(MainSimulator m, LinkedList<Segment> list_segments) {		
		this.simulator = m;
		this.list_segments = list_segments;
		calculatecostnbsegments();
		
		if(list_segments.isEmpty()) {
			current = new Point(simulator.initial.x,simulator.initial.y);
			this.has_reached = false;
		}
		else {
			current = new Point(list_segments.getLast().end.x,list_segments.getLast().end.y);
		}
		updatecomfort();		
	}
	
	
	void updatecomfort(){
		double a,b;
		a = Math.pow(simulator.comfortsens,(1 - ((current_cost - current_nbsegments + 2)/((simulator.comfortsens - 1)*current_nbsegments + 3))));
		b = Math.pow(simulator.comfortsens,(1-(this.findpath())/ (simulator.n + simulator.m + 1)));
		this.comfort = (float) (a* b);
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
		
		ListIterator<Segment> listIt = list_segments.listIterator(list_segments.size());
		boolean cycle = false;
				
		while(listIt.hasNext())
		{
			Segment temp = listIt.next();			
			if(temp.end.equals(nextseg.end)) {
				cycle = true;							
			}
			if(cycle == true) {
				current_cost = current_cost - temp.cost;
				current_nbsegments--;
				listIt.remove();
			}			
		}				
		if(cycle == false) {			
			list_segments.add(nextseg);
			current_cost = current_cost + nextseg.cost;
			current_nbsegments++;			
		}
				
		return cycle;
	}
	
	void calculatecostnbsegments() {		
		ListIterator<Segment> listIt = list_segments.listIterator(list_segments.size());				
		while(listIt.hasNext())
		{
			Segment temp = listIt.next();						
			current_cost = current_cost + temp.cost;
			current_nbsegments++;
		}										
	}

String printpath() {
	ListIterator<Segment> listIt = list_segments.listIterator(list_segments.size());
	String result = "";
	Segment temp = null;
	while(listIt.hasNext())
	{
		temp = listIt.next();	
		result = result + "("+temp.start.x+","+temp.start.y+")"+",";
	}
	if(temp != null) {
		result = result + "("+temp.end.x+","+temp.end.y+")";
	}
	return result;
}


@Override
public String toString() {
	return "Individual [comfort=" + comfort + ", current_cost=" + current_cost + ", current_nbsegments="
			+ current_nbsegments + ", current=" + current + ", list_segments=" + list_segments  + "]";
}

	
	



}
