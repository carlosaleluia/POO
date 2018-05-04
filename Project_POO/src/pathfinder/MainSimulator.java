package pathfinder;

import java.util.LinkedList;

import generator.ExpRandom;
import simulator.EventSimulator;
import simulator.PEC;

public class MainSimulator extends EventSimulator{
	
	
	final int init_population, max_population;
	final int n, m; //n is the number of lines, m is the number of columns
	final int comfortsens; // Comfort Parameter
	final int param_death, param_move, param_reproduce;	//Parameters for number generator
	final Point initial, destination; // Initial and Final Point
	double[] death_param, move_param, reproduction_param;
	
	boolean final_point_hit;
	
	Grid Map;
	LinkedList<Individual> list_individuals;
	
	Individual the_best;
	
	public MainSimulator(String s) {				
		ReadFile r = new ReadFile(s); 
		r.readXML();		
		
		Map = new Grid(r);
		Map.initializeGrid();
		
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
				
		this.container = new PEC(3* this.max_population);
		this.generator = new ExpRandom();
		this.list_individuals = new LinkedList<Individual>();
		this.the_best = new Individual(this,new LinkedList<Segment>());
		this.the_best.current = new Point(this.initial.x,this.initial.y);
		
		death_param = new double[2];
		reproduction_param = new double[2];
		move_param = new double[2];		
	}
	
	
	public void run(){
		
		System.out.println("\nPARAMETERS READ FROM XML FILE: \n");
		System.out.println("Final instant: " + this.final_instant + "\n");
		System.out.println("Initial population: " + this.init_population + "\n");
		System.out.println("Max population: " + this.max_population + "\n");
		System.out.println("Comfort: " + this.comfortsens + "\n");
		System.out.println("Parameters: " + "Death: "+  this.param_death + " Move: "+ this.param_move + " Reproduction: " + this.param_reproduce +"\n");	

		this.StartSimulation();
		while(!HasOnlyObservation()) {
			
			 Next();
		
		//	if(this.list_individuals.size() > this.max_population) {
				//EPIDEMIAAAAAA
		//	}
			
		}
		
		
	}
	
	@Override
	public void StartSimulation(){
		
		int i = 0;
		Individual a;
		Death d;
		Reproduction r;
		Observation o;
		Move m;
				
		o = new Observation(this,this.list_individuals);
		this.container.addEvent(o);		
		
		for(i = 0 ; i < this.init_population ; i++) {
			
			a = new Individual(this,new LinkedList<Segment>());
			
			death_param[0] = (1 - Math. log(1 - a.comfort))*param_death;
			death_param[1] = 0;			
			d = new Death(a,this.list_individuals, death_param );
			reproduction_param[0] = (1 - Math. log(1 - a.comfort))*10;
			reproduction_param[1] = 0;	
			r = new Reproduction(a,this.list_individuals,this.Map,reproduction_param);
			move_param[0] = (1 - Math. log(1 - a.comfort))*param_move;
			move_param[1] = 0;
			m = new Move(a,this.Map,move_param);
			this.list_individuals.add(a);
			this.container.addEvent(d);
			this.container.addEvent(r);
			this.container.addEvent(m);				
		}				
	}
	private boolean HasOnlyObservation() {
		if (container.numberEvents()<=1) return true;
		else return false;
	}
	
	public void Epidemics() {
		
	}
	

}
