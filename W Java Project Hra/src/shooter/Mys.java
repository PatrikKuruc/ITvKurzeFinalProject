package shooter;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import shooter.objektyHry.Handler;

/**
 * Trieda Mys sluzi na ziskanie vstupov mysi
 */

public class Mys extends MouseAdapter{
	
	Handler handler;
	
	public Mys(Handler handler) {
		this.handler = handler;
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
