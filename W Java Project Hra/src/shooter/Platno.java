package shooter;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * Trieda sluzi na vykreslovanie objektov hry
 */
public class Platno extends JPanel implements ActionListener, Runnable{
	
	// casovac, ktory bude zodpovedny za aktualizacie a vykreslovanie objektov
	private Timer timer = new Timer(Settings.REFRESH_RATE, this);
	
	private static Handler handler;
	private Player player;
	
	private Klavesnica klavesnica;
	
	/**
	 * Vytvor panel
	 * @param klavesnica 
	 */
	public Platno() {
		this.handler = new Handler(this);
		
		// velkost platna nacita z nastaveni
		setPreferredSize(Settings.PANEL_SIZE);
				
		// vytvori posluchac pohybu mysky, prida ho na platno
		Mys Mys = new Mys(handler);
		addMouseListener(Mys);
		addMouseMotionListener(Mys);
		
		handler.nahrajPozadie();
		handler.nahrajObjekty();
		
		handler.addObject(new Player(100,100,this,handler));
		run();
	}

	// telo posluchaca, vykonava sa 60-krat za sekundu

	@Override
	public void actionPerformed(ActionEvent e) {
		// prekresli sa cele platno (JPanel) nanovo
		handler.aktualizujObjektyHry();
		repaint();
	}

	/**
	 * Vykreslovanie platna
	 */
	protected void paintComponent(Graphics g) {
		// chyba: buffer TODO:
		
		// pri kazdom dalsom prekresleni treba najprv nakreslit JPanel nanovo
		super.paintComponent(g);
		
		// najprv nakresli mapu (staticke objekty)
		setBackground(new Color(20,100,80));
		
		// nakresli objekty (pohyblive objekty - v zmysle, veci ktore menia svoju poziciu alebo stav - cize aj itemy
		g.drawOval(50, 100, 50, 100);
		
		// nakresli hraca
		handler.vykresli(g);
	}

	@Override
	public void run() {
		// spusti timer
		timer.start();
	}

	public static Handler getHandler() {
		return handler;
	}
}
