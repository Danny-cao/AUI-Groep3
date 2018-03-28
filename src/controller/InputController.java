package controller;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.json.Json;
import javax.json.JsonObjectBuilder;

import server.Conversation;
import server.Handler;


public class InputController implements Handler{
	
	public void handle(Conversation conversation) {
		
		 String gerechtsnaam = conversation.getParameter("gerechtsnaam");
		 String ingredienten = conversation.getParameter("ingredienten");
		 String prijs = conversation.getParameter("prijs");
		 String beschikbaarInSeizoen = conversation.getParameter("beschikbaar");
		 

		 JsonObjectBuilder objBuilder = Json.createObjectBuilder();
		 objBuilder.add("gereacht", "gerecht:" + gerechtsnaam + "ingredienten:"+ ingredienten + "prijs:"+ prijs + "seizoen:" + beschikbaarInSeizoen);
		 
		 try {
			 FileWriter fw = new FileWriter("src/controller/gerechten.txt");
		     
		     fw.write(objBuilder.build().toString());
		     fw.flush();
		     fw.close();

		 }
		 catch(IOException e) {
			 e.printStackTrace();
		 }
	}

}
