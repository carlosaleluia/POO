package pathfinder;

import java.util.Comparator;

/**
 * This Comparator is used to compare Individuals by their Paths, to check whether the best Individual stored in MainSimulator is worse than the best one alive.
 *
 */
public class IndividualComparatorByPath implements Comparator<Individual>{

	/**
	 * Compares two Individuals by better path:<p>
	 * if there is an individual which reached the final point, the individual with lower cost is better;<p>
	 * if none reached final destination, the individual with greater comfort is better.
	 * @param i1 Individual 1 to compare
	 * @param i2 Individual 2 to compare
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 * @return 1 if i1 has better path than i2, -1 if i2 has better path than i1 and 0 if they have
	 * equally good paths.
	 */
	@Override
	public int compare(Individual i1, Individual i2) {
		if(i1.has_reached==true && i2.has_reached==false)
			return 1;
		else if(i1.has_reached==false && i2.has_reached==true)
			return -1;
		else if(i1.has_reached==true && i2.has_reached==true){
			if (i1.current_cost < i2.current_cost) return 1;
	        if (i1.current_cost > i2.current_cost) return -1;
		}
		else if(i1.has_reached==false && i2.has_reached==false) {
			IndividualComparatorByComfort c=new IndividualComparatorByComfort();
			return c.compare(i1, i2)*(-1);
		}
		return 0;
	}

}
