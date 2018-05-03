package generator;

import java.util.Random;

public interface GenerateNumber {

	static Random random = new Random();
	
	public static double Generate(double[] par) {
		double next =random.nextDouble();
		return -par[0]*Math.log(1.0-next);
	}
}


