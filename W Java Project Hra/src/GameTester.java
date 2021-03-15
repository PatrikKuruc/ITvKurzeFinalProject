import java.io.BufferedReader;
import java.io.*;

import java.nio.charset.*;

import junit.framework.Test;
import shooter.Menu.Menu;

/**
 * Class GameTester tests the game.
 */
public class GameTester {

	public static void main(String[] args) throws IOException {
		/*
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(Test.class.getResourceAsStream("/resources/gameConfig.properties"),StandardCharsets.UTF_8))) {
	            String line;
	            while ((line = reader.readLine()) != null) {
	                System.out.println(line);
	            }
	        }
		//alebo
		//getClass
		//getResource
		*/
		// creates and runs the game
		Menu game = new Menu();

		game.run();
	}
}