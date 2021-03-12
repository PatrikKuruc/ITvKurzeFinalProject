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
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;

public class Panel1_PlayerSettings extends JPanel {
	
	Font font = new Font("Segoe Script", Font.BOLD, 13);

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
		setBounds(300, 50, 350, 400);
		//setOpaque(false);
		setBackground(Color.white);
		setLayout(null);
		

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon(playerConfig.getProperty("ImagePath")));
		lblNewLabel.setBounds(200, 20, 69, 69);
		add(lblNewLabel);

		JTextArea playerChooser = new JTextArea();
		playerChooser.setBounds(20, 10, 165, 30);
		playerChooser.setFont(font);
		playerChooser.setForeground(Color.GREEN.darker().darker());
		playerChooser.setText("Choose character:");
		playerChooser.setEditable(false);
		add(playerChooser);

		String meno = playerConfig.getProperty("ImagePath");
		meno = meno.substring(0, meno.lastIndexOf("/"));
		meno = meno.substring(meno.lastIndexOf("/") + 1);

		JComboBox<String> comboBox = new JComboBox<>();
		
		comboBox.addItem("modry");
		comboBox.addItem("vojak");
		comboBox.addItem("zena");
		comboBox.addItem("starec");
		comboBox.setSelectedItem(meno);
		comboBox.setBounds(32, 40, 97, 23);
		comboBox.setForeground(Color.GREEN.darker().darker());
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

		
		
		setVisible(false);
		
		
		/*Border vonkajsi = BorderFactory.createEmptyBorder(20,20,20,20);
		//Border vnutorny = BorderFactory.createLineBorder(Color.DARK_GRAY);
		Border vnutorny = BorderFactory.createTitledBorder("Nastavenia hraca");
		Border zlozeny = BorderFactory.createCompoundBorder(vonkajsi, vnutorny); 
		setBorder(zlozeny);*/
		
		//setBorder(BorderFactory.createEtchedBorder());	// vystupeny panel, akoby schodik, pri obr. na pozadi nevidno
		
		
		
	}
}
