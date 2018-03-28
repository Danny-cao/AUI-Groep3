package model;

import javax.json.JsonObject;

public abstract class BaseModel {
	private int id;
	
	public BaseModel(int id) {
		this.id = id;
	}
	
	public int getId() {
		return this.id;
	}
	
	public JsonObject getInfo() {
		return null;
	}
}
