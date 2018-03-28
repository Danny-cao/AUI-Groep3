package model;

import java.util.ArrayList;
import java.util.HashMap;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

import model.Ingredient;

public class Gerecht extends BaseModel {

	private String naam;
	private double prijs;
	private Seizoen seizoen;
	private HashMap<Ingredient, Integer> ingredienten;
	
	public Gerecht(int id, String naam, double prijs) {
		super(id);
		this.naam = naam;
		this.ingredienten = new HashMap<Ingredient, Integer>();
		setPrijs(prijs);
	}
	
	public Gerecht(int id, String naam, double prijs, Seizoen seizoen) {
		this(id, naam, prijs);
		this.seizoen = seizoen;
	}

	@Override
	public JsonObject getInfo() {
		JsonObjectBuilder builder = Json.createObjectBuilder();
		builder.add("id", getId());
		builder.add("naam", getNaam());
		builder.add("ingredienten", getIngredienten());
		
		if (this.seizoen != null) {
			builder.add("seizoen", seizoen.getInfo());
		}
		
		return builder.build();
	}
	
	public void setPrijs(double prijs) {
		if (prijs > 0) {
			this.prijs = prijs;
		}
	}
	
	public double getPrijs() {
		return this.prijs;
	}
	
	public String getNaam() {
		return this.naam;
	}
	
	public JsonArray getIngredienten() {
		JsonArrayBuilder builder = Json.createArrayBuilder();
		
		for (Ingredient ingredient : this.ingredienten.keySet()) {
			builder.add(ingredient.getInfo());
		}
		
		return builder.build();
	}
	
	public void voegIngredientToe(Ingredient ingredient) {
		voegIngredientToe(ingredient, 1);
	}
	
	public void voegIngredientToe(Ingredient ingredient, int aantal) {
		if (!this.ingredienten.containsKey(ingredient)) {
			this.ingredienten.put(ingredient, aantal);
		} else {
			int huidigAantal = this.ingredienten.get(ingredient);
			this.ingredienten.replace(ingredient, huidigAantal + aantal);
		}
	}
	
	public void verwijderIngredient(Ingredient ingredient) {
		verwijderIngredient(ingredient, 1);
	}
	
	public void verwijderIngredient(Ingredient ingredient, int aantal) {
		if (this.ingredienten.containsKey(ingredient)) {
			int huidigAantal = this.ingredienten.get(ingredient);
			int nieuwAantal = huidigAantal - aantal;
			if (aantal > 0) {
				this.ingredienten.replace(ingredient, nieuwAantal);
			} else {
				this.ingredienten.remove(ingredient);
			}
		}
	}
}
