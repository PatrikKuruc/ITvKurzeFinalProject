package shooter;

import java.awt.Dimension;

/**
 * Trieda sluzi na ukladanie nastaveni hry
 */

public class Settings {
	
	public static final int WINDOW_HEIGHT = 1024;
	public static final int WINDOW_WIDTH = 800;
	public static final Dimension PANEL_SIZE = new Dimension(WINDOW_HEIGHT, WINDOW_WIDTH);

	public static final int FPS = 120;
	public static final int REFRESH_RATE = 1000/FPS; // v ms, 1000/60 zabezpeci 60 FPS
}
