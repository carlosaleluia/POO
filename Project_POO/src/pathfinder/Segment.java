package pathfinder;

/**
 * This class represents a segment between two {@link pathfinder.Point}.<p>
 *  Each segment will be added to Individual path when he crosses the segment in 
 *  {@link Individual#checkcycles(Segment)}.
 *
 */
class Segment {
	
	/**
	 * Beginning of segment
	 */
	Point start;
	/**
	 * End of segment
	 */
	Point end;
	/**
	 * Segment cost
	 */
	final int cost;

	Segment(Point start,Point end, int c) {
		 this.start =start;
		 this.end = end;
		 this.cost = c;		
	}
	
	/**
	 * Getter for segment cost
	 * @return segment cost
	 */
	int getCost() {
		return this.cost;
	}
	
	public String toString() {
		return start.toString()+end.toString();
	}

	
}
