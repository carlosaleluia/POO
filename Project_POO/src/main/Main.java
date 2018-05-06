package main;

import pathfinder.MainSimulator;

public class Main{
	
	public static void main(String[] args) throws Exception {
		
		MainSimulator simulator = new MainSimulator("test_3.xml");
		
		simulator.run();		
	}
}
