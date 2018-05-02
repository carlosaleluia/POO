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
	int finalinst;
	int initpop;
	int maxpop;
	int comfortsens;
	int colsnb;
	int rowsnb;
	Point initialpoint;
	Point finalpoint;
	int nbrzones;
	Point[][] zones;
	int[] cost_zones;
	int idzone = 0;
	Point[] obstacles;
	int idobstacle = 0;
	int nbrobstacles;
	int param_death;
	int param_move;
	int param_reproduce;

	 protected ReadFile(String f){
		this.FileName = f;
	}
	 
	 
	
	 
	 public void startDocument(){
		 System.out.println("Beginning the parsing of "+ FileName);
		}
	 
		public void endDocument(){
		 System.out.println("Parsing concluded");
		}
		
		public void startElement(String uri, String name,
		 String tag, Attributes atts){
						
			int i=0;
			
		 System.out.print("Element <" + tag + " ");
		 
		 if (tag.equals("simulation")){
			 
			 for(i=0;i<atts.getLength();i++) {				 
				 switch(atts.getLocalName(i)) {
				 	case("finalinst"):{
				 		this.finalinst = Integer.parseInt(atts.getValue(i)); 
				 		break;
				 	}
				 	case("initpop"):{
				 		this.initpop = Integer.parseInt(atts.getValue(i));
				 		break;
				 	}
				 	case("maxpop"):{
				 		this.maxpop = Integer.parseInt(atts.getValue(i));
				 		break;
				 	}
				 	case("comfortsens"):{
				 		this.comfortsens= Integer.parseInt(atts.getValue(i));
				 		break;}
				 	default:{
				 		break;
				 	}			 		
				 	
				 }				 			 
			 
			 System.out.print( "" + atts.getLocalName(i) + " " + atts.getValue(i) + " ");
			 }
		 }
		 else if (tag.equals("grid")){				 
			for(i=0;i<atts.getLength();i++) {				 
				switch(atts.getLocalName(i)) {
					case("colsnb"):{
					 	this.colsnb=Integer.parseInt(atts.getValue(i)); 
					 	break;
					 }
					 case("rowsnb"):{
					 	this.rowsnb=Integer.parseInt(atts.getValue(i));
					 		break;
					 	}
					 	default:{
					 		break;
					 	}			 							 	
					 }				 			 				 
				 System.out.print( "" + atts.getLocalName(i) + " " + atts.getValue(i) + " ");
				 }		
		 }
		 else if (tag.equals("initialpoint")){	
			this.initialpoint = new Point(0,0);				
			for(i=0;i<atts.getLength();i++) {				 
				switch(atts.getLocalName(i)) {
					case("xinitial"):{
					 	initialpoint.x = Integer.parseInt(atts.getValue(i));
					 	break;
					 }
					 case("yinitial"):{
						initialpoint.y = Integer.parseInt(atts.getValue(i));
					 	break;
					 }
					 default:{
					 	break;
					 }			 							 	
			 }				 			 				 
				 System.out.print( "" + atts.getLocalName(i) + " " + atts.getValue(i) + " ");
				 }		
		 }		 
		 else if (tag.equals("finalpoint")){
			 this.finalpoint = new Point(0,0);
			for(i=0;i<atts.getLength();i++) {				 
				switch(atts.getLocalName(i)) {
					case("xfinal"):{
					 	finalpoint.x = Integer.parseInt(atts.getValue(i));
					 	break;
					 }
					 case("yfinal"):{
						finalpoint.y = Integer.parseInt(atts.getValue(i));
					 	break;
					 }
					 default:{
					 	break;
					 }			 							 	
			 }				 			 				 
				 System.out.print( "" + atts.getLocalName(i) + " " + atts.getValue(i) + " ");
				 }		
		 }
		 else if (tag.equals("specialcostzones")){
			nbrzones = Integer.parseInt(atts.getValue(0));
			this.zones = new Point[nbrzones][2];
			this.cost_zones = new int[nbrzones];
		 }	
		 else if (tag.equals("zone")){
			 this.zones[idzone][0] = new Point(0,0);
			 this.zones[idzone][1] = new Point(0,0);
			for(i=0;i<atts.getLength();i++) {				 
				switch(atts.getLocalName(i)) {
					case("xinitial"):{
						 this.zones[idzone][0].x = Integer.parseInt(atts.getValue(i));
					 	break;
					 }
					 case("yinitial"):{
						 this.zones[idzone][0].y = Integer.parseInt(atts.getValue(i));
					 	break;
					 }
					case("xfinal"):{
						this.zones[idzone][1].x = Integer.parseInt(atts.getValue(i));
						 break;
					}
					case("yfinal"):{
						this.zones[idzone][1].y = Integer.parseInt(atts.getValue(i));
						 break;
					}
					 default:{
					 	break;
					 }			 							 	
			 }
				System.out.print( "" + atts.getLocalName(i) + " " + atts.getValue(i) + " ");
				 }	
			 idzone++;
		 }
		 else if (tag.equals("obstacles")){	
			 nbrobstacles = Integer.parseInt(atts.getValue(0));
				this.obstacles = new Point[nbrobstacles];
		}
		 else if (tag.equals("obstacle")){
			 this.obstacles[idobstacle] = new Point(0,0);
			for(i=0;i<atts.getLength();i++) {				 
				switch(atts.getLocalName(i)) {
					case("xpos"):{
						 this.obstacles[idobstacle].x = Integer.parseInt(atts.getValue(i));
					 	break;
					 }
					 case("ypos"):{
						 this.obstacles[idobstacle].y = Integer.parseInt(atts.getValue(i));
					 	break;
					 }
					 default:{
					 	break;
					 }			 							 	
			 }
				System.out.print( "" + atts.getLocalName(i) + " " + atts.getValue(i) + " ");
				 }	
			 idobstacle++;
		 }
		 else if (tag.equals("death")){	
			 param_death = Integer.parseInt(atts.getValue(0));
		}
		 else if (tag.equals("reproduction")){	
			 param_reproduce = Integer.parseInt(atts.getValue(0));
		}
		 else if (tag.equals("move")){	
			 param_move = Integer.parseInt(atts.getValue(0));
		}		 
		 else {			 
			 for(i=0;i<atts.getLength();i++) {		
				 System.out.print( "" + atts.getLocalName(i) + " " + atts.getValue(i) + " ");
			 }
			 
		 }
		 	System.out.print( "> \n");	
		}

		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		public void characters(char[]ch,int start,int length){
			 cost_zones[idzone-1] = Integer.parseInt(new String(ch,start,length));
		 System.out.print(new String(ch,start,length) );
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
		 }
		 catch(IOException e) {
			 System.err.println("IO error");
		 } catch(SAXException e) {
			 System.err.println("Parser error");
		 } catch(ParserConfigurationException e) {
			 System.err.println("Parser configuration error");
		 }
		 
		 
	 }



	 
	
	
	

}
