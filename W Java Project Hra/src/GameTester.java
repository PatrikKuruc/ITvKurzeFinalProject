import java.io.IOException;

import shooter.Menu.Menu;

/**
 * Class GameTester tests the game.
 */
public class GameTester {

	public static void main(String[] args) throws IOException {
		// creates and runs the game
		Menu game = new Menu();

		//game.run();
		//game.runWithoutMenu();
		game.runMapGen();
	}
}
