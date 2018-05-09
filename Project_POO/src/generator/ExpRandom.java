package generator;

import pathfinder.MainSimulator;

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
		return -par[0]*Math.log(1.0-next);
	
	}

}
