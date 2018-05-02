package pathfinder;

import java.util.LinkedList; 
import simulator.EventSimulator;
import simulator.PEC;

public class MainSimulator extends EventSimulator{
	
	
	final int init_population, max_population;
	final int n, m; //n is the number of lines, m is the number of columns
	final int comfortsens; // Comfort Parameter
	final int finalinst; // Final instant of the simulation
	final int param_death, param_move, param_reproduce;	//Parameteres for number generator
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
		this.initial = r.initialpoint;
		this.destination = r.finalpoint;		
		this.finalinst = r.finalinst;
		this.param_death = r.param_death;
		this.param_move = r.param_move;
		this.param_reproduce = r.param_reproduce;		
	}
	
	
	public void run(){
		
		System.out.println(Map);
		
		//this.StartSimulation();
		//while(!PEC.isempty() || lasteevent.time > tempofinal)  
		//... redifinir PEC.isempty para se tiver 1 unico evento acabar
		/*fazer next event.. e ir contando eventos feitos*/
		//check se é preciso epidemia
		
		
	}
	
	@Override
	public void StartSimulation(){
		
		int i = 0;
		
		this.pec = new PEC(100000); //???
		this.list_individuals = new LinkedList<Individual>();
		//preciso de criar a primeira observacao e adicionar à PEC
		
		for(i = 0 ; i < this.init_population ; i++) {
			this.list_individuals.add(new Individual(this,new LinkedList<Segment>()));
			
			//preciso de criar os primeiros 3 eventos para todos e adicionar à PEC
		}
		
		
		
	}
	
	

}
