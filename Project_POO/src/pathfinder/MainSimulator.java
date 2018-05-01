package specific;

import java.util.LinkedList;

public class MainSimulator {
	
	
	final int max_population, comfortsens,n , m;
	
	final Point initial, destination;
	
	Grid Map;
	
	/* Em cada epidemia faz-se sort para os 5 melhores,e SOBREVIVEM */
	LinkedList<Individual> list_individuals;
	
	Individual the_best;
	
	public MainSimulator(String s) {
				
		ReadFile r = new ReadFile(s); 
		r.readXML();		
		
		Map = new Grid(r);
		Map.initializeGrid();
		
		this.max_population = r.maxpop;
		this.comfortsens = r.comfortsens;
		this.n = r.rowsnb;
		this.m= r.colsnb;
		
		this.initial = r.initialpoint;
		this.destination = r.finalpoint;
		
		this.list_individuals = new LinkedList<Individual>();	

	}
	
	
	public void run(){
		
		System.out.println(Map);
		
		/*start simulation*/
		/*while(1)     --PEC NOT EMPTY --TEMPO DE SIMULACAO EXCEDIDO   */
		/*contar number de eventos feitos*/
		
		
		
	}
	
	public void startsimulation(){
		
		/*criar a primeira lista de individuos e observacoes*/
		
		
	}
	
	

}
