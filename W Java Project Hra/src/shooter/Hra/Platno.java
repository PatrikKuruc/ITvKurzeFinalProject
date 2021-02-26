package shooter.Hra;

import java.awt.Graphics;
import java.io.FileNotFoundException;

import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * Trieda sluzi na vykreslovanie objektov hry
 */

public class Platno extends JPanel{
	
	// casovac, ktory bude zodpovedny za aktualizacie a vykreslovanie objektov
	private Timer timer = new Timer(Settings.REFRESH_RATE, e -> repaint());
	protected static Handler handler;
	
	/**
	 * Vytvor panel
	 * @throws FileNotFoundException 
	 */
	public Platno() throws FileNotFoundException{
		// vytvori handler a nahra mapu a objekty hry
		Platno.handler = new Handler();
		
		nahravacMapy nahravacMapy = new nahravacMapy(handler);
		nahravacMapy.nahrajMapu();
		
		setPreferredSize(Settings.PANEL_PLATNO_SIZE);
				
		// vytvori posluchac mysky, prida ho na platno
		UserInput UserInput = new UserInput(handler);
		addMouseListener(UserInput);
		addMouseMotionListener(UserInput);
		
		// spusti hru
		run();
	}



	/**
	 * Vykreslovanie komponentov platna
	 */
	protected void paintComponent(Graphics g) {
		// sem pride buffer ak ho budeme chciet pouzit
		super.paintComponent(g);
		handler.aktualizujObjektyHry();
		handler.vykresliObjektyHry(g);
	}

	/**
	 * Spusti hru
	 */
	public void run() {
		timer.start();
	}

}
