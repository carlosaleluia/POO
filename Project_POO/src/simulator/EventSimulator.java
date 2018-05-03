package simulator;

import generator.GenerateNumber;

public abstract class EventSimulator implements Simulator{

	GenerateNumber generator;
	EventContainer container;
	
	protected float final_instant;
	protected int nb_events_done;
	
	
	@Override
	public void Next() {
		container.addEvent(container.nextEvent().doEvent());
		nb_events_done++;
	}
	
	public float GetFinalInstant() {
		return final_instant;
	}
	public float GetNbEventsDone() {
		return nb_events_done;
	}
	public double Generator(double[] par) {
		return generator.Generate(par);
	}
	
}
