package shooter;

import java.awt.BorderLayout;

import javax.swing.JFrame;
/**
 * Trieda sluzi na vytvorenie okna a beh hry.
 */
public class Game extends JFrame {
	/**
	 * Vytvori okno (JFrame) hry
	 */
	public Game() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(Settings.WINDOW_HEIGHT, Settings.WINDOW_WIDTH);
		setLocationRelativeTo(null);
		setResizable(false);
		setTitle("Pow Pow Pow");

		// vytvor JPanel (platno) a prida ho do okna
		Platno hra = new Platno();
		add(hra, BorderLayout.CENTER);
		
		// vytvori a prida posluchac klavesnice do okna
		Klavesnica klavesnica = new Klavesnica();
		addKeyListener(klavesnica);
	
		// prisposobi velkost okna Jframe velkosti komponentov (preferredSize) v okne (v nasom pripade Jpanel - Platno)
		pack();
		// ked budeme chciet vytvorit menu, nebude treba nastavovat ziadne rozmery okien, len sa prida novy JPanel a okno sa prisposobi
	}
	/**
	 * zobrazi okno hry
	 */
	public void run() {
		setVisible(true);
	}

}