package shooter.mapGen;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class GeneratorMapy extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public void run() {
		try {
			GeneratorMapy frame = new GeneratorMapy();
			frame.setVisible(true);
			frame.setLocationRelativeTo(null);
			frame.setResizable(true);
			frame.setTitle("Map Generator");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the frame.
	 */
	public GeneratorMapy() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0,0, 900, 800);
		contentPane = new ContentPanelMapa();
		setContentPane(contentPane);
	}

}
