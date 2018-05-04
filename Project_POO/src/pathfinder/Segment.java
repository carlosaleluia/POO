package pathfinder;

class Segment {
	
	Point start;
	Point end;
	final int cost;

	Segment(Point start,Point end, int c) {
		 this.start =start;
		 this.end = end;
		 this.cost = c;		
	}
	
	int getCost() {
		return this.cost;
	}
	
	public String toString() {
		return start.toString()+end.toString();
	}

	
}
