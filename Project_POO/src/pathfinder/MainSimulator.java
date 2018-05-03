package pathfinder;

import java.util.LinkedList;
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
	PEC pec;
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
		
		death_param = new double[2];
		reproduction_param = new double[2];
		move_param = new double[2];		
	}
	
	
	public void run(){
		
		System.out.println(Map);
		
		System.out.println("\nPARAMETERS READ FROM XML FILE: \n");
		System.out.println("Final instant: " + this.final_instant + "\n");
		System.out.println("Initial population: " + this.init_population + "\n");
		System.out.println("Max population: " + this.max_population + "\n");
		System.out.println("Comfort: " + this.comfortsens + "\n");
		System.out.println("Parameters: " + "Death: "+  this.param_death + " Move: "+ this.param_move + " Reproduction: " + this.param_reproduce +"\n");	
		
	//	List<Event> aux;
		
		this.StartSimulation();
		//... redifinir PEC.isempty para se tiver 1 unico evento acabar
	//	while(!pec.isempty()) {
			
			// Next();
		
		//	if(this.list_individuals.size() > this.max_population) {
				//EPIDEMIAAAAAA
		//	}
			
	//	}
		
		
	}
	
	@Override
	public void StartSimulation(){
		
		int i = 0;
		Individual a;
		Death d;
		Reproduction r;
		Observation o;
		Move m;
		
		this.pec = new PEC(3* this.max_population); 
		this.list_individuals = new LinkedList<Individual>();
		
		o = new Observation(this,this.list_individuals);
		this.pec.addEvent(o);
		
		
		for(i = 0 ; i < this.init_population ; i++) {
			
			a = new Individual(this,new LinkedList<Segment>());
			
			death_param[0] = (1 - Math. log(1 - a.comfort))*param_death;
			death_param[1] = 0;			
//			d = new Death(a,this.list_individuals, death_param );
			reproduction_param[0] = (1 - Math. log(1 - a.comfort))*param_reproduce;
			reproduction_param[1] = 0;	
//			r = new Reproduction(a,this.list_individuals,this.Map,reproduction_param);
			move_param[0] = (1 - Math. log(1 - a.comfort))*param_move;
			move_param[1] = 0;
//			m = new Move(a,this.Map,move_param);
//		    a.death_event = d;
//		    a.reproduction_event = r;
//		    a.move_event = m;	
			this.list_individuals.add(a);
//			this.pec.addEvent(d);
//			this.pec.addEvent(r);
//			this.pec.addEvent(m);	
			System.out.println(a);
			
		}
		
		System.out.println(this.list_individuals);
		
	}
	
	

}
