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
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;

/**
 * Class Panel1_PlayerSettings creates settings for player in the menu.
 */
public class Panel1_PlayerSettings extends JPanel {
	
	Font font = new Font("Segoe Script", Font.BOLD, 20);


	/**
	 * Create the panel.
	 */
	public Panel1_PlayerSettings() {
		
		Properties playerConfig = new Properties();
		

		try {

			FileInputStream fis;
			playerConfig.load(fis = new FileInputStream("src/playerConfig.properties"));
			fis.close();

			
		} catch (IOException e2) {

			e2.printStackTrace();
		}
		
		Properties gameConfig = new Properties();

		try {

			FileInputStream fis;
			gameConfig.load(fis = new FileInputStream("src/gameConfig.properties"));
			fis.close();

		} catch (IOException e2) {

			e2.printStackTrace();
		}
		
		setBounds(300, 50, 350, 400);
		setOpaque(false);
		setBackground(Color.white);
		setLayout(null);
		

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon(playerConfig.getProperty("ImagePath")));
		lblNewLabel.setBounds(32, 330, 70, 70);
		add(lblNewLabel);

		JTextArea playerChooser = new JTextArea();
		playerChooser.setBounds(20, 250, 200, 30);
		playerChooser.setFont(font);
		playerChooser.setForeground(Color.WHITE);
		playerChooser.setOpaque(false);
		playerChooser.setText("Choose character:");
		playerChooser.setEditable(false);
		add(playerChooser);

		String meno = playerConfig.getProperty("ImagePath");
		meno = meno.substring(0, meno.lastIndexOf("/"));
		meno = meno.substring(meno.lastIndexOf("/") + 1);

		JComboBox<String> comboBox = new JComboBox<>();
		
		comboBox.addItem("civil");
		comboBox.addItem("citizen");
		comboBox.addItem("default");
		comboBox.addItem("grandpa");
		comboBox.addItem("hitman");
		comboBox.addItem("robot");
		comboBox.addItem("soldier");
		comboBox.addItem("woman");
		comboBox.setSelectedItem(meno);
		comboBox.setBounds(32, 300, 100, 23);
		//comboBox.setForeground(Color.WHITE);
		comboBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String hrac = comboBox.getItemAt(comboBox.getSelectedIndex());
				lblNewLabel.setIcon(new ImageIcon("obr/hrac/" + hrac + "/3.png"));
				try {

					playerConfig.setProperty("ImagePath", "obr/hrac/" + hrac + "/3.png");

					FileOutputStream fos;
					playerConfig.store(fos = new FileOutputStream("src/playerConfig.properties"), null);
					fos.close();
				}

				catch (IOException e1) {

					e1.printStackTrace();
				}

			}
		});

		add(comboBox);


		JTextArea difficultySetting = new JTextArea();
		difficultySetting.setBounds(20, 120, 300, 30);
		difficultySetting.setFont(font);
		difficultySetting.setForeground(Color.WHITE);
		difficultySetting.setText(" Set the difficulty level :");
		difficultySetting.setEditable(false);
		difficultySetting.setOpaque(false);
		add(difficultySetting);

		ButtonGroup bg = new ButtonGroup();

		JRadioButton rdbtnNewRadioButton = new JRadioButton("Beginner");
		rdbtnNewRadioButton.setBounds(45, 150, 109, 23);
		rdbtnNewRadioButton.setForeground(Color.WHITE);
		rdbtnNewRadioButton.setOpaque(false);
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
		rdbtnNewRadioButton_1.setBounds(45, 173, 109, 23);
		rdbtnNewRadioButton_1.setForeground(Color.WHITE);
		rdbtnNewRadioButton_1.setOpaque(false);
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
		rdbtnNewRadioButton_2.setBounds(45, 196, 109, 23);
		rdbtnNewRadioButton_2.setForeground(Color.WHITE);
		rdbtnNewRadioButton_2.setOpaque(false);
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

		
		setVisible(false);
		
		
		/*Border vonkajsi = BorderFactory.createEmptyBorder(20,20,20,20);
		//Border vnutorny = BorderFactory.createLineBorder(Color.DARK_GRAY);
		Border vnutorny = BorderFactory.createTitledBorder("Nastavenia hraca");
		Border zlozeny = BorderFactory.createCompoundBorder(vonkajsi, vnutorny); 
		setBorder(zlozeny);*/
		
		//setBorder(BorderFactory.createEtchedBorder());	// vystupeny panel, akoby schodik, pri obr. na pozadi nevidno
		
		
		
	}


}
