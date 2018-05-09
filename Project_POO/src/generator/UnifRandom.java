package generator;

//import java.util.Random;

public class UnifRandom extends GenerateRandom{
	
	//static Random random =new Random();
		
	public double Generate(double[] par) {
		
		//int n =random.nextInt(par[2]) + par[1]; 
		//return n;
		
		return random.nextDouble()*(par[1]-par[0])+par[0];
			
	}
}
