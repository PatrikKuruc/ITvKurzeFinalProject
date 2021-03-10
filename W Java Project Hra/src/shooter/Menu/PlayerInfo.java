package shooter.Menu;

import shooter.Hra.Casovac;
import shooter.Hra.Handler;
import shooter.Hra.SoundEffect;

import javax.swing.*;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.sql.SQLException;

/**
 * Trieda PlayerInfo sluzi na zbieranie a ukladanie udajov o hracovi do textoveho suboru.
 */
public class PlayerInfo extends JFrame {

    private JPanel panel;
    private JLabel lblName, lblDamageTaken, lblTime, lblScore, lblBackRound, lblGameOver;
    private JButton btnSave;
    public static JTextField txtName;
    Handler handler;
    private Database database;
    private SoundEffect soundEffect;

    /**
     * Vytovri sa okno PlayerInfo, kde sa zobrazia udaje o ukoncenej hre, vyziada si meno od hraca a nasledne ich ulozi.
     *
     * @param handler handler
     */
    public PlayerInfo(Handler handler) {
        this.handler = handler;

        setResizable(false);
        setTitle("Player Info");
        setSize(new Dimension(800, 600));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        panel = new JPanel();
        panel.setLayout(null);

        lblBackRound = new JLabel("");
        lblBackRound.setIcon(new ImageIcon("obr/bg.jpg"));
        lblBackRound.setBounds(0, 0, 800, 600);

        lblGameOver = new JLabel("GAME OVER");
        lblGameOver.setBounds(100, 75, 400, 40);
        lblGameOver.setForeground(Color.white);
        lblGameOver.setFont(new Font("Forte", Font.BOLD, 40));

        lblName = new JLabel("Name: ");
        lblName.setBounds(100, 150, 100, 20);
        lblName.setForeground(Color.white);

        lblScore = new JLabel("Score: " + handler.score);
        lblScore.setBounds(100, 200, 100, 20);
        lblScore.setForeground(Color.white);

        lblDamageTaken = new JLabel("Damage Taken: " + handler.zranenia);
        lblDamageTaken.setBounds(100, 250, 200, 20);
        lblDamageTaken.setForeground(Color.white);

        lblTime = new JLabel("Time: " + Casovac.getDdMinute() + ":" + Casovac.getDdSecond());
        lblTime.setBounds(100, 300, 250, 20);
        lblTime.setForeground(Color.white);

        txtName = new JTextField();
        txtName.setBounds(150, 150, 100, 20);
        txtName.setDocument(new JTextFieldLimit(5));    // limituje dlzku mena na 5 znakov

        btnSave = new JButton("SAVE");
        btnSave.setBounds(100, 350, 100, 40);

        // prida udaje o hracovi do databazy a nasledne sa zobrazi uvodne menu hry s highscore tabulkou
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                soundEffect = new SoundEffect();
                soundEffect.setFileButtonClick();
                soundEffect.play();

                if (txtName.getText().isEmpty() || txtName.getText().contains(" ")) {
                    JOptionPane.showMessageDialog(null, "Name can't be empty!\nName can't contain spaces!",
                            "NAME ERROR", JOptionPane.INFORMATION_MESSAGE);
                } else {

                    try {
                        database = new Database(handler);
                        database.insertData();
                        database.resetHighScore();
                        database.selectDataScore();

                        shutDown();

                        ContentPanel.menu.run();
                        ContentPanel.panel1.setVisible(false);
                        ContentPanel.panel2.setVisible(true);
                        ContentPanel.panel3.setVisible(false);
                        Panel2_HighScore.scoreMAX[0] = true;
                        ContentPanel.btnVolume.setBackground(Color.green);

                    } catch (SQLException fileNotFoundException) {
                        fileNotFoundException.printStackTrace();
                    }
                }

            }
        });

        panel.add(lblGameOver);
        panel.add(lblName);
        panel.add(txtName);
        panel.add(lblScore);
        panel.add(lblDamageTaken);
        panel.add(lblTime);
        panel.add(btnSave);

        add(panel, BorderLayout.CENTER);
        panel.add(lblBackRound);
    }

    /**
     * Spusti okno PlayerInfo.
     */
    public void run() {
        setVisible(true);
    }

    /**
     * Vypne okno PlayerInfo.
     */
    public void shutDown() {
        setVisible(false);
    }

}
