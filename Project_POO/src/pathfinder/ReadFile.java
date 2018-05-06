package pathfinder;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class ReadFile extends DefaultHandler {
	
	String FileName;
	int finalinst = 0;
	int initpop = 0;
	int maxpop = 0 ;
	int comfortsens = 0;
	int colsnb = 0;
	int rowsnb = 0;
	Point initialpoint;
	Point finalpoint;
	int nbrzones = 0;
	Point[][] zones;
	int[] cost_zones;
	int idzone = 0;
	Point[] obstacles;
	int idobstacle = 0;
	int nbrobstacles = 0;
	int param_death = 0;
	int param_move = 0;
	int param_reproduce = 0;

	 protected ReadFile(String f){
		this.FileName = f;
	}
	 
	 
	/* public void startDocument(){
		 System.out.println("Beginning the parsing of "+ FileName);
		}
	 
		public void endDocument(){
		 System.out.println("Parsing concluded");
		} */
		
public void startElement(String uri, String name, String tag, Attributes atts){						
	int i=0;
	try {		 
		 if (tag.equals("simulation")){		 
			 for(i=0;i<atts.getLength();i++) {				 
				 switch(atts.getLocalName(i)) {
				 	case("finalinst"):{
				 		this.finalinst = Integer.parseInt(atts.getValue(i));
				 		if(this.finalinst <= 0) {throw new Exception("Invalid final instant");}
				 		break;}
				 	case("initpop"):{
				 		this.initpop = Integer.parseInt(atts.getValue(i));
				 		if(this.initpop <= 0) {throw new Exception("Invalid initial population");}
				 		break;}
				 	case("maxpop"):{
				 		this.maxpop = Integer.parseInt(atts.getValue(i));
				 		if(this.maxpop <= 0) {throw new Exception("Invalid maximum population");}
				 		break;}
				 	case("comfortsens"):{
				 		this.comfortsens= Integer.parseInt(atts.getValue(i));
				 		if(this.maxpop <= 0) {throw new Exception("Invalid comfort sensitivity");}
				 		break;}
				 	default:{break;}			 						 	
				 }				 			 			 
		//	 System.out.print( "" + atts.getLocalName(i) + " " + atts.getValue(i) + " ");
			 }
		 }
		 else if (tag.equals("grid")){				 
			for(i=0;i<atts.getLength();i++) {				 
				switch(atts.getLocalName(i)) {
					case("colsnb"):{
					 	this.colsnb=Integer.parseInt(atts.getValue(i)); 
					 	if(this.colsnb <= 0) { throw new Exception("Invalid number of columns");}
					 	break;}
					 case("rowsnb"):{
					 	this.rowsnb=Integer.parseInt(atts.getValue(i));
					 	if(this.rowsnb <= 0) { throw new Exception("Invalid number of rows");}
					 	break;}
					 default:{break;}			 							 	
					 }				 			 				 
			//	 System.out.print( "" + atts.getLocalName(i) + " " + atts.getValue(i) + " ");
				 }		
		 }
		 else if (tag.equals("initialpoint")){	
			this.initialpoint = new Point(0,0);				
			for(i=0;i<atts.getLength();i++) {				 
				switch(atts.getLocalName(i)) {
					case("xinitial"):{
					 	this.initialpoint.x = Integer.parseInt(atts.getValue(i));
					 	if(this.initialpoint.x <= 0 || this.initialpoint.x > this.colsnb) { throw new Exception("Invalid coordinates");}
					 	break;}
					 case("yinitial"):{
						initialpoint.y = Integer.parseInt(atts.getValue(i));
						if(this.initialpoint.y <= 0 || this.initialpoint.y > this.rowsnb) { throw new Exception("Invalid coordinates");}
					 	break;
					 }
					 default:{break;}			 							 	
			 }				 			 				 
			//	 System.out.print( "" + atts.getLocalName(i) + " " + atts.getValue(i) + " ");
			}		
		 }		 
		 else if (tag.equals("finalpoint")){
			 this.finalpoint = new Point(0,0);
			 for(i=0;i<atts.getLength();i++) {				 
				switch(atts.getLocalName(i)) {
					case("xfinal"):{
					 	finalpoint.x = Integer.parseInt(atts.getValue(i));
					 	if(this.finalpoint.x <= 0 || this.finalpoint.x > this.colsnb) { throw new Exception("Invalid coordinates");}
					 	break;}
					 case("yfinal"):{
						finalpoint.y = Integer.parseInt(atts.getValue(i));
						if(this.finalpoint.y <= 0 || this.finalpoint.y > this.rowsnb ) { throw new Exception("Invalid coordinates");}
					 	break;}
					 default:{break;}			 							 	
				}				 			 				 
			//	 System.out.print( "" + atts.getLocalName(i) + " " + atts.getValue(i) + " ");
			 }		
		 }
		 else if (tag.equals("specialcostzones")){
			this.nbrzones = Integer.parseInt(atts.getValue(0));
			if(this.nbrzones < 0) { throw new Exception("Invalid number of zones");}
			this.zones = new Point[nbrzones][2];
			this.cost_zones = new int[nbrzones];
		 }	
		 else if (tag.equals("zone")){
			if(this.nbrzones == 0){ throw new Exception("Invalid number of zones");}
			if(this.idzone >= this.nbrzones){ throw new Exception("Number of zones doesn't match zones");}
			this.zones[idzone][0] = new Point(0,0);
			this.zones[idzone][1] = new Point(0,0);
			for(i=0;i<atts.getLength();i++) {				 
				switch(atts.getLocalName(i)) {
					case("xinitial"):{
						 this.zones[idzone][0].x = Integer.parseInt(atts.getValue(i));
						 if(this.zones[idzone][0].x <= 0 || this.zones[idzone][0].x > this.colsnb){ throw new Exception("Invalid zones");}
					 	break;}
					 case("yinitial"):{
						 this.zones[idzone][0].y = Integer.parseInt(atts.getValue(i));
						 if(this.zones[idzone][0].y <= 0 || this.zones[idzone][0].y > this.rowsnb){ throw new Exception("Invalid zones");}
					 	break;}
					case("xfinal"):{
						this.zones[idzone][1].x = Integer.parseInt(atts.getValue(i));
						if(this.zones[idzone][1].x <= 0 || this.zones[idzone][1].x > this.colsnb){ throw new Exception("Invalid zones");}
						break;}
					case("yfinal"):{
						this.zones[idzone][1].y = Integer.parseInt(atts.getValue(i));
						if(this.zones[idzone][1].y <= 0 || this.zones[idzone][1].y > this.rowsnb){ throw new Exception("Invalid zones");}
						break;}
					 default:{break;}			 							 	
			 }
			//	System.out.print( "" + atts.getLocalName(i) + " " + atts.getValue(i) + " ");
				 }	
			 idzone++;
		 }
		 else if (tag.equals("obstacles")){	
			nbrobstacles = Integer.parseInt(atts.getValue(0));
			if(this.nbrobstacles < 0) { throw new Exception("Invalid number of obstacles");}
			this.obstacles = new Point[nbrobstacles];
		}
		 else if (tag.equals("obstacle")){
			if(this.nbrobstacles == 0){ throw new Exception("Invalid number of obstacles");}
			if(this.idobstacle >= this.nbrobstacles){ throw new Exception("Number of obstacles doesn't match obstacles");}
			this.obstacles[idobstacle] = new Point(0,0);
			for(i=0;i<atts.getLength();i++) {				 
				switch(atts.getLocalName(i)) {
					case("xpos"):{
						 this.obstacles[idobstacle].x = Integer.parseInt(atts.getValue(i));
						 if(this.obstacles[idobstacle].x <= 0 || this.obstacles[idobstacle].x > this.colsnb){ throw new Exception("Invalid obstacle");}
					 	break;}
					 case("ypos"):{
						 this.obstacles[idobstacle].y = Integer.parseInt(atts.getValue(i));
						 if(this.obstacles[idobstacle].y <= 0 || this.obstacles[idobstacle].y > this.rowsnb){ throw new Exception("Invalid obstacle");}
					 	break;}
					 default:{break;}			 							 	
				}
			//	System.out.print( "" + atts.getLocalName(i) + " " + atts.getValue(i) + " ");
			}	
			 idobstacle++;
		 }
		 else if (tag.equals("death")){	
			 this.param_death = Integer.parseInt(atts.getValue(0));
			 if(this.param_death <= 0){ throw new Exception("Invalid death parameter");}
		}
		 else if (tag.equals("reproduction")){	
			 param_reproduce = Integer.parseInt(atts.getValue(0));
			 if(this.param_reproduce <= 0){ throw new Exception("Invalid reproduction parameter");}
		}
		 else if (tag.equals("move")){	
			 param_move = Integer.parseInt(atts.getValue(0));
			 if(this.param_move <= 0){ throw new Exception("Invalid move parameter");}
		}		 
		 else {			 
			 for(i=0;i<atts.getLength();i++) {		
			//	 System.out.print( "" + atts.getLocalName(i) + " " + atts.getValue(i) + " ");
			 }
			 
		 }
		 //	System.out.print( "> \n");			 	
		} 
		catch (NumberFormatException e) {System.err.println("Invalid character sequence"); System.exit(1);}
		catch (Exception a) {System.err.println(a.getMessage()); System.exit(1); }
}		
		
				
public void characters(char[]ch,int start,int length){
	try {
		this.cost_zones[idzone-1] = Integer.parseInt(new String(ch,start,length));
		if(this.cost_zones[idzone-1] <= 0){ throw new Exception("Invalid zone cost");}
		}
		catch (Exception a){System.err.println(a.getMessage()); System.exit(1);}
		//	 System.out.print(new String(ch,start,length) );
} 
	
		 
	 protected void readXML() {		 
		 	 
		// builds the SAX parser
		 SAXParserFactory fact = SAXParserFactory.newInstance();		 
		 
		 try {
		 fact.setValidating(true); 
		 SAXParser saxParser = fact.newSAXParser();

		 // parse the XML document with this handler
		 DefaultHandler handler = this;			 
		 saxParser.parse(new File(this.FileName), handler); 
		 
		 if(this.nbrobstacles != this.idobstacle){ throw new Exception("Number of obstacles doesn't match obstacles");}
		 if(this.nbrzones != this.idzone){ throw new Exception("Number of zones doesn't match zones");}
		 }
		 catch(IOException e) {System.err.println("IO error");System.exit(1);} 
		 catch(SAXException e) {System.err.println("Parser error"); System.exit(1);} 
		 catch(ParserConfigurationException e) { System.err.println("Parser configuration error"); System.exit(1);} 
		 catch (Exception a) {System.err.println(a.getMessage()); System.exit(1); }		 
	 }



}
