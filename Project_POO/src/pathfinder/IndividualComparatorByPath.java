package pathfinder;

import java.util.Comparator;

public class IndividualComparatorByPath implements Comparator<Individual>{

	@Override
	public int compare(Individual i1, Individual i2) {
		if(i1.has_reached==true && i2.has_reached==false)
			return 1;
		else if(i1.has_reached==false && i2.has_reached==true)
			return -1;
		else {
			if (i1.current_cost < i2.current_cost) return 1;
	        if (i1.current_cost > i2.current_cost) return -1;
	        else
	        	return 0;
		}
	}

}
