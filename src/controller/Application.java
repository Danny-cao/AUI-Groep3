package controller;

import java.io.File;
import server.JSONFileServer;

public class Application {
	
	public static void main(String[] args) {
		JSONFileServer server = new JSONFileServer(new File("webapp"), 8888);

		GerechtController gerechtController = new GerechtController();
		server.registerHandler("/gerecht", gerechtController);
		
		IngredientController ingredientController = new IngredientController();
		server.registerHandler("/ingredient", ingredientController);

		BestellingController bestellingController = new BestellingController();
		server.registerHandler("/bestelling", bestellingController);

		SeizoenController seizoenController = new SeizoenController();
		server.registerHandler("/seizoen", seizoenController);

		server.start();
	}
}
