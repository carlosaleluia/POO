package main;

import pathfinder.MainSimulator;

public class Main{
	
	public static void main(String[] args) throws Exception {
		
		MainSimulator simulator = new MainSimulator("test_1.xml");		
		simulator.run();		
	}
}
