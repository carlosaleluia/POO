package pathfinder;

import java.util.LinkedList;

/**
 * This class implements {@link pathfinder.Grid} for a 2D map where Individuals 
 * can move. Thus, it stores information in a 2D Matrix of integers, {@link pathfinder.Grid2D#Map}, 
 * with dimension (number_rows*number_columns)x4. Each row of this matrix represents a Point, and each line
 * is the cost of moving to one direction, counting counter clockwise.
 * @see pathfinder.Grid2D#initializeGrid()
 * @see pathfinder.Grid2D#ValidSegments(Point)
 *
 */
public class Grid2D implements Grid{	
	
	/**
	 * 2D int Matrix where cost for each of movement directions is stored.<p>
	 * Zero cost means opposite site of segment is an obstacle.
	 */
	int[][] Map;
	/**
	 * Read File used to extract information from XML.
	 */
	ReadFile r;
		
	/**
	 * This method is the constructor of the Grid2D Class.
	 * @param  r   ReadFile object with all the informations needed to build the map
	 * @see ReadFile
	 */
	public Grid2D(ReadFile r) {		
		Map = new int[r.colsnb * r.rowsnb][4];	
	    this.r = r;
	}
	
	/**
	 * This method reads the specifications of the map obtained 
	 * from the XML and builds a Map.
	 * A Map is represented by a 2D int vector, in which the first
	 * dimension represents the index of the point on the grid and 
	 * the second dimension represents it's four adjacent segments.
	 * 0 - Left, 1 - Up, 2 - Right, 3- Down 
	 * For example, the Map[13][2] stores the value of the segment on the right
	 * of point 13.
	 * @return      Maximum cost in the grid
	 */
	public int initializeGrid() {
		
		int i = 0, j = 0, t = 0;
		int x=0, y=0;
		int x1 = 0, x2 = 0, y1 = 0, y2 = 0;
		int max_cost=1;
		
		//Initialization
		for(i=0;i<r.colsnb * r.rowsnb;i++) {
			for(j=0;j<4;j++) {
				Map[i][j] = 1;
			}
		}		
		
		//Obstacles
		for(i=0; i<r.nbrobstacles;i++) {
			x = r.obstacles[i].x;
			y = r.obstacles[i].y;
			
			for(j=0;j<4;j++) {
				setValue(x,y,j,0);
			}	
			//Update adjacent points
			if(x-1 > 0) {
				setValue(x-1,y,2,0);
			}
			if(x+1 < r.colsnb + 1) {
				setValue(x+1,y,0,0);
			}
			if(y-1 > 0) {
				setValue(x,y-1,1,0);
			}
			if(y+1 < r.rowsnb + 1) {
				setValue(x,y+1,3,0);
			}
		}
		//Limits of the grid
		for(i=1;i<=r.colsnb;i++) {
			setValue(i,1,3,0);
			setValue(i,r.rowsnb,1,0);			
		}
		for(i=1;i<=r.rowsnb;i++) {
			setValue(1,i,0,0);
			setValue(r.colsnb,i,2,0);
		}
		
		for(t=0;t<r.nbrzones;t++) {
			x1= r.zones[t][0].x;
			y1= r.zones[t][0].y;
			x2= r.zones[t][1].x;
			y2= r.zones[t][1].y;
			if(r.cost_zones[t] > max_cost) {
				max_cost = r.cost_zones[t];
			}
			for(x=1; x<=r.colsnb; x++) {
				for(y=1; y<=r.rowsnb;y++) {			
					if(x-1 >= Math.min(x1, x2) && x <= Math.max(x2, x1)  && (y == y1 || y == y2) ) {
						if(getValue(x,y,0) != 0 && getValue(x,y,0) < r.cost_zones[t]) {
							setValue(x,y,0,r.cost_zones[t]);
						}
					}
					if(x >= Math.min(x1, x2) && x+1 <= Math.max(x2, x1)  && (y == y1 || y == y2 )) {
						if(getValue(x,y,2) !=0 && getValue(x,y,2) < r.cost_zones[t]) {
							setValue(x,y,2,r.cost_zones[t]);
						}																			
					}
					if((x == x1 || x == x2)  && y-1 >= Math.min(y2, y1) && y <= Math.max(y2, y1)) {
						if(getValue(x,y,3)!=0 && getValue(x,y,3) < r.cost_zones[t]) {
							setValue(x,y,3,r.cost_zones[t]);
						}							
					}
					if((x == x1 || x == x2)  && y >= Math.min(y2, y1) && y+1 <= Math.max(y2, y1)) {	
						if(getValue(x,y,1)!=0 && getValue(x,y,1) < r.cost_zones[t]) {
							setValue(x,y,1,r.cost_zones[t]);
						}						
					}										
				}
			}									
		}
		return max_cost;
	}
	
	/**
	 * This method is an override of the toString method() to print the contents of the map.
	 * @return  Map information
	 */
	@Override
	public String toString() {
		int i = 0 , j = 0;
		String print = "";
		
		for(i=0;i<r.colsnb * r.rowsnb;i++) {
			for(j=0;j<4;j++) {
				
				print = print +"i: "+ i + " j: " + j + " Value: " + Map[i][j] + "\n";
			}
		}			
		return print;
	}
	
	/**
	 * This method sets a value in the Map.
	 * @param  x   Coordinate x of the point
	 * @param y Coordinate y of the point
	 * @param index (up,down,left,right)
	 * @param value Value to be inserted
	 */
	 void setValue(int x, int y, int index, int value) {
		Map[(y-1)*r.colsnb + (x-1)][index] = value;
	}
	
	/**
	 * This method returns a value in the Map.
	 * @param  x   Coordinate x of the point
	 * @param y Coordinate y of the point
	 * @param index (up,down,left,right)
	 * @return Value on that position
	 */
	 int getValue(int x, int y, int index) {
		return Map[(y-1)*r.colsnb + (x-1)][index];
	}
	
	
	/**
	 * This method receives a point, and returns the linked list of valid segments for which the individual can travel. <p>
	 * This is done by checking the map at the index of the point and evaluating all the four segments. 
	 * If the cost of the segment is different than zero, than the segment is added to the list.
	 * @param A current point of the individual
	 * @return  Linked List of Segments
	 * @see Point
	 * @see Segment 
	 * @see Individual
	 */
	public LinkedList<Segment> ValidSegments(Point A) {
		
		LinkedList<Segment> seglist = new LinkedList<Segment>();
		
		if(getValue(A.x,A.y,0) != 0) {
			seglist.add(new Segment(new Point(A.x,A.y),new Point(A.x-1,A.y), getValue(A.x,A.y,0)));
		}
		if(getValue(A.x,A.y,1) != 0) {
			seglist.add(new Segment(new Point(A.x,A.y),new Point(A.x,A.y+1), getValue(A.x,A.y,1)));
		}
		if(getValue(A.x,A.y,2) != 0) {
			seglist.add(new Segment(new Point(A.x,A.y),new Point(A.x+1,A.y), getValue(A.x,A.y,2)));
		}
		if(getValue(A.x,A.y,3) != 0) {
			seglist.add(new Segment(new Point(A.x,A.y),new Point(A.x,A.y-1), getValue(A.x,A.y,3)));
		}
		return seglist;
	}	

}

