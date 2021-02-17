package shooter;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Trieda sluzi na spracovanie vstupov z klavesnice
 */
public class Klavesnica implements KeyListener{
		
	// vektory pohybu
	private static final int[] Move = {0,0};

	/**
	 * Vykona prikazy po kliknuti tlacidla
	 */
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	/**
	 * Vykona prikazy ak je tlacidla stlacene
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		// aktualizuje vektory pohybu podla stlacenej klavesnice
		char keyPressed = e.getKeyChar();
		if (keyPressed == 'a') {
			Move[1] = -1;
		}
		if (keyPressed == 'd') {
			Move[1] = 1;
		}
		if (keyPressed == 'w') {
			Move[0] = -1;
		}
		if (keyPressed == 's') {
			Move[0] = 1;
		}
	}

	/**
	 * Vykona prikazy ak je tlacidlo uvolnene
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		// zresetuje vektory pohybu
		char keyPressed = e.getKeyChar();
		if (keyPressed == 'a') {
			Move[1] = 0;
		}
		if (keyPressed == 'd') {
			Move[1] = 0;
		}
		if (keyPressed == 'w') {
			Move[0] = 0;
		}
		if (keyPressed == 's') {
			Move[0] = 0;
		}
	}

	/**
	 * Vrati vektor pohybu
	 * @return Move vektory pohybu
	 */
	public static int[] getMove() {
		return Move;
	}
	
}
