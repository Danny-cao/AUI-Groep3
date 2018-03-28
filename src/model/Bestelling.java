package model;

import java.util.HashMap;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

public class Bestelling extends BaseModel {
	private int tafelNummer;
	private HashMap<Gerecht, Integer> gerechten;
	
	public Bestelling(int id, int tafelNummer) {
		super(id);
		this.tafelNummer = tafelNummer;
		this.gerechten = new HashMap<Gerecht, Integer>();
	}
	
	public int getTafelNummer() {
		return this.tafelNummer;
	}

	@Override
	public JsonObject getInfo() {
		JsonObjectBuilder builder = Json.createObjectBuilder();
		builder.add("id", getId());
		builder.add("tafelnummer", getTafelNummer());
		builder.add("gerechten", getGerechten());
		return builder.build();
	}
	
	public JsonArray getGerechten() {
		JsonArrayBuilder builder = Json.createArrayBuilder();

		for (Gerecht gerecht : this.gerechten.keySet()) {
			builder.add(gerecht.getInfo());
		}
		
		return builder.build();
	}
	
	public void voegGerechtToe(Gerecht gerecht) {
		voegGerechtToe(gerecht, 1);
	}
	
	public void voegGerechtToe(Gerecht gerecht, int aantal) {
		if (!this.gerechten.containsKey(gerecht)) {
			this.gerechten.put(gerecht, aantal);
		} else {
			int huidigAantal = this.gerechten.get(gerecht);
			this.gerechten.replace(gerecht, huidigAantal + aantal);
		}
	}
	
	public void verwijderGerecht(Gerecht gerecht) {
		verwijderGerecht(gerecht, 1);
	}
	
	public void verwijderGerecht(Gerecht gerecht, int aantal) {
		if (this.gerechten.containsKey(gerecht)) {
			int huidigAantal = this.gerechten.get(gerecht);
			int nieuwAantal = huidigAantal - aantal;
			if (aantal > 0) {
				this.gerechten.replace(gerecht, nieuwAantal);
			} else {
				this.gerechten.remove(gerecht);
			}
		}
	}
}
