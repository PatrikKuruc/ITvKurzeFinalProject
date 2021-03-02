package shooter.mapGen;

import javax.swing.JPanel;

import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import java.awt.Component;

public class MGContentPanel extends JPanel {
	
	private MGHandler handler;

	/**
	 * Create the panel.
	 * @throws FileNotFoundException 
	 */
	public MGContentPanel(){
		setBackground(Color.GRAY);
		setBounds(0, 0, 1024, 910);
		setLayout(null);
		handler = new MGHandler();
		
		PanelMapa panelMapa = new PanelMapa(handler);
		add(panelMapa);
		
		PanelVyber panelVyber = new PanelVyber(handler);
		add(panelVyber);
		
		
		JButton btnNewButton = new JButton("Nacitaj Default Map");
		btnNewButton.addActionListener(e -> handler.stavNaDefaultMap());
		btnNewButton.setBounds(10, 10, 175, 20);
		add(btnNewButton);
		
		JButton btnZresetujMapu = new JButton("Zmaz Mapu");
		btnZresetujMapu.setBounds(10, 40, 175, 20);
		btnZresetujMapu.addActionListener(e -> handler.zmazMapu());
		add(btnZresetujMapu);
		
		JButton btnUlozMapu = new JButton("Uloz Mapu");
		btnUlozMapu.addActionListener(e -> handler.ulozMapu());
		btnUlozMapu.setBounds(10, 70, 175, 20);
		add(btnUlozMapu);
		
		JTextPane txtpnVyberObjektKtory = new JTextPane();
		txtpnVyberObjektKtory.setText("Vyber objekt ktory chces vlozit na mapu (hore) a lavym kliknutim ho pridas, pravym zmazes");
		txtpnVyberObjektKtory.setBounds(210, 90, 600, 20);
		txtpnVyberObjektKtory.setEditable(false);;
		add(txtpnVyberObjektKtory);
	}
}