package simulator;

import java.util.Comparator;


public class EventComparatorByTime implements Comparator<Event>{
	
	@Override
	public int compare(Event e1,Event e2) {
		if (e1.time() < e2.time()) return -1;
        if (e1.time() > e2.time()) return 1;
        return 0;
	}
}
