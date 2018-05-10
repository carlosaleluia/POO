package pathfinder;

import java.util.LinkedList;
import java.util.ListIterator;

import generator.GenerateNumber;
import generator.ExpRandom;
import generator.UnifRandom;
import simulator.EventSimulator;
import simulator.PEC;

/**
 * This class represents the extension of applied to the specific case of best path finding simulation.<p>
 * Thus, creates a {@link pathfinder.ReadFile} to read the XML data and stores all the constants, also 
 * creating the {@link pathfinder.Grid} used.<p>
 * To simulate it from a main function, {@link pathfinder.MainSimulator#run()} should be used. 
 *
 */
public class MainSimulator extends EventSimulator{
	
	
	/**
	 * Initial population size.
	 */
	final int init_population;
	
	/**
	 * Maximum population size. If population gets bigger, {@link pathfinder.MainSimulator#Epidemics()}
	 * happens.
	 */
	final int max_population;
	/**
	 * Maximum cost edge.
	 */
	final int max_cost_edge;
	
	/**
	 * Number of lines of the grid.
	 */
	final int n;
	/**
	 * Number of columns of the grid.
	 */
	final int m; 
	/**
	 * Comfort parameter.
	 */
	final int comfortsens;
	/**
	 * Death parameter.
	 */
	final int param_death;
	/**
	 * Move parameter.
	 */
	final int param_move;
	/**
	 * Reproduction parameter.
	 */
	final int param_reproduce;
	/**
	 * Initial Point.
	 */
	final Point initial;
	/**
	 * Final Point.
	 */
	final Point destination;
	
	/**
	 * Whether or not the final point has been hit so far in the simulation.
	 */
	boolean final_point_hit;
	
	/**
	 * Associated map where Individuals will move.
	 */
	Grid Map;
	/**
	 * List of alive Individuals.
	 */
	LinkedList<Individual> list_individuals;	
	/**
	 * Best individual so far: highest comfort if final point has not been hit and lowest cost otherwise.
	 */
	Individual the_best;
	
	/**
	 * Default constructor of MainSimulator. By default, uses generators specified in project description, 
	 * {@link generator.ExpRandom} and {@link generator.UnifRandom} respectively. Uses the given String to get 
	 * XML file name and give it to {@link pathfinder.ReadFile}. Then, creates {@link pathfinder.Grid}
	 * and stores all constants from XML.
	 * @param s XML file name.
	 */
	public MainSimulator(String s) {				
		ReadFile r = new ReadFile(s); 
		r.readXML();		
		
		Map = new Grid2D(r);
		this.max_cost_edge = Map.initializeGrid();
		
		this.init_population = r.initpop;
		this.max_population = r.maxpop;				
		this.n = r.rowsnb;
		this.m= r.colsnb;
		this.comfortsens = r.comfortsens;	
		this.initial = new Point(r.initialpoint.x,r.initialpoint.y);  
		this.destination = new Point(r.finalpoint.x,r.finalpoint.y);
		this.final_instant = r.finalinst;
		this.param_death = r.param_death;
		this.param_move = r.param_move;
		this.param_reproduce = r.param_reproduce;
		this.nb_events_done = 0;
				
		this.container = new PEC(3* (this.max_population+1)+1);
		this.generators = new GenerateNumber[2];
		generators[0]=new ExpRandom();
		generators[1]=new UnifRandom();
		this.list_individuals = new LinkedList<Individual>();
		this.the_best = new Individual(this,new LinkedList<Segment>());
		this.the_best.current = new Point(this.initial.x,this.initial.y);
	
	}
	/**
	 * Constructor of MainSimulator using given generators. Uses the given String to get 
	 * XML file name and give it to {@link pathfinder.ReadFile}. Then, creates {@link pathfinder.Grid}
	 * and stores all constants from XML.
	 * @param s XML file name.
	 * @param g given generators to be used in generating times (g[0]) and survival and movement (g[1]).
	 */
	public MainSimulator(String s, GenerateNumber[] g) {				
		ReadFile r = new ReadFile(s); 
		r.readXML();		
		
		Map = new Grid2D(r);
		this.max_cost_edge = Map.initializeGrid();
		
		this.init_population = r.initpop;
		this.max_population = r.maxpop;				
		this.n = r.rowsnb;
		this.m= r.colsnb;
		this.comfortsens = r.comfortsens;	
		this.initial = new Point(r.initialpoint.x,r.initialpoint.y);  
		this.destination = new Point(r.finalpoint.x,r.finalpoint.y);
		this.final_instant = r.finalinst;
		this.param_death = r.param_death;
		this.param_move = r.param_move;
		this.param_reproduce = r.param_reproduce;
		this.nb_events_done = 0;
				
		this.container = new PEC(3* (this.max_population+1)+1);
		generators=g;
		this.list_individuals = new LinkedList<Individual>();
		this.the_best = new Individual(this,new LinkedList<Segment>());
		this.the_best.current = new Point(this.initial.x,this.initial.y);
	
	}
	
	
	/**
	 * Implementation in specific case of this simulation.<p>
	 * Consists in a cycle where events in the container are all simulated one at a time by
	 * ascending time order. Stops when container has only an observation to be made, that can
	 * happen either when all {@link pathfinder.Individual} die or when final instant comes.
	 * @see simulator.Simulator#run()
	 */
	public void run(){
	//
		this.StartSimulation();
		while(!HasOnlyObservation()) {
			Next();
			if(this.list_individuals.size() > this.max_population) {
				Epidemics();
			}			
		}
		if(list_individuals.size()!=0) {
			Next();
		}
		System.out.println("Path of the best fit individual = " + this.the_best.printpath());	
	}
	
