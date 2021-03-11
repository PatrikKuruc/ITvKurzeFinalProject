package shooter.Menu;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

import shooter.Hra.Game;
import shooter.Hra.Handler;
import shooter.Hra.SoundEffect;
import shooter.mapGen.GeneratorMapy;

import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class ContentPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public static Panel1_PlayerSettings panel1;
	public static Panel2_HighScore panel2;
	public static Panel3_Settings panel3;
	public static Menu menu;
	public static Game game;
	private Database database;
	private SoundEffect soundEffect;
	Handler handler;
	private Image imageON;
	public static JButton btnVolume;

	
	public ContentPanel(Menu menu) {
		ContentPanel.menu = menu;
		setLayout(null);
		setBounds(0, 0, 800, 600);			//netreba, len kvoli windowdesigneru
		
		nacitajpanely();
		createButtons();
		nacitajpozadie();
	}

	private void nacitajpanely() {
		panel1 = new Panel1_PlayerSettings();
		panel2 = new Panel2_HighScore();
		panel3 = new Panel3_Settings();
		
		add(panel1);
		add(panel2);
		add(panel3);
	}

	private void nacitajpozadie() {
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("obr/bg.jpg"));
		lblNewLabel.setBounds(0, 0, 800, 600);
		add(lblNewLabel);
	}

	private void createButtons() {
		JButton btnNewButton = new JButton("New Game");
		btnNewButton.setBounds(100, 150, 150, 30);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				soundEffect = new SoundEffect();
				soundEffect.setFileButtonClick();
				soundEffect.play();

				menu.shutDown();
				try {
					game = new Game();
					game.run();
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Player Settings");
		btnNewButton_1.setBounds(100, 200, 150, 30);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				soundEffect = new SoundEffect();
				soundEffect.setFileButtonClick();
				soundEffect.play();

				panel1.setVisible(true);
				panel2.setVisible(false);
				panel3.setVisible(false);
			}
		});
		add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("High Score");
		btnNewButton_2.setBounds(100, 250, 150, 30);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				soundEffect = new SoundEffect();
				soundEffect.setFileButtonClick();
				soundEffect.play();

				panel1.setVisible(false);
				panel2.setVisible(true);
				panel3.setVisible(false);

				try {
					database = new Database(handler);
					database.resetHighScore();
					database.selectDataScore();
				} catch (SQLException throwables) {
					throwables.printStackTrace();
				}
			}
		});
		add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Settings");
		btnNewButton_3.setBounds(100, 300, 150, 30);
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				soundEffect = new SoundEffect();
				soundEffect.setFileButtonClick();
				soundEffect.play();

				panel1.setVisible(false);
				panel2.setVisible(false);
				panel3.setVisible(true);
			}
		});
		add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Map Generator");
		btnNewButton_4.setBounds(100, 350, 150, 30);
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				soundEffect = new SoundEffect();
				soundEffect.setFileButtonClick();
				soundEffect.play();

				menu.setVisible(false);
				GeneratorMapy mapGen;
				mapGen = new GeneratorMapy();
				mapGen.run();
			}
		});
		add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("Exit");
		btnNewButton_5.setBounds(100, 400, 150, 30);
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(1);
			}
		});
		add(btnNewButton_5);

		// tlacitku pre "mute" hudby v menu
		btnVolume = new JButton("");
		btnVolume.setBounds(10,10,30,30);
		final boolean[] soundIsOn = {true};

		try {
			imageON = ImageIO.read(new File("obr/sound_on.png"));
			imageON = imageON.getScaledInstance(30, 30, Image.SCALE_FAST);
		} catch (IOException e) {
			e.printStackTrace();
		}

		btnVolume.setIcon(new ImageIcon(imageON));
		btnVolume.setBackground(Color.green);
		btnVolume.setVisible(true);

		btnVolume.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				if(soundIsOn[0]){
					Menu.soundEffect.setVolume(-80);
					soundIsOn[0] = false;
					btnVolume.setBackground(Color.red);

				} else{
					Menu.soundEffect.setVolume(-20);
					soundIsOn[0] = true;
					btnVolume.setIcon(new ImageIcon(imageON));
					btnVolume.setBackground(Color.green);
				}
			}
		});
		add(btnVolume);

	}
}
