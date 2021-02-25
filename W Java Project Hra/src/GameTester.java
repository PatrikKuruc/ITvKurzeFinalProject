import java.io.FileNotFoundException;

import shooter.Hra.Game;

/**
 * Trieda sluzi na testovanie hry. 
 */
public class GameTester {

	public static void main(String[] args) throws FileNotFoundException {
		// vytvor a pusti hru
		Game hra = new Game();
		hra.run();
	}
}
