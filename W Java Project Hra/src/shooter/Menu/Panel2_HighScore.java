package shooter.Menu;

import java.awt.Color;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class Panel2_HighScore extends JPanel {

	/**
	 * Create the panel.
	 */
	public Panel2_HighScore() {
		setBackground(Color.LIGHT_GRAY);
		setOpaque(false);
		setBounds(300, 50, 350, 400);
		setLayout(null);
		setVisible(false);
		
		setBorder(BorderFactory.createLineBorder(Color.YELLOW, 5, true));   // okraj - farba, hrubka, zaoblenie
	}
	
	
	public void nacitajProperties() {
		try {
			//nacitanie properties:
			InputStream input = new FileInputStream("src/playerConfig.properties");
			Properties playerProp = new Properties();
			playerProp.load(input);
			
			//praca s properties
			playerProp.setProperty("test", "5");
			
			
			int test = Integer.parseInt(playerProp.getProperty("test"));
			System.out.println(test);
			
			
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
}
