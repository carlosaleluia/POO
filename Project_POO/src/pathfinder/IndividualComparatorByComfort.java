package pathfinder;

import java.util.Comparator;


public class IndividualComparatorByComfort implements Comparator<Individual>{
	
	/**
	 * Compares two Individuals by greater comfort.
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 * @return 1 if i2 has greater comfort than i1, -1 if i1 has greater comfort than i2 and 0 if they have
	 * equal comfort.
	 */
	@Override
	public int compare(Individual i1,Individual i2) {
		if (i1.comfort < i2.comfort) return 1;
        if (i1.comfort > i2.comfort) return -1;
        return 0;
	}

}
