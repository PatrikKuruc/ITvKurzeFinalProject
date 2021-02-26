package shooter.mapGen;

import javax.swing.JPanel;

import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class ContentPanelMapa extends JPanel {

	/**
	 * Create the panel.
	 */
	public ContentPanelMapa() {
		setBackground(Color.GRAY);
		setBounds(0, 0, 820, 710);
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 100, 800, 600);
		add(panel);
		
		
		panelMapy panelMapy = new panelMapy();
		add(panelMapy);
	}
}
