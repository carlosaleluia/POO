package generator;

import java.util.Random;

public abstract class GenerateRandom implements GenerateNumber{
	
	protected static Random random =new Random();
	
	public abstract double Generate(double[] par);
	}
