package controller;

import java.io.File;
import server.JSONFileServer;

public class Application {

	public static void main(String[] args) {
		
		JSONFileServer server = new JSONFileServer(new File("webapp"), 8888);
		server.start();
		
		InputController controller = new InputController();
		server.registerHandler("/example", controller);
		
	}

}
