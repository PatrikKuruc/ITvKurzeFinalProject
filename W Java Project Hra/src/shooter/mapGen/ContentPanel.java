package shooter.mapGen;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import java.io.FileNotFoundException;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JList;

public class ContentPanel extends JPanel {
	
	private HandlerMapGen handler;

	/**
	 * Create the panel.
	 * @throws FileNotFoundException 
	 */
	public ContentPanel(){
		setBackground(Color.GRAY);
		setBounds(0, 0, 1024, 910);
		setLayout(null);
		handler = new HandlerMapGen();
		
		createMapGenButtons();
		createMapGenItems();
		createMapGenCanvas();
	}
	
	private void createMapGenItems() {
		PanelVyber panelVyber = new PanelVyber(handler);
		add(panelVyber);
		
		JScrollPane scrollPane = new JScrollPane(panelVyber);
		scrollPane.setBounds(200, 10, 650, 80);
		add(scrollPane);
		/*
		String ItemsList[]= {"Walls","Ground","Enemy","Items","Player"};
		JList<String> comboBox = new JList<>(ItemsList);
		
		JScrollPane pane = new JScrollPane(comboBox);
		pane.setBounds(900, 10, 110, 70);
		add(pane);
		
		JButton loadItemsButton = new JButton("<<");
		loadItemsButton.setBounds(850, 30, 50, 30);
		loadItemsButton.addActionListener(e -> panelVyber.setVybranyZoznam(comboBox.getSelectedValue()));
		add(loadItemsButton);
		*/
	}
	
	private void createMapGenButtons() {
		JButton btnNewButton = new JButton("Nacitaj Default Map");
		btnNewButton.addActionListener(e -> handler.setDefaultMap());
		btnNewButton.setBounds(10, 10, 175, 20);
		add(btnNewButton);
		
		JButton btnZresetujMapu = new JButton("Zmaz Mapu");
		btnZresetujMapu.setBounds(10, 40, 175, 20);
		btnZresetujMapu.addActionListener(e -> handler.deleteCurrentMap());
		add(btnZresetujMapu);
		
		JButton btnUlozMapu = new JButton("Uloz Mapu");
		btnUlozMapu.addActionListener(e -> handler.saveMap());
		btnUlozMapu.setBounds(10, 70, 175, 20);
		add(btnUlozMapu);
	}
	
	private void createMapGenCanvas() {
		CanvasMapGen panelMapa = new CanvasMapGen(handler);
		add(panelMapa);
	}
}