package shooter.Hra;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.JLabel;
import javax.swing.Timer;


/**
 * Trieda Casovac sluzi ako stopky, ktory meraju dobu hrania vo formate minuty:sekundy.
 */
public class Casovac {

    JLabel timeLabel;
    Timer timer;
    int second;
    int minute;
    static String ddSecond, ddMinute;

    // naformatovanie casu na 00:00
    DecimalFormat decimalFormat = new DecimalFormat("00");
	private Handler handler;

    /**
     * Zostroji sa casovac
     *
     * @param platno platno vykreslovania
     * @param handler 
     */
    public Casovac(Platno platno, Handler handler) {
    	this.handler = handler;
        timeLabel = new JLabel();
        timeLabel.setBounds(890, 5, 100, 20);
        timeLabel.setBackground(Color.cyan);
        timeLabel.setOpaque(true);
        
        platno.add(timeLabel);
        timeLabel.setText("           00:00");
        second = 0;
        minute = 0;

        Timer();
        timer.start();
    }

    /**
     * Spusti sa casovac, ktory bude pocitat (minuty:sekundy) dobu hrania
     */
    public void Timer() {
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if (handler.isBezi()) {
            		second++;
                    ddSecond = decimalFormat.format(second);
                    ddMinute = decimalFormat.format(minute);

                    timeLabel.setText("           " + ddMinute + ":" + ddSecond);

                    if (second == 60) {

                        second = 0;
                        minute++;

                        ddSecond = decimalFormat.format(second);
                        ddMinute = decimalFormat.format(minute);

                        timeLabel.setText("           " + ddMinute + ":" + ddSecond);
                    }
				}
            }
        });
    }

    public static String getDdSecond() {
        return ddSecond;
    }

    public static String getDdMinute() {
        return ddMinute;
    }
}
