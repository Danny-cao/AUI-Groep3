package controller;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

import model.Seizoen;
import server.Conversation;
import server.Handler;

public class SeizoenController implements Handler {

	private Seizoen[] seizoenen;

	public SeizoenController() {
		this.seizoenen = new Seizoen[] {
				new Seizoen(1, "Lente"),
				new Seizoen(2, "Zomer"),
				new Seizoen(3, "Herfst"),
				new Seizoen(4, "Winter")
		};
	}

	public void handle(Conversation conversation) {
		JsonObjectBuilder response = Json.createObjectBuilder();
		
		String action = conversation.getParameter("action");
		if (action != null) {
			switch (action) {
			case "get":
				try {
					int seizoenId = Integer.parseInt(conversation.getParameter("id"));
					Seizoen seizoen = getSeizoenById(seizoenId);
					if (seizoen != null) {
						response.add("seizoen", seizoen.getInfo());
					}
				}
				catch (Exception e) {
					System.out.println(e.getMessage());
				}
				break;
			}
		} else {
			response.add("seizoenen", getSeizoenen());
		}
		
		conversation.sendJSONMessage(response.build().toString());
	}
	
	private Seizoen getSeizoenById(int id) {
		for (Seizoen seizoen : this.seizoenen) {
			if (seizoen.getId() == id) {
				return seizoen;
			}
		}
		
		return null;
	}
	
	private JsonArray getSeizoenen() {
		JsonArrayBuilder seizoenen = Json.createArrayBuilder();
		
		for (Seizoen seizoen : this.seizoenen) {
			seizoenen.add(seizoen.getInfo());
		}
		
		return seizoenen.build();
	}
}