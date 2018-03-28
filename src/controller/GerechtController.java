package controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

import model.Gerecht;
import model.Ingredient;
import server.Conversation;
import server.Handler;

public class GerechtController implements Handler {
	
	private ArrayList<Gerecht> gerechten;
	
	public GerechtController() {
		this.gerechten = new ArrayList<Gerecht>();
	}
	
	public void handle(Conversation conversation) {
		String action = conversation.getParameter("action");
		if (action != null) {
			switch (action) {
			case "get":
				try {
					int gerechtId = Integer.parseInt(conversation.getParameter("id"));
					Gerecht gerecht = getGerechtById(gerechtId);
					if (gerecht != null) {
						conversation.sendJSONMessage(gerecht.getInfo().toString());
					}
				}
				catch (Exception e) {
					System.out.println(e.getMessage());
				}
				break;
			}
		} else {
			conversation.sendJSONMessage(getGerechten().toString());
		}
	 }
	
	private Gerecht getGerechtById(int id) {
		for (Gerecht gerecht : this.gerechten) {
			if (gerecht.getId() == id) {
				return gerecht;
			}
		}
		
		return null;
	}
	
	private JsonArray getGerechten() {
		JsonArrayBuilder gerechten = Json.createArrayBuilder();
		
		for (Gerecht gerecht : this.gerechten) {
			gerechten.add(gerecht.getInfo());
		}
		
		return gerechten.build();
	}
}