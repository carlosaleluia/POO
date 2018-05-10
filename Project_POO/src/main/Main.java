package main;

import pathfinder.MainSimulator;
import simulator.Simulator;

public class Main{
	
	/**
	 * Main method that runs project.
	 * @param args name of XML file with constants.
	 * @throws Exception error reading XML file.
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
