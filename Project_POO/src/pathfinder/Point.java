package pathfinder;

/**
 * This class represents a Point in the grid, with fields x and y being its coordinates.
 *
 */
public class Point {
	
	protected int x;   
    protected int y;   
	
	public Point(int x, int y) {
		this.x=x;
		this.y=y;
	}
	/**
	 * This method is an override of the toString method() to print the contents of the Point.
	 * @return  (x,y)
	 */
	@Override
	public String toString() {
		return "(" + x + "," + y + ")";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(x);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(y);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Point))
			return false;
		Point other = (Point) obj;
		if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x))
			return false;
		if (Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y))
			return false;
		if(y == other.y && x == other.x) 
			return true;
		return true;
		
	}
	
	
	

}
