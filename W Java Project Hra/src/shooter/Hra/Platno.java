package shooter.Hra;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.FileNotFoundException;
import java.util.Properties;

import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * Trieda sluzi na vykreslovanie objektov hry
 */

public class Platno extends JPanel{
	
	// casovac, ktory bude zodpovedny za aktualizacie a vykreslovanie objektov
	private Timer timer;
	protected static Handler handler;
	
	private Properties gameProperties;
	private int FPS;
	private int Height;
	private int Width;
	
	/**
	 * Vytvor panel
	 * @param gameProperties 
	 * @throws FileNotFoundException 
	 */
	public Platno(Properties gameProperties) throws FileNotFoundException{
		this.gameProperties = gameProperties;
		this.FPS = Integer.parseInt(gameProperties.getProperty("FPS"));
		this.Height = Integer.parseInt(gameProperties.getProperty("WindowHeight"));
		this.Width = Integer.parseInt(gameProperties.getProperty("WindowWidth"));
		this.timer= new Timer(FPS/10, e -> repaint());
		this.timer= new Timer(FPS/100, e -> repaint());
		
		setLayout(null);
		setPreferredSize(new Dimension(Height,Width));
		
		// vytvori handler
		Platno.handler = new Handler(timer);

		// nahra mapu a objekty hry
		nahravacMapy nahravacMapy = new nahravacMapy(handler);
		nahravacMapy.nahrajMapu();
				
		// vytvori posluchac mysky, prida ho na platno
		UserInput UserInput = new UserInput(handler);
		addMouseListener(UserInput);
		addMouseMotionListener(UserInput);
		
		// prida sa casovac hry
		new Casovac(this, handler);

		//spusti hru
		run();
	}

	/**
	 * Vykreslovanie komponentov platna
	 */
	protected void paintComponent(Graphics g2) {
		Graphics2D g = (Graphics2D) g2;
		// sem pride buffer ak ho budeme chciet pouzit
		super.paintComponent(g);
		handler.aktualizujObjektyHry();
		
		//g.translate(-(handler.getPoziciaHracaX()-Width/2), -(handler.getPoziciaHracaY()-Height/2));
		handler.vykresliObjektyHry(g);
		handler.vykresliHraca(g);
		//g.translate((handler.getPoziciaHracaX()-Width/2), (handler.getPoziciaHracaY()-Height/2));
		
	}

	/**
	 * Spusti hru
	 */
	public void run() {
		timer.start();
	}
}
