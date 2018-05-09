package generator;

/**
 * This interface provides the service of generating a number according to any given parameters.<p>
 * It can be implemented by either a random generator or by a deterministic one.
 *
 */
public interface GenerateNumber {
	
	/**
	 * This method generates a number given certain parameters.
	 * @param par parameters
	 * @return generated number
	 */
	public double Generate(double[] par);
}


