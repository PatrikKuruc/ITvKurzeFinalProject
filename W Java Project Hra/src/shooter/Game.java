package shooter;

import java.awt.BorderLayout;
import java.io.FileNotFoundException;

import javax.swing.JFrame;
/**
 * Trieda sluzi na vytvorenie okna aplikacie.
 */
public class Game extends JFrame{
	/**
	 * Vytvori okno (JFrame) hry
	 * @throws FileNotFoundException 
	 */
	public Game() throws FileNotFoundException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(Settings.WINDOW_HEIGHT, Settings.WINDOW_WIDTH);
		setLocationRelativeTo(null);
		setResizable(false);
		setTitle("Pow Pow Pow");
			
		// vytvor JPanel (platno) a prida ho do okna
		Platno hra = new Platno();
		add(hra, BorderLayout.CENTER);
	
		// vytvori a prida posluchac klavesnice do okna
		UserInput klavesnica = new UserInput(Platno.handler);
		addKeyListener(klavesnica);
		
		// prisposobi velkost okna JFrame velkosti komponentov (ich preferredSize) v okne (v nasom pripade sa prisposobi velkosti JPanelu - Platno)
		pack();
	}
	/**
	 * zobrazi okno hry
	 */
	public void run() {
		setVisible(true);
	}
}
