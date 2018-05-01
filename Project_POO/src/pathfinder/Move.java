package especific;

import simulator.Event;

import java.util.LinkedList;
import java.util.List;

public class Move extends Event{

	Individual individual;
	static double[] move_param;
	
	SegmentList grid;
	
	public Move(Individual i, SegmentList g) {
		individual=i;
		grid=g;
		//time!!
	}
	
	@Override
	protected Event[] doEvent() {
		Point position=individual.path.peekLast().end;
		LinkedList<Segment> valid_segments=grid.ValidSegments(position);
		int k=valid_segments.size();
		
		
		Event[] next_events=new Event[1];
		next_events[0]=new Move(individual,grid);
		return next_events;
	}

}
