package shooter.Menu;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JToggleButton;
import javax.swing.JTextField;
import javax.swing.JSlider;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

public class Panel3_Settings extends JPanel {
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public Panel3_Settings() {
		//setOpaque(false);	//transparentny JPanel
		setBounds(300, 50, 350, 400);
		setBackground(Color.YELLOW);
		setLayout(null);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("New radio button");
		rdbtnNewRadioButton.setBounds(157, 264, 109, 23);
		add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("New radio button");
		rdbtnNewRadioButton_1.setOpaque(false);
		rdbtnNewRadioButton_1.setBounds(157, 293, 109, 23);
		add(rdbtnNewRadioButton_1);
		
		JRadioButton rdbtnNewRadioButton_2 = new JRadioButton("New radio button");
		rdbtnNewRadioButton_2.setBounds(157, 319, 109, 23);
		add(rdbtnNewRadioButton_2);
		
		JToggleButton tglbtnNewToggleButton = new JToggleButton("New toggle button");
		tglbtnNewToggleButton.setOpaque(false);
		tglbtnNewToggleButton.setBounds(204, 81, 121, 23);
		add(tglbtnNewToggleButton);
		
		textField = new JTextField();
		textField.setBounds(43, 149, 86, 23);
		add(textField);
		textField.setColumns(10);
		
		JToggleButton tglbtnNewToggleButton_1 = new JToggleButton("New toggle button");
		tglbtnNewToggleButton_1.setBounds(204, 115, 121, 23);
		add(tglbtnNewToggleButton_1);
		
		JToggleButton tglbtnNewToggleButton_2 = new JToggleButton("New toggle button");
		tglbtnNewToggleButton_2.setBounds(204, 149, 121, 23);
		add(tglbtnNewToggleButton_2);
		
		JSlider slider = new JSlider();
		slider.setBounds(36, 225, 200, 26);
		add(slider);
		
		JComboBox<String> comboBox = new JComboBox();
		comboBox.addItem("modry");
		comboBox.addItem("vojak");
		comboBox.addItem("zena");
		comboBox.addItem("dedo");
		comboBox.setBounds(32, 12, 97, 23);
		add(comboBox);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon("D:\\ITvKurzeFinalProject\\W Java Project Hra\\obr\\hrac\\modry\\3.png"));
		lblNewLabel.setBounds(43, 46, 69, 69);
		add(lblNewLabel);
		setVisible(false);
		
	}
}