package simulator;

import java.util.Comparator;


/**
 * This Comparator is used to compare Events by their time, to sort them in the {@link simulator.PEC}.
 * @see simulator.PEC
 */
public class EventComparatorByTime implements Comparator<Event>{
	
	/**
	 * Compares two Events by smaller time: returns 
	 * @param e1 Event 1 to compare
	 * @param e2 Event 2 to compare
	 * @return 1 if e2 has smaller time than e1, -1 if e1 has smaller time than e2 and 0 if they have
	 * equal times.
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@Override
	public int compare(Event e1,Event e2) {
		if (e1.time() < e2.time()) return -1;
        if (e1.time() > e2.time()) return 1;
        return 0;
	}
}
