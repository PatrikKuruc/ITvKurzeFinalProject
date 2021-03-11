package shooter.Menu;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.*;

import shooter.Game.Game;
import shooter.Game.SoundEffect;
import shooter.mapGen.GeneratorMapy;

public class Menu extends JFrame {
	
	private ContentPanel contentPane;
	public static SoundEffect soundEffect;

	public Menu() throws IOException {
		setResizable(false);
		setTitle("Nemame nazov :/");
		setSize(new Dimension(800, 600));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(null);
		
		contentPane = new ContentPanel(this);
		setContentPane(contentPane);
	}
	
	/**
	 * Spusti menu.
	 */
	public void run() {
		setVisible(true);
		soundEffect = new SoundEffect();
		soundEffect.setFileMenuMusic();
		soundEffect.play();
	}

	/**
	 * Vypne menu.
	 */
	public void shutDown(){
		setVisible(false);
		soundEffect.stopMusic();
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
