package pathfinder;

import java.util.LinkedList;

/**
 * This interface provides a service to be used to get valid segments where individual 
 * can move given its current point in the map.
 * @see pathfinder.Individual
 * @see pathfinder.Move
 * 
 */
public interface Grid {	
			
	
	/**
	 * Generic initialization of Grid
	 * @return Maximum cost in the grid
	 */
	int initializeGrid();
	
	
	/**
	 * This is the interface central method. It should be implemented for a 
	 * specific dimension grid.<p>
	 * When called, given current Point, returns List of Segments
	 * that Individual can append to his path.
	 * @param A current Individual Point
	 * @return LinkedList of Segments where Individual can move
	 */
	LinkedList<Segment> ValidSegments(Point A);

}

