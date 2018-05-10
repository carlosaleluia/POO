package main;

import pathfinder.MainSimulator;

public class Main{
	
	/**
	 * Main method that runs project.
	 * @param args name of XML file with constants.
	 * @throws Exception error reading XML file.
	 */
	public static void main(String[] args) throws Exception {
		
		
		 //if (args.length <= 0) {
		  //    System.out.println("Usage: java BetterSAXChecker URL");
		   //   return;
		  //  }
		// String document = args[0];
		
		MainSimulator simulator = new MainSimulator("test_2.xml");	
		
		simulator.run();		
	}
}