	/** 
	 * This method implements everything that should happen when beginning the best 
	 * path finder simulation: create the list of alive individuals and the events 
	 * associated with each {@link pathfinder.Individual} and adding them to the 
	 * {@link simulator.PEC}.
	 * @see simulator.Simulator#StartSimulation()
	 */
	@Override
	public void StartSimulation(){
		
		int i = 0;
		Individual a;
		Death d;
		Reproduction r;
		Observation o;
		Move m;
		
		//double[] aux;
		//aux = new double[1];
				
		o = new Observation(this,this.list_individuals);
		this.container.addEvent(o);		
		
		for(i = 0 ; i < this.init_population ; i++) {
			
			a = new Individual(this,new LinkedList<Segment>());			
			//aux[0] = a.death_p;		
			d = new Death(a,this.list_individuals, a.death_p);
			//aux[0] = a.reproduce_p;
			r = new Reproduction(a,this.list_individuals,this.Map,a.reproduce_p);
			//aux[0] = a.move_p;
			m = new Move(a,this.Map,a.move_p);
			this.list_individuals.add(a);
			if(d.time()<GetFinalInstant())
				this.container.addEvent(d);
			if(r.time()<a.death_event.time() && r.time()<GetFinalInstant())
				this.container.addEvent(r);
			if(m.time()<a.death_event.time() && m.time()<GetFinalInstant())
				this.container.addEvent(m);				
		}	

	}
	/**
	 * Checks whether only Event on PEC is the observation.
	 * @return true if has only observation and false otherwise.
	 */
	boolean HasOnlyObservation() {
		if (container.numberEvents()<=1) return true;
		else return false;
	}
	
	/**
	 * This methods does an Epidemic, saving five Individuals with greater comfort and possibly killing
	 * the rest. The probability of survival depends on Individual comfort. 
	 */
	protected void Epidemics() {
		list_individuals.sort(new IndividualComparatorByComfort());
		double[] survive=new double[2];
		survive[0]=0;
		survive[1]=1;
		Individual sick;
		ListIterator<Individual> ind_it = list_individuals.listIterator(5);
		while(ind_it.hasNext()){
			sick=ind_it.next();
			if(generators[1].Generate(survive)>sick.comfort) {
				if(sick.move_event.time()<sick.death_event.time() && sick.move_event.time()<final_instant) 
					container.removeEvent(sick.move_event);
				if(sick.reproduction_event.time()<sick.death_event.time() && sick.reproduction_event.time()<final_instant) 
					container.removeEvent(sick.reproduction_event);
				if(sick.death_event.time()<final_instant)
					container.removeEvent(sick.reproduction_event);
				ind_it.remove();
			}
		}
	}
}
