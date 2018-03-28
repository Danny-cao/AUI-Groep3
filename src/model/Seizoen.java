package model;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

public class Seizoen extends BaseModel {
	private String naam;
	
	public Seizoen(int id, String naam) {
		super(id);
		this.naam = naam;
	}
	
	public String getNaam() {
		return this.naam;
	}
	
	@Override
	public JsonObject getInfo() {
		JsonObjectBuilder builder = Json.createObjectBuilder();
		builder.add("id", getId());
		builder.add("naam", getNaam());
		return builder.build();
	}
}
