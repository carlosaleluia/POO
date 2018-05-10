package generator;

//import java.util.Random;

/**
 * This class extends {@link generator.GenerateRandom}, implementing number generator according to exponential distribution.
 *
 */
public class ExpRandom extends GenerateRandom{

	
	/** 
	 * This method generates a random according to exponential distribution, given it's parameter.
	 * @see generator.GenerateRandom#Generate(double[])
	 * @param par mean value of exponential
	 */
	public double Generate(double[] par) {
		
		double next = random.nextDouble();
		
		if(par.length < 1) {
			System.err.println("Not enough parameters given for the generation of random numbers");
			System.exit(1);
		}		
		return -par[0]*Math.log(1.0-next);
	
	}

}
