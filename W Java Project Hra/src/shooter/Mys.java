package shooter;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import shooter.objektyHry.Handler;


/**
 * Sluzi na ziskanie suradnic kurzora mysi
 */
public class Mys extends MouseAdapter{
	
	Handler handler;
	
	public Mys(Handler handler) {
		this.handler = handler;
	}


	@Override
	public void mouseMoved(MouseEvent e) {
		handler.setMouseX(e.getX());
		handler.setMouseY(e.getY());
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
	}
	/**
	 * Vykonava prikazy po kliknuti tlacidla
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		//e.getButton(); - vrati integer, podla toho ktorym tlacidlom mysi kliknes
		// 1 = lave tlacidlo mysi
		// 2 = stredne tlacidlo mysi
		// 3 = prave tlacidlo mysi
	}

	/**
	 * Vykonava prikazy po stisnuti tlacidla
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		handler.playerShoot();
    }
	
	/**
	 * Vykonava prikazy po pusteni stlaceneho tlacidla
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
	}

	/**
	 * Vykonava prikazy ak kurzor mysi vojde do okna aplikacie
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
	}

	/**
	 * Vykonava prikazy ak kurzor mysi opusti okno aplikacie
	 */
	@Override
	public void mouseExited(MouseEvent e) {
	}
}
