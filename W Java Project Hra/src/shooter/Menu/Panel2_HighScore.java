package shooter.Menu;

import shooter.Hra.Handler;
import shooter.Hra.SoundEffect;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Properties;

import javax.swing.*;
import javax.xml.crypto.Data;

public class Panel2_HighScore extends JPanel {

	private JLabel lblNumber,lblName, lblScore, lblDamageTaken, lblTime, lblFilter;
	private JPanel pnlPanelInfo;
	private JButton btnScore, btnDamageTaken, btnTime;
	public static JTextArea txtPlayerNumber, txtPlayerName, txtScore, txtDamageTaken, txtTime;
	private Database database;
	private SoundEffect soundEffect;
	Handler handler;
	public static final boolean[] scoreMAX = {true};
	public static final boolean[] damageTakenMIN = {true};
	public static final boolean[] timeMIN = {true};

	/**
	 * Create the panel.
	 */
	public Panel2_HighScore() {
		setBackground(Color.lightGray);
		setBounds(300, 50, 450, 400);
		setLayout(null);
		setVisible(false);

		pnlPanelInfo = new JPanel();
		pnlPanelInfo.setBounds(0,0,450,30);
		pnlPanelInfo.setLayout(null);
		pnlPanelInfo.setVisible(true);
		pnlPanelInfo.setBackground(Color.lightGray);
		pnlPanelInfo.setBorder(BorderFactory.createLineBorder(new Color(180,0,0), 5, false));

		lblNumber = new JLabel("N.");
		lblNumber.setBounds(30, 5, 100,20);

		lblName = new JLabel("Name");
		lblName.setBounds(90, 5, 100,20);

		lblScore = new JLabel("Score");
		lblScore.setBounds(190, 5, 100,20);

		lblDamageTaken = new JLabel("DMG Taken");
		lblDamageTaken.setBounds(275, 5, 100,20);

		lblTime = new JLabel("Time");
		lblTime.setBounds(385, 5, 100,20);

		lblFilter = new JLabel("CHOOSE FILTER: ");
		lblFilter.setBounds(30, 370, 100,20);

		txtPlayerNumber = new JTextArea();
		txtPlayerNumber.setBounds(5,30, 50, 335);
		txtPlayerNumber.setBackground(Color.lightGray);
		txtPlayerNumber.setEditable(false);
		txtPlayerNumber.setBorder(BorderFactory.createEtchedBorder());

		txtPlayerName = new JTextArea();
		txtPlayerName.setBounds(55,30, 100, 335);
		txtPlayerName.setBackground(Color.lightGray);
		txtPlayerName.setEditable(false);
		txtPlayerName.setBorder(BorderFactory.createEtchedBorder());

		txtScore = new JTextArea();
		txtScore.setBounds(155,30, 100, 335);
		txtScore.setBackground(Color.lightGray);
		txtScore.setEditable(false);
		txtScore.setBorder(BorderFactory.createEtchedBorder());

		txtDamageTaken = new JTextArea();
		txtDamageTaken.setBounds(255,30, 100, 335);
		txtDamageTaken.setBackground(Color.lightGray);
		txtDamageTaken.setEditable(false);
		txtDamageTaken.setBorder(BorderFactory.createEtchedBorder());

		txtTime = new JTextArea();
		txtTime.setBounds(355,30, 90, 335);
		txtTime.setBackground(Color.lightGray);
		txtTime.setEditable(false);
		txtTime.setBorder(BorderFactory.createEtchedBorder());

		btnScore = new JButton("SCORE");
		btnScore.setBounds(155,365,100,30);

		btnScore.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				soundEffect = new SoundEffect();
				soundEffect.setFileButtonClick();
				soundEffect.play();

				try {
					database = new Database(handler);
					database.resetHighScore();
					database.selectDataScore();
				} catch (SQLException throwables) {
					throwables.printStackTrace();
				}
			}
		});

		btnDamageTaken = new JButton("DMG");
		btnDamageTaken.setBounds(255,365,100,30);

		btnDamageTaken.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				soundEffect = new SoundEffect();
				soundEffect.setFileButtonClick();
				soundEffect.play();

				try {
					database = new Database(handler);
					database.resetHighScore();
					database.selectDataDamage();
				} catch (SQLException throwables) {
					throwables.printStackTrace();
				}
			}
		});

		btnTime = new JButton("TIME");
		btnTime.setBounds(355,365,90,30);

		btnTime.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				soundEffect = new SoundEffect();
				soundEffect.setFileButtonClick();
				soundEffect.play();

				try {
					database = new Database(handler);
					database.resetHighScore();
					database.selectDataTime();
				} catch (SQLException throwables) {
					throwables.printStackTrace();
				}
			}
		});

		pnlPanelInfo.add(lblNumber);
		pnlPanelInfo.add(lblName);
		pnlPanelInfo.add(lblScore);
		pnlPanelInfo.add(lblDamageTaken);
		pnlPanelInfo.add(lblTime);
		add(lblFilter);
		add(txtPlayerNumber);
		add(txtPlayerName);
		add(txtScore);
		add(txtDamageTaken);
		add(txtTime);
		add(pnlPanelInfo);
		add(btnScore);
		add(btnDamageTaken);
		add(btnTime);
		
		setBorder(BorderFactory.createLineBorder(new Color(180,0,0), 5, false));   // okraj - farba, hrubka, zaoblenie
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
