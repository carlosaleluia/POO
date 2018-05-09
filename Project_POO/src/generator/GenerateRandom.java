package generator;

import java.util.Random;

/**
 * This abstract class implements the interface, adding a static Random field to be inherited by subclasses.
 *
 */
public abstract class GenerateRandom implements GenerateNumber{
	
	protected static Random random =new Random();
	
	public abstract double Generate(double[] par);
	}
