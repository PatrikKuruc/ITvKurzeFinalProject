package shooter.Menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JToggleButton;
import javax.swing.JTextField;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;

/**
 * Class Panel3_Settings creates settings for the game in the menu.
 */
public class Panel3_Settings extends JPanel {

	private JTextField textField;
	public static JButton btnVolume;
	 private Image imageON;
	Font font = new Font("Segoe Script", Font.BOLD, 20);

	/**
	 * Create the panel.
	 * 
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public Panel3_Settings() {

		setOpaque(false); //transparentny JPanel
		setBounds(300, 50, 350, 400);
		setBackground(Color.WHITE);
		setLayout(null);
		
		JTextArea ovladanie = new JTextArea();
		ovladanie.setBounds(20, 250, 200, 30);
		ovladanie.setFont(font);
		ovladanie.setForeground(Color.WHITE);
		ovladanie.setOpaque(false);
		ovladanie.setText("Control settings:");
		ovladanie.setEditable(false);
		add(ovladanie); 
		
		JComboBox<String> vyberOvladania = new JComboBox<>();
		vyberOvladania.setBounds(35, 290, 120, 20);
		//vyberOvladania.setForeground(Color.WHITE);
		//vyberOvladania.setOpaque(false);
		vyberOvladania.addItem("WASD");
		vyberOvladania.addItem("Arrows");
		add(vyberOvladania);
		
		JTextArea ovladaniePauzy = new JTextArea();
		ovladaniePauzy.setBounds(20, 320, 200, 30);
		ovladaniePauzy.setFont(font);
		ovladaniePauzy.setForeground(Color.WHITE);
		ovladaniePauzy.setOpaque(false);
		ovladaniePauzy.setText("Pause settings:");
		ovladaniePauzy.setEditable(false);
		add(ovladaniePauzy); 
		
		JComboBox<String> pauza = new JComboBox<>();
		pauza.setBounds(35, 360, 120, 20);
		//pauza.setForeground(Color.GREEN.darker().darker());
		pauza.addItem("P");
		pauza.addItem("Space");
		add(pauza);

		JTextArea speedSettings = new JTextArea();
		speedSettings.setBounds(20, 125, 200, 30);
		speedSettings.setFont(font);
		speedSettings.setForeground(Color.WHITE);
		speedSettings.setOpaque(false);
		speedSettings.setText("Speed settings:");
		speedSettings.setEditable(false);
		add(speedSettings);

		JSlider slider = new JSlider();
		slider.setBounds(35, 175, 200, 50);
		slider.setValue(20);
		slider.setMinimum(0);
		slider.setMaximum(200);
		slider.setMajorTickSpacing(50);
		slider.setMinorTickSpacing(25);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		slider.setForeground(Color.WHITE);
		slider.setOpaque(false);
		add(slider);

		JTextArea music = new JTextArea();
		music.setBounds(20, 10, 145, 30);
		music.setFont(font);
		music.setForeground(Color.WHITE);
		music.setOpaque(false);
		music.setText("Music on/off:");
		music.setEditable(false);
		add(music); 
		
		 // button for muting the menu music
        btnVolume = new JButton("");
        btnVolume.setBounds(175, 10, 30, 30);
        final boolean[] soundIsOn = {true};

        try {
            imageON = ImageIO.read(new File("obr/sound_on.png"));
            imageON = imageON.getScaledInstance(30, 30, Image.SCALE_FAST);
        } catch (IOException e) {
            e.printStackTrace();
        }

        btnVolume.setIcon(new ImageIcon(imageON));
        btnVolume.setBackground(Color.green);
        btnVolume.setVisible(true);

        btnVolume.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (soundIsOn[0]) {
                    Menu.soundEffect.setVolume(-80);
                    soundIsOn[0] = false;
                    btnVolume.setBackground(Color.red);

                } else {
                    Menu.soundEffect.setVolume(-20);
                    soundIsOn[0] = true;
                    btnVolume.setIcon(new ImageIcon(imageON));
                    btnVolume.setBackground(Color.green);
                }
            }
        });
        add(btnVolume);
        
    	JTextArea effects = new JTextArea();
    	effects.setBounds(20, 70, 200, 30);
    	effects.setFont(font);
    	effects.setForeground(Color.WHITE);
    	effects.setOpaque(false);
    	effects.setText("Sound effects:");
    	effects.setEditable(false);
		add(effects); 
		
		setVisible(false);

	}
}