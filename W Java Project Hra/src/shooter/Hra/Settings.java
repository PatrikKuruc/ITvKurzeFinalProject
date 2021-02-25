package shooter.Hra;

import java.awt.Dimension;

/**
 * Trieda sluzi na ukladanie nastaveni hry
 */

public class Settings {
	
	public static final int WINDOW_HEIGHT = 1024;
	public static final int WINDOW_WIDTH = 800;
	public static final Dimension PANEL_PLATNO_SIZE = new Dimension(WINDOW_HEIGHT, WINDOW_WIDTH);
	public static final Dimension PANEL_MENU_SIZE = new Dimension(WINDOW_HEIGHT/2, WINDOW_WIDTH/2);

	public static final int FPS = 98;
	public static final int REFRESH_RATE = 1000/FPS; // v ms, 1000/60 zabezpeci 60 FPS
	
	public static double playerSpeed = 5;
	public static double enemySpeed = 1;
}
