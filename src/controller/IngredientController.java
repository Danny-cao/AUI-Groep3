package controller;

import java.util.ArrayList;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

import model.Ingredient;
import server.Conversation;
import server.Handler;

public class IngredientController implements Handler {
	
	private ArrayList<Ingredient> ingredienten;
	
	public IngredientController() {
		this.ingredienten = new ArrayList<Ingredient>();
	}
	
	public void handle(Conversation conversation) {
		JsonObjectBuilder response = Json.createObjectBuilder();
		
		String action = conversation.getParameter("action");
		if (action != null) {
			switch (action) {
			case "get":
				try {
					int ingredientId = Integer.parseInt(conversation.getParameter("id"));
					Ingredient ingredient = getIngredientById(ingredientId);
					if (ingredient != null) {
						response.add("ingredient", ingredient.getInfo());
					}
				}
				catch (Exception e) {
					System.out.println(e.getMessage());
				}
				break;
			}
		} else {
			response.add("ingredienten", getIngredienten());
		}
		
		conversation.sendJSONMessage(response.build().toString());
	}
	
	private Ingredient getIngredientById(int id) {
		for (Ingredient ingredient : this.ingredienten) {
			if (ingredient.getId() == id) {
				return ingredient;
			}
		}
		
		return null;
	}
	
	private JsonArray getIngredienten() {
		JsonArrayBuilder ingredienten = Json.createArrayBuilder();
		
		for (Ingredient ingredient : this.ingredienten) {
			ingredienten.add(ingredient.getInfo());
		}
		
		return ingredienten.build();
	}
}