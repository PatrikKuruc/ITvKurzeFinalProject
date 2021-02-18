package shooter;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
/**
 * Trieda sluzi na spracovanie vstupu z tlacidiel mysi.
 */
public class ClickMysou implements MouseListener {

	private Player player;

	/**
	 * Vytvori mouse listener
	 * @param player hrac
	 */
	public ClickMysou(Player player) {
		this.player = player;
	}
	/**
	 * Vykonava prikazy po kliknuti tlacidla
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	/**
	 * Vykonava prikazy po stisnuti tlacidla
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		//e.getButton(); - vrati integer, podla toho ktorym tlacidlom mysi kliknes
		// 1 = lave tlacidlo mysi
		// 2 = stredne tlacidlo mysi
		// 3 = prave tlacidlo mysi
		
		System.out.println(e.getButton());
		int clicked = e.getButton();
		if (clicked == 1) {
			player.shoot();
		}
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
