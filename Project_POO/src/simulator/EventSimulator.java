package simulator;

import generator.GenerateNumber;

public abstract class EventSimulator implements Simulator{

	GenerateNumber generator;
	EventContainer container;
	
	protected float simulation_time;
	protected float final_instant;
	
}
