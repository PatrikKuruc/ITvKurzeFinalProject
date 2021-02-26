package shooter.Hra;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Trieda Mys sluzi na ukladanie a ziskanie vstupov od pouzivatela.
 * Kombinuje KeyListener a MouseAdapter
 * Mouse Adapter implementuje MouseListener (click mysou), MouseWheelListener (scrollovanie koleckom mysi) a MouseMotionListener (pohyb mysi).
 */

public class UserInput extends MouseAdapter implements KeyListener{
	
	private static boolean up = false, down = false, left = false, right = false;
	private static int mouseX;
	private static int mouseY;
	private Handler handler;
	
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
			setLeft(true);
		}
		if (keyPressed == 'd') {
			setRight(true);
		}
		if (keyPressed == 'w') {
			setUp(true);
		}
		if (keyPressed == 's') {
			setDown(true);
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
			setLeft(false);
		}
		if (keyReleased == 'd') {
			setRight(false);
		}
		if (keyReleased == 'w') {
			setUp(false);
		}
		if (keyReleased == 's') {
			setDown(false);
		}
	}
	
	/**
	 * Vykona, ak sa kurzor mysi pohne ale nie su ziadne tlacidla mysi stlacene 
	 */
	@Override
	public void mouseMoved(MouseEvent e) {
		setMouseX(e.getX());
		setMouseY(e.getY());
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
	
	public static boolean isUp() {
		return up;
	}

	public void setUp(boolean up) {
		this.up = up;
	}

	public static boolean isDown() {
		return down;
	}

	public void setDown(boolean down) {
		this.down = down;
	}

	public static boolean isLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}

	public static boolean isRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

	public static int getMouseX() {
		return mouseX;
	}

	public void setMouseX(int mouseX) {
		this.mouseX = mouseX;
	}

	public static int getMouseY() {
		return mouseY;
	}

	public void setMouseY(int mouseY) {
		this.mouseY = mouseY;
	}
}
