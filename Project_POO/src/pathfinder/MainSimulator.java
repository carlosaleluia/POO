package pathfinder;

import java.util.LinkedList;
import java.util.List;

import simulator.Event;
import simulator.EventSimulator;
import simulator.PEC;

public class MainSimulator extends EventSimulator{
	
	
	final int init_population, max_population;
	final int n, m; //n is the number of lines, m is the number of columns
	final int comfortsens; // Comfort Parameter
	final int finalinst; // Final instant of the simulation
	final int param_death, param_move, param_reproduce;	//Parameters for number generator
	final Point initial, destination; // Initial and Final Point
	int nbeventsdone;
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
		this.initial = r.initialpoint;
		this.destination = r.finalpoint;		
		this.finalinst = r.finalinst;
		this.param_death = r.param_death;
		this.param_move = r.param_move;
		this.param_reproduce = r.param_reproduce;
		this.nb_events_done = 0;
	}
	
	
	public void run(){
		
		System.out.println(Map);
		
		System.out.println("\nPARAMETERS READ FROM XML FILE: \n");
		System.out.println("Final instant: " + this.finalinst + "\n");
		System.out.println("Initial population: " + this.init_population + "\n");
		System.out.println("Max population: " + this.max_population + "\n");
		System.out.println("Comfort: " + this.comfortsens + "\n");
		System.out.println("Parameters: " + "Death: "+  this.param_death + " Move: "+ this.param_move + " Reproduction: " + this.param_reproduce +"\n");	
		
		List<Event> aux;
		
		//this.StartSimulation();
		//... redifinir PEC.isempty para se tiver 1 unico evento acabar
		while((pec.nextEvent().time() < this.finalinst) && !pec.isempty() ) {
			
			// aux = pec.nextEvent().doEvent();
			//this.nb_eventsdone++;
			
			if(this.list_individuals.size() > this.max_population) {
				//EPIDEMIAAAAAA
			}
			
		}
		
		
	}
	
	@Override
	public void StartSimulation(){
		
		int i = 0;
		Individual a;
		Death d;
		Reproduction r;
		Move m;
		
		this.pec = new PEC(100000); //???
		this.list_individuals = new LinkedList<Individual>();
		//preciso de criar a primeira observacao e adicionar à PEC
		
		for(i = 0 ; i < this.init_population ; i++) {
			a = new Individual(this,new LinkedList<Segment>());
			//tempo dos eventos?
		//	d = new Death(a,this.list_individuals,??);
		//	r = new Reproduction(a,this.list_individuals,this.Map,??);
		//	m = new Move(a,this.Map,??);
		//  a.death_event = d;
		//  a.reproduction_event = r;
		//  a.move_event = m;	
			a.current_cost = 0 ;
			a.current_nbsegments = 0;
			a.updatecomfort();
			this.list_individuals.add(a);
		//	this.pec.addEvent(d);
//			this.pec.addEvent(r);
//			this.pec.addEvent(m);
			
		}
		
		
		
	}
	
	

}
