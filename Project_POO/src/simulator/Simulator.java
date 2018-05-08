package simulator;

public interface Simulator {

	/**
	 * This method implements everything that should be done when starting a given simulation, like instantiating and defining constants.
	 */
	public void StartSimulation();
	/**
	 * Main method of Simulator interface, called to run simulation.
	 */
	public void run();
	
	/**
	 * This method should be called in run(), doing next thing to be simulated.
	 */
	public void Next();
	
}
