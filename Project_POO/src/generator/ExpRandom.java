package generator;

import java.util.Random;

public class ExpRandom extends GenerateRandom{
	
static Random random =new Random();
	
	public double Generate(double[] par) {
		
		double next = random.nextDouble();
		return -par[0]*Math.log(1.0-next);
	
	}

}
