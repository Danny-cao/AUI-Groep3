package model;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

public class Ingredient extends BaseModel {
	private String naam;
	private int voorraad;
	private int minimumVoorraad;
	private boolean inBestelling;
	
	public Ingredient(int id, String naam) {
		super(id);
		this.naam = naam;
	}
	
	public String getNaam() {
		return this.naam;
	}
	
	public int getVoorraad() {
		return this.voorraad;
	}
	
	@Override
	public JsonObject getInfo() {
		JsonObjectBuilder builder = Json.createObjectBuilder();
		builder.add("id", getId());
		builder.add("naam", getNaam());
		builder.add("voorraad", getVoorraad());
		return builder.build();
	}
}
