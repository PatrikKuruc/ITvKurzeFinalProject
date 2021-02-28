package shooter.mapGen;

import java.io.FileNotFoundException;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GeneratorMapy extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 * @throws FileNotFoundException 
	 */
	public void run(){
		GeneratorMapy frame = new GeneratorMapy();
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.setResizable(true);
		frame.setTitle("Map Generator");
	}

	/**
	 * Create the frame.
	 * @throws FileNotFoundException 
	 */
	public GeneratorMapy(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0,0, 840, 750);
		contentPane = new GMContentPanel();
		setContentPane(contentPane);
		
		
	}

}