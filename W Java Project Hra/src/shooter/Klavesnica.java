package shooter;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import shooter.objektyHry.Handler;

/**
 * Trieda sluzi na spracovanie vstupov z klavesnice
 */

public class Klavesnica implements KeyListener{
		
	Handler handler;

	/**
	 * 
	 * @param handler
	 */
	public Klavesnica(Handler handler) {
		this.handler = handler;
	}

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
			handler.setLeft(true);
		}
		if (keyPressed == 'd') {
			handler.setRight(true);
		}
		if (keyPressed == 'w') {
			handler.setUp(true);
		}
		if (keyPressed == 's') {
			handler.setDown(true);
		}
	}

	/**
	 * Vykona prikazy ak je tlacidlo uvolnene
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		// zresetuje vektory pohybu
		char keyReleased = e.getKeyChar();
		if (keyReleased == 'a') {
			handler.setLeft(false);
		}
		if (keyReleased == 'd') {
			handler.setRight(false);
		}
		if (keyReleased == 'w') {
			handler.setUp(false);
		}
		if (keyReleased == 's') {
			handler.setDown(false);
		}
	}
}
