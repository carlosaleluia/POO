package pathfinder;

import java.util.LinkedList; //??
import simulator.EventSimulator;

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
	
	/* Em cada epidemia faz-se sort para os 5 melhores,e SOBREVIVEM */
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
		this.list_individuals = new LinkedList<Individual>();

	}
	
	
	public void run(){
		
		System.out.println(Map);
		
		/*start simulation*/
		/*while(1)     --PEC NOT EMPTY --TEMPO DE SIMULACAO EXCEDIDO   */
		/*contar number de eventos feitos*/
		
		
		
	}
	
	@Override
	public void StartSimulation(){
		
		/*criar a primeira lista de individuos e observacoes*/
		
		
	}
	
	

}
