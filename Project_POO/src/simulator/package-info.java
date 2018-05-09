/**
 * 
 */
/**
 * This package contains general interface that provides simulation required services, {@link simulator.Simulator}.
 * Any kind of simulation should implement its methods.<p>
 * This package also provides the more specific case of  {@link simulator.EventSimulator}, where simulation time jumps
 * from Event to Event. A general, {@link simulator.EventSimulator}, and specific, 
 * {@link simulator.PEC}, type of container are given for this case, also being provided in 
 * whis package the {@link simulator.EventComparatorByTime} used in the implementation of the container.
 *
 */
package simulator;