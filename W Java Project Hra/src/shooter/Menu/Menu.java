package shooter.Menu;

import java.awt.Dimension;
import java.io.FileNotFoundException;

import javax.swing.JFrame;

import shooter.Hra.Game;
import shooter.mapGen.GeneratorMapy;

public class Menu extends JFrame {
	
	private ContentPanel contentPane;

	public Menu() {
		setResizable(false);
		setTitle("Nemame nazov :/");
		setSize(new Dimension(800, 600));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		contentPane = new ContentPanel(this);
		setContentPane(contentPane);
	}
	
	/**
	 * Spusti menu
	 */
	public void run() {
		setVisible(true);
	}

	/**
	 * Spusti hru
	 */
	public void pustiHruBezMenu() {
		try {
			Game game = new Game();
			game.run();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public void pustiMapGen() {
		GeneratorMapy game = new GeneratorMapy();
		game.run();
	}
}
