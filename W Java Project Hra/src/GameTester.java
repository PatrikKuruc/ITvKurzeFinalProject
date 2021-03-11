import java.io.IOException;

import shooter.Menu.Menu;

/**
 * Class GameTester tests the game.
 */
public class GameTester {

	public static void main(String[] args) throws IOException {
		// creates and runs the game
		Menu hra = new Menu();

		hra.run();
		//hra.pustiHruBezMenu();
		//hra.pustiMapGen();
	}
}