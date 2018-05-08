package simulator;

import generator.GenerateNumber;

public abstract class EventSimulator implements Simulator{

	/**
	 * Generator of numbers (random or deterministic) to be used in simulation.
	 */
	protected GenerateNumber generator;
	/**
	 * Event container where events to be simulated are stored.
	 */
	protected EventContainer container;
	
	protected float final_instant;
	protected int nb_events_done;
	
	
	/**
	 * Implementation of simulator.Simulator.Next#(), simulating next event in line and adding new events
	 * to be generated all at once.
	 * @see simulator.Simulator#Next()
	 * @see simulator.Event#doEvent()
	 */
	@Override
	public void Next() {
		container.addEvent(container.nextEvent().doEvent());
		nb_events_done++;
	}
	
	/**
	 * This method returns final instant of simulation, to access outside of EventSimulator.
	 * @return final instant of simulation.
	 */
	public float GetFinalInstant() {
		return final_instant;
	}
	/**
	 * This method returns number of events already simulated, to access outside of EventSimulator.
	 * @return number of events done.
	 */
	public float GetNbEventsDone() {
		return nb_events_done;
	}
	/**
	 * This method generates a number according to the generator of this EventCointainer.
	 * @param parameters to be used in number generation.
	 * @return number generated according to generator.
	 */
	public double Generator(double[] par) {
		return generator.Generate(par);
	}
	
}
