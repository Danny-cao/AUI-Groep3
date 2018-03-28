package controller;

import java.util.ArrayList;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

import model.Bestelling;
import server.Conversation;
import server.Handler;

public class BestellingController implements Handler {
	
	private ArrayList<Bestelling> bestellingen;
	
	public void handle(Conversation conversation) {
		String action = conversation.getParameter("action");
		if (action != null) {
			switch (action) {
			case "get":
				try {
					int bestellingId = Integer.parseInt(conversation.getParameter("id"));
					Bestelling bestelling = getBestellingById(bestellingId);
					if (bestelling != null) {
						conversation.sendJSONMessage(bestelling.getInfo().toString());
					}
				}
				catch (Exception e) {
					System.out.println(e.getMessage());
				}
				break;
			}
		} else {
			conversation.sendJSONMessage(getBestellingen().toString());
		}
	}
	
	private Bestelling getBestellingById(int id) {
		for (Bestelling bestelling : this.bestellingen) {
			if (bestelling.getId() == id) {
				return bestelling;
			}
		}
		
		return null;
	}
	
	private JsonArray getBestellingen() {
		JsonArrayBuilder bestellingen = Json.createArrayBuilder();
		
		for (Bestelling bestelling : this.bestellingen) {
			bestellingen.add(bestelling.getInfo());
		}
		
		return bestellingen.build();
	}
}