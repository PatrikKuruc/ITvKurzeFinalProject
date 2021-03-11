package shooter.Menu;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JToggleButton;
import javax.swing.JTextField;
import javax.swing.JSlider;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

/**
 * Class Panel3_Settings creates settings for the game in the menu.
 */
public class Panel3_Settings extends JPanel {

    private JTextField textField;

    /**
     * Creates the game settings section.
     */
    public Panel3_Settings() {

        Properties p = new Properties();

        try {
            FileInputStream fis;
            p.load(fis = new FileInputStream("src/playerConfig.properties"));
            fis.close();
        } catch (IOException e2) {
            e2.printStackTrace();
        }

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

        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setIcon(new ImageIcon(p.getProperty("ImagePath")));
        lblNewLabel.setBounds(43, 46, 69, 69);
        add(lblNewLabel);

        JComboBox<String> comboBox = new JComboBox();
        comboBox.addItem("modry");
        comboBox.addItem("vojak");
        comboBox.addItem("zena");
        comboBox.addItem("dedo");
        comboBox.setBounds(32, 12, 97, 23);
        comboBox.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                String hrac = comboBox.getItemAt(comboBox.getSelectedIndex());
                lblNewLabel.setIcon(new ImageIcon("obr/hrac/" + hrac + "/3.png"));
                try {

                    p.setProperty("ImagePath", "obr/hrac/" + hrac + "/3.png");

                    FileOutputStream fos;
                    p.store(fos = new FileOutputStream("src/playerConfig.properties"), null);
                    fos.close();
                } catch (IOException e1) {

                    e1.printStackTrace();
                }


            }
        });

        add(comboBox);
        setVisible(false);
    }
}
