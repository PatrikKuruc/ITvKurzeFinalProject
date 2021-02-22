package shooter;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelListener;

import shooter.Handler;

/**
 * Trieda Mys sluzi na ziskanie vstupov mysi.
 * Kombinuje KeyListener a MouseAdapter
 * Mouse Adapter implementuje MouseListener (click mysou), MouseWheelListener (scrollovanie koleckom mysi) a MouseMotionListener (pohyb mysi).
 */

public class UserInput extends MouseAdapter implements KeyListener{
	
	Handler handler;
	
	public UserInput(Handler handler) {
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
	
	/**
	 * Vykona, ak sa kurzor mysi pohne ale nie su ziadne tlacidla mysi stlacene 
	 */
	@Override
	public void mouseMoved(MouseEvent e) {
		handler.setMouseX(e.getX());
		handler.setMouseY(e.getY());
	}
	
	/**
	 * Vykona, ak je mys stlacena nad komponentom a kurzor mysi sa pohne. Prestane vykonavat po pusteni tlacidla mysi.
	 */
	@Override
	public void mouseDragged(MouseEvent e) {
	}
	
	/**
	 * Vykonava prikazy po kliknuti tlacidla mysi (stlac a pusti)
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		// e.getButton(); - vrati integer, podla toho ktorym tlacidlom mysi kliknes
		// 1 = lave tlacidlo mysi
		// 2 = stredne tlacidlo mysi
		// 3 = prave tlacidlo mysi
	}

	/**
	 * Vykonava prikazy po stlaceni tlacidla mysi
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		handler.playerShoot();
    }
	
	/**
	 * Vykonava prikazy po pusteni tlacidla mysi
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
	}

	/**
	 * Vykonava prikazy ak kurzor mysi vojde na komponent
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
	}

	/**
	 * Vykonava prikazy ak kurzor mysi opusti komponent
	 */
	@Override
	public void mouseExited(MouseEvent e) {
	}
}
