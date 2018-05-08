package main;

import pathfinder.MainSimulator;

public class Main{
	
	public static void main(String[] args) throws Exception {
		
		
		 //if (args.length <= 0) {
		  //    System.out.println("Usage: java BetterSAXChecker URL");
		   //   return;
		  //  }
		// String document = args[0];
		
		MainSimulator simulator = new MainSimulator("data1.xml");	
		
		simulator.run();		
	}
}
