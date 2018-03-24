package controller;

import javax.json.Json;
import javax.json.JsonObjectBuilder;

import server.Conversation;
import server.Handler;

public class InputController implements Handler {
	
	public void handle(Conversation conversation) {
		
		 String name = conversation.getParameter("user");

		 JsonObjectBuilder objBuilder = Json.createObjectBuilder();
		 objBuilder.add("message", "The server says: Hi " + name + "!");
		 conversation.sendJSONMessage(objBuilder.build().toString());
		 
	}
}