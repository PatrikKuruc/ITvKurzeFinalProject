package shooter.Menu;

import java.awt.Color;

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
}
