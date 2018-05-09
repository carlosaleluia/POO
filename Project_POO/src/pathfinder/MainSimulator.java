package pathfinder;

import java.util.LinkedList;
import java.util.ListIterator;

import generator.GenerateNumber;
import generator.ExpRandom;
import generator.UnifRandom;
import simulator.EventSimulator;
import simulator.PEC;

public class MainSimulator extends EventSimulator{
	
	
	final int init_population, max_population, max_cost_edge;
	final int n, m; //n is the number of lines, m is the number of columns
	final int comfortsens; // Comfort Parameter
	final int param_death, param_move, param_reproduce;	//Parameters for number generator
	final Point initial, destination; // Initial and Final Point
	
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
	 * XML file name and give it to {@link pathfinder.ReadFile}.
	 * @param s XML file name.
	 */
	public MainSimulator(String s) {				
		ReadFile r = new ReadFile(s); 
		r.readXML();		
		
		Map = new Grid(r);
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
	 * XML file name and give it to {@link pathfinder.ReadFile}.
	 * @param s XML file name.
	 */
	public MainSimulator(String s, GenerateNumber[] g) {				
		ReadFile r = new ReadFile(s); 
		r.readXML();		
		
		Map = new Grid(r);
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
	
	
	public void run(){
	
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
	
	@Override
	public void StartSimulation(){
		
		int i = 0;
		Individual a;
		Death d;
		Reproduction r;
		Observation o;
		Move m;
		
		double[] aux;
		aux = new double[1];
				
		o = new Observation(this,this.list_individuals);
		this.container.addEvent(o);		
		
		for(i = 0 ; i < this.init_population ; i++) {
			
			a = new Individual(this,new LinkedList<Segment>());			
			aux[0] = a.death_p;		
			d = new Death(a,this.list_individuals, aux);
			aux[0] = a.reproduce_p;
			r = new Reproduction(a,this.list_individuals,this.Map,aux);
			aux[0] = a.move_p;
			m = new Move(a,this.Map,aux);
			this.list_individuals.add(a);
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
	private boolean HasOnlyObservation() {
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
