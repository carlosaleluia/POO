package pathfinder;

import java.util.Comparator;


public class IndividualComparatorByComfort implements Comparator<Individual>{
	
	@Override
	public int compare(Individual i1,Individual i2) {
		if (i1.comfort < i2.comfort) return 1;
        if (i1.comfort > i2.comfort) return -1;
        return 0;
	}

}
