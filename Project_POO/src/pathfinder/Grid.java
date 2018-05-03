package pathfinder;

import java.util.LinkedList;

class Grid {	
	
	int[][] Map;
	ReadFile r;
	
	public Grid(ReadFile r) {		
		Map = new int[r.colsnb * r.rowsnb][4];	
	    this.r = r;
	}
	
	
	void initializeGrid() {
		
		int i = 0, j = 0, t = 0;
		int x=0, y=0;
		int x1 = 0, x2 = 0, y1 = 0, y2 = 0;
		
		//Initialization
		for(i=0;i<r.colsnb * r.rowsnb;i++) {
			for(j=0;j<4;j++) {
				Map[i][j] = 1;
			}
		}		
		
		//Obstacles
		for(i=0; i<r.nbrobstacles;i++) {
			x = r.obstacles[i].x;
			y = r.obstacles[i].y;
			
			for(j=0;j<4;j++) {
				setValue(x,y,j,0);
			}	
			//Update adjacent points
			if(x-1 > 0) {
				setValue(x-1,y,2,0);
			}
			if(x+1 < r.colsnb + 1) {
				setValue(x+1,y,0,0);
			}
			if(y-1 > 0) {
				setValue(x,y-1,1,0);
			}
			if(y+1 < r.rowsnb + 1) {
				setValue(x,y+1,3,0);
			}
		}
		//Limits of the grid
		for(i=1;i<=r.colsnb;i++) {
			setValue(i,1,3,0);
			setValue(i,r.rowsnb,1,0);			
		}
		for(i=1;i<=r.rowsnb;i++) {
			setValue(1,i,0,0);
			setValue(r.colsnb,i,2,0);
		}
		
		for(t=0;t<r.nbrzones;t++) {
			x1= r.zones[t][0].x;
			y1= r.zones[t][0].y;
			x2= r.zones[t][1].x;
			y2= r.zones[t][1].y;
			for(x=1; x<=r.colsnb; x++) {
				for(y=1; y<=r.rowsnb;y++) {	
		
					if(x-1 >= x1 && x <= x2  && (y == y1 || y == y2) ) {
						if(getValue(x,y,0) != 0 && getValue(x,y,0) < r.cost_zones[t]) {
							setValue(x,y,0,r.cost_zones[t]);
						}
					}
					if(x >= x1 && x+1 <= x2  && (y == y1 || y == y2 )) {
						if(getValue(x,y,2) !=0 && getValue(x,y,2) < r.cost_zones[t]) {
							setValue(x,y,2,r.cost_zones[t]);
						}																			
					}
					if((x == x1 || x == x2)  && y-1 >= y1 && y <= y2) {
						if(getValue(x,y,3)!=0 && getValue(x,y,3) < r.cost_zones[t]) {
							setValue(x,y,3,r.cost_zones[t]);
						}							
					}
					if((x == x1 || x == x2)  && y >= y1 && y+1 <= y2) {	
						if(getValue(x,y,1)!=0 && getValue(x,y,1) < r.cost_zones[t]) {
							setValue(x,y,1,r.cost_zones[t]);
						}						
					}										
				}
			}									
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
	
	
	public void setValue(int x, int y, int index, int value) {
		Map[(y-1)*r.colsnb + (x-1)][index] = value;
	}
	
	public int getValue(int x, int y, int index) {
		return Map[(y-1)*r.colsnb + (x-1)][index];
	}
	
	
	LinkedList<Segment> ValidSegments(Point A) {
		
		LinkedList<Segment> seglist = new LinkedList<Segment>();
		
		if(getValue(A.x,A.y,0) != 0) {
			seglist.add(new Segment(new Point(A.x,A.y),new Point(A.x-1,A.y), getValue(A.x,A.y,0)));
		}
		if(getValue(A.x,A.y,1) != 0) {
			seglist.add(new Segment(new Point(A.x,A.y),new Point(A.x,A.y+1), getValue(A.x,A.y,1)));
		}
		if(getValue(A.x,A.y,2) != 0) {
			seglist.add(new Segment(new Point(A.x,A.y),new Point(A.x+1,A.y), getValue(A.x,A.y,2)));
		}
		if(getValue(A.x,A.y,3) != 0) {
			seglist.add(new Segment(new Point(A.x,A.y),new Point(A.x,A.y-1), getValue(A.x,A.y,3)));
		}
		return seglist;
	}
	
	
	

}

