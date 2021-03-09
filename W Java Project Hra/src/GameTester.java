import java.io.FileNotFoundException;
import java.io.IOException;

import shooter.Menu.Menu;

/**
 * Trieda sluzi na testovanie hry. 
 */
public class GameTester {

	public static void main(String[] args) throws IOException {
		// vytvor a pusti hru
		Menu hra = new Menu();

		hra.run();
		//hra.pustiHruBezMenu();
		//hra.pustiMapGen();
	}
}