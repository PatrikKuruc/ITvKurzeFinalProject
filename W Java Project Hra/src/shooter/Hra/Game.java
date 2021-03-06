package shooter.Hra;

import java.awt.BorderLayout;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.swing.JFrame;
/**
 * Trieda sluzi na vytvorenie okna hry.
 */
public class Game extends JFrame{
	
	private Properties gameProperties;
	private int WINDOW_HEIGHT;
	private int WINDOW_WIDTH;
	
	/**
	 * Vytvori okno (JFrame) hry
	 * @throws FileNotFoundException 
	 */
	public Game() throws FileNotFoundException {
		nacitajNastaveniaHry();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(WINDOW_HEIGHT, WINDOW_WIDTH);
		setLocationRelativeTo(null);
		setResizable(false);
		setTitle("Pow Pow Pow");
		
		// vytvor JPanel (platno) a prida ho do okna
		Platno hra = new Platno(gameProperties);
		add(hra, BorderLayout.CENTER);
	
		// vytvori a prida posluchac klavesnice do okna
		UserInput klavesnica = new UserInput(Platno.handler);
		addKeyListener(klavesnica);
		
		// prisposobi velkost okna JFrame velkosti komponentov (ich preferredSize) v okne (v nasom pripade sa prisposobi velkosti JPanelu - Platno)
		pack();
	}
	
	private void nacitajNastaveniaHry() {
		try {
			InputStream input = new FileInputStream("src/gameConfig.properties");
			gameProperties = new Properties();
			gameProperties.load(input);
			this.WINDOW_HEIGHT = Integer.parseInt(gameProperties.getProperty("WindowHeight"));
			this.WINDOW_WIDTH = Integer.parseInt(gameProperties.getProperty("WindowWidth"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * Spusti okno hry
	 */
	public void run() {
		setVisible(true);
	}

	/**
	 * Vypne okno hry.
	 */
	public void shutDown(){
		setVisible(false);
	}
}
