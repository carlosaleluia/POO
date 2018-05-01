package pathfinder;


class Grid {	
	
	int[][] Map;
	ReadFile r;
	
	public Grid(ReadFile r) {		
		Map = new int[r.colsnb * r.rowsnb][4];	
	    this.r = r;
	}
	
	
	void initializeGrid() {
		
		int i = 0, j = 0;
		
		for(i=0;i<r.colsnb * r.rowsnb;i++) {
			for(j=0;j<4;j++) {
				Map[i][j] = 1;
			}
		}		
				
		for(i=0; i<r.nbrobstacles;i++) {		
			for(j=0;j<4;j++) {
				Map[((r.obstacles[i].y)-1)*r.colsnb + (r.obstacles[i].x)-1][j] = 0;
			}			
		}
		
		for(i=1;i<=r.colsnb;i++) {
			Map[i-1][1]=0;		
			Map[(r.rowsnb-1)*r.colsnb + i-1][3]=0;
		}
		for(i=1;i<=r.rowsnb;i++) {
			Map[(i-1)* r.colsnb][0]=0;		
			Map[(i-1)* r.colsnb + r.colsnb-1][2]=0;
		}
		
		
		
		
	}
	

	@Override
	public String toString() {
		int i = 0 , j = 0;
		String print = "";
		
		for(i=0;i<r.colsnb * r.rowsnb;i++) {
			for(j=0;j<4;j++) {
				
				print = print +"i: "+ i + " j: " + j + " Value: " + Map[i][j] + "\n";
			}
		}
		
		
		
		return print;
	}
	
	
	
	

}

