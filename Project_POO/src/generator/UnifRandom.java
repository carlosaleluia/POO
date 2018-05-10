package generator;

//import java.util.Random;

/**
 * This class extends {@link generator.GenerateRandom}, implementing number generator according to uniform distribution.
 *
 */
public class UnifRandom extends GenerateRandom{
	
	/** 
	 * This method generates a random according to uniform distribution, given it's bounds.
	 * @see generator.GenerateRandom#Generate(double[])
	 * @param par lower and higher bound of generated number
	 */
	public double Generate(double[] par) {
		
		//int n =random.nextInt(par[2]) + par[1]; 
		//return n;
		if(par.length < 2) {
			System.err.println("Not enough parameters given for the generation of random numbers");
			System.exit(1);
		}
		return random.nextDouble()*(par[1]-par[0])+par[0];
			
	}
}
