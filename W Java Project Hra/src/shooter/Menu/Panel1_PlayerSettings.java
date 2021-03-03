package shooter.Menu;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.BorderFactory;

public class Panel1_PlayerSettings extends JPanel {

	/**
	 * Create the panel.
	 */
	public Panel1_PlayerSettings() {
		setBounds(300, 50, 350, 400);
		setOpaque(false);
		setBackground(Color.white);
		setLayout(null);
		setVisible(false);
		
		
		Border vonkajsi = BorderFactory.createEmptyBorder(20,20,20,20);
		//Border vnutorny = BorderFactory.createLineBorder(Color.DARK_GRAY);
		Border vnutorny = BorderFactory.createTitledBorder("Nastavenia hraca");
		Border zlozeny = BorderFactory.createCompoundBorder(vonkajsi, vnutorny); 
		setBorder(zlozeny);
		
		//setBorder(BorderFactory.createEtchedBorder());	// vystupeny panel, akoby schodik, pri obr. na pozadi nevidno
	}
}
