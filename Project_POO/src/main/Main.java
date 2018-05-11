package main;

import pathfinder.MainSimulator;
import simulator.Simulator;

/**
 * This class serves simply to run the project.
 *
 */
public class Main{
	
	/**
	 * Main method that runs project.
	 * @param args name of XML file with constants.
	 */
	public static void main(String[] args){
		
		if (args.length <= 0 || args.length > 1) {
			System.err.println("Usage: java -jar XMLFILE");
		    System.exit(1);
		}
		Simulator simulator = new MainSimulator(args[0]);			
		simulator.run();
	}
}
