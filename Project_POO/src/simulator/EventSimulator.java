package simulator;

import generator.GenerateNumber;

/**
 * This abstract class implements the Simulator, providing a general simulator of time Events. Thus, implements Next() (next Event to be simulated) and has general event container, sorted by order to be simulated.<p>
 * Also, contains a generator to provide generated numbers necessary for the simulation, random or not.<p>
 * @see simulator.Event
 *
 */
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
	public int GetNbEventsDone() {
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
