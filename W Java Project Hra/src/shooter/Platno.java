package shooter;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import javax.swing.JPanel;
import javax.swing.Timer;

import shooter.objektyHry.Handler;

/**
 * Trieda sluzi na vykreslovanie objektov hry
 */

public class Platno extends JPanel implements ActionListener{
	
	// casovac, ktory bude zodpovedny za aktualizacie a vykreslovanie objektov
	private Timer timer = new Timer(Settings.REFRESH_RATE, this);
	private static Handler handler;
	
	/**
	 * Vytvor panel
	 * @throws FileNotFoundException 
	 */
	public Platno() throws FileNotFoundException {
		// vytvori handler a nahra mapu a objekty hry
		Platno.handler = new Handler(this);
		handler.nahraj();
		
		setPreferredSize(Settings.PANEL_SIZE);
				
		// vytvori posluchac mysky, prida ho na platno
		Mys Mys = new Mys(handler);
		addMouseListener(Mys);
		addMouseMotionListener(Mys);
		
		// spusti hru
		run();
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		// telo posluchaca, FPS-krat za sekundu aktualizuje objekty hry a prekresli platno
		handler.aktualizujObjektyHry();
		repaint();
	}

	/**
	 * Vykreslovanie komponentov platna
	 */
	protected void paintComponent(Graphics g) {
		// buffer
		
		// pri kazdom dalsom prekresleni treba najprv nakreslit JPanel nanovo
		super.paintComponent(g);
		
		handler.vykresliObjektyHry(g);
	}

	/**
	 * Spusti hru
	 */
	public void run() {
		timer.start();
	}

	public static Handler getHandler() {
		return handler;
	}
}
