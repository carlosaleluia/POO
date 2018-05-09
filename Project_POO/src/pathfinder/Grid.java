package pathfinder;

import java.util.LinkedList;

/**
 * This class represents the map where Individuals will move. Being a 2D grid, 
 * stores values in 2D int array. Is used to get valid segments where individual 
 * can move given its current point in the map.
 * @see pathfinder.Individual
 * @see pathfinder.Move
 * 
 *
 */
public interface Grid {	
			
	/**
	 * This method is the constructor of the Grid Class.
	 * @param  r   ReadFile object with all the informations needed to build the map
	 * @see ReadFile
	 */
	//public Grid(ReadFile r) {		
	//	Map = new int[r.colsnb * r.rowsnb][4];	
	 //   this.r = r;
	//}
	
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
	abstract int initializeGrid();
	
	/**
	 * This method is an override of the toString method() to print the contents of the map.
	 * @return  Map information
	 */
	
	
	/**
	 * This method sets a value in the Map.
	 * @param  x   Coordinate x of the point
	 * @param y Coordinate y of the point
	 * @param index (up,down,left,right)
	 * @param value Value to be inserted
	 */
	 
	
	/**
	 * This method returns a value in the Map.
	 * @param  x   Coordinate x of the point
	 * @param y Coordinate y of the point
	 * @param index (up,down,left,right)
	 * @return Value on that position
	 */
	 
	
	
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
	abstract LinkedList<Segment> ValidSegments(Point A);

}

