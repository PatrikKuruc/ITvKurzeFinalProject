package shooter.Menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JToggleButton;
import javax.swing.JTextField;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

/**
 * Class Panel3_Settings creates settings for the game in the menu.
 */
public class Panel3_Settings extends JPanel {

	private JTextField textField;
	Font font = new Font("Segoe Script", Font.BOLD, 13);

	/**
	 * Create the panel.
	 * 
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public Panel3_Settings() {

		Properties gameConfig = new Properties();

		try {

			FileInputStream fis;
			gameConfig.load(fis = new FileInputStream("src/gameConfig.properties"));
			fis.close();

		} catch (IOException e2) {

			e2.printStackTrace();
		}

		// setOpaque(false); //transparentny JPanel
		setBounds(300, 50, 350, 400);
		setBackground(Color.WHITE);
		setLayout(null);

		JTextArea difficultySetting = new JTextArea();
		difficultySetting.setBounds(20, 232, 165, 30);
		difficultySetting.setFont(font);
		difficultySetting.setForeground(Color.GREEN.darker().darker());
		difficultySetting.setText(" Set the difficulty level :");
		difficultySetting.setEditable(false);
		add(difficultySetting);

		ButtonGroup bg = new ButtonGroup();

		JRadioButton rdbtnNewRadioButton = new JRadioButton("Beginner");
		rdbtnNewRadioButton.setBounds(45, 264, 109, 23);
		rdbtnNewRadioButton.setForeground(Color.GREEN.darker().darker());
		rdbtnNewRadioButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					gameConfig.setProperty("Difficulty", "1");

					FileOutputStream fos;
					gameConfig.store(fos = new FileOutputStream("src/gameConfig.properties"), null);
					fos.close();
				}

				catch (IOException vynimka1) {

				}

			}
		});
		add(rdbtnNewRadioButton);

		bg.add(rdbtnNewRadioButton);

		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Middle");
		rdbtnNewRadioButton_1.setOpaque(false);
		rdbtnNewRadioButton_1.setBounds(45, 293, 109, 23);
		rdbtnNewRadioButton_1.setForeground(Color.GREEN.darker().darker());
		rdbtnNewRadioButton_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					gameConfig.setProperty("Difficulty", "1.3");

					FileOutputStream fos;
					gameConfig.store(fos = new FileOutputStream("src/gameConfig.properties"), null);
					fos.close();
				}

				catch (IOException vynimka1) {

				}

			}
		});

		add(rdbtnNewRadioButton_1);
		bg.add(rdbtnNewRadioButton_1);

		JRadioButton rdbtnNewRadioButton_2 = new JRadioButton("Advanced");
		rdbtnNewRadioButton_2.setBounds(45, 319, 109, 23);
		rdbtnNewRadioButton_2.setForeground(Color.GREEN.darker().darker());
		rdbtnNewRadioButton_2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					gameConfig.setProperty("Difficulty", "1.5");

					FileOutputStream fos;
					gameConfig.store(fos = new FileOutputStream("src/gameConfig.properties"), null);
					fos.close();
				}

				catch (IOException vynimka1) {

				}

			}
		});
		add(rdbtnNewRadioButton_2);
		bg.add(rdbtnNewRadioButton_2);

		double difficulty = Double.parseDouble(gameConfig.getProperty("Difficulty"));

		if (difficulty == 1) {
			rdbtnNewRadioButton.setSelected(true);
		} else if (difficulty == 1.3) {
			rdbtnNewRadioButton_1.setSelected(true);
		} else
			rdbtnNewRadioButton_2.setSelected(true);

		
		JTextArea ovladanie = new JTextArea();
		ovladanie.setBounds(25, 10, 165, 30);
		ovladanie.setFont(font);
		ovladanie.setForeground(Color.GREEN.darker().darker());
		ovladanie.setText("Control settings:");
		ovladanie.setEditable(false);
		add(ovladanie); 
		
		JComboBox<String> vyberOvladania = new JComboBox<>();
		vyberOvladania.setBounds(20, 50, 120, 20);
		vyberOvladania.setForeground(Color.GREEN.darker().darker());
		vyberOvladania.addItem("WASD");
		vyberOvladania.addItem("Arrows");
		add(vyberOvladania);

		
		JTextArea ovladaniePauzy = new JTextArea();
		ovladaniePauzy.setBounds(200, 10, 165, 30);
		ovladaniePauzy.setFont(font);
		ovladaniePauzy.setForeground(Color.GREEN.darker().darker());
		ovladaniePauzy.setText("Pause settings:");
		ovladaniePauzy.setEditable(false);
		add(ovladaniePauzy); 
		
		JComboBox<String> pauza = new JComboBox<>();
		pauza.setBounds(200, 50, 120, 20);
		pauza.setForeground(Color.GREEN.darker().darker());
		pauza.addItem("P");
		pauza.addItem("Space");
		add(pauza);

		JTextArea speedSettings = new JTextArea();
		speedSettings.setBounds(25, 100, 200, 25);
		speedSettings.setFont(font);
		speedSettings.setForeground(Color.GREEN.darker().darker());
		speedSettings.setText("Speed settings:");
		speedSettings.setEditable(false);
		add(speedSettings);

		JSlider slider = new JSlider();
		slider.setBounds(35, 140, 200, 50);
		slider.setValue(20);
		slider.setMinimum(0);
		slider.setMaximum(200);
		slider.setMajorTickSpacing(50);
		slider.setMinorTickSpacing(25);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		slider.setForeground(Color.GREEN.darker().darker());
		add(slider);

		setVisible(false);

	}
}