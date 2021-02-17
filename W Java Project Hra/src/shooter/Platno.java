package shooter;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.Timer;
/**
 * Trieda sluzi na vykreslovanie objektov hry
 */
public class Platno extends JPanel implements ActionListener{
	
	// casovac, ktory bude zodpovedny za aktualizacie a vykreslovanie objektov
	private Timer timer = new Timer(Settings.REFRESH_RATE, this);
	
	private static Player player;
	
	// Arraylist sluzi na jednoduchsie prekreslovanie objektov hry, kazdy objekt hry musi byt podtriedou JComponentu
	private ArrayList<JComponent> strely = new ArrayList<>();
	private ArrayList<JComponent> objektyMapy = new ArrayList<>();
	
	/**
	 * Vytvor panel
	 */
	public Platno() {
		// velkost platna nacita z nastaveni
		setPreferredSize(Settings.PANEL_SIZE);
		
		// vytvor hraca na suradniciach
		this.player = new Player(150,150,this);
		
		// vytvori posluchac pohybu mysky, prida ho na platno
		PohybMysou pohybMysou = new PohybMysou();
		addMouseMotionListener(pohybMysou);
		
		// vytvori posluchac klikania mysky, prida ho na platno
		ClickMysou myska = new ClickMysou(this.player);
		addMouseListener(myska);

		// spusti casovac
		timer.start();
	}

	// telo posluchaca, vykonava sa 60-krat za sekundu
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// prekresli sa cele platno (JPanel) nanovo
		repaint();	
	}
	
	/**
	 * Vykreslovanie platna
	 */
	protected void paintComponent(Graphics g) {
		// pri kazdom dalsom prekresleni treba najprv nakreslit JPanel nanovo
		super.paintComponent(g);
		
		// najprv nakresli mapu
		// zatial len nastavi pozadie, akoze trava :D
		setBackground(new Color(20,100,80));
		
		// nakresli objekty
		g.drawOval(50, 100, 50, 100);
		
		// nakresli hraca
		player.draw(g);
		
		// nakresli vsetky strely
		for (JComponent strela : strely) {
			((Strela) strela).draw(g);
		}

	}

	// prida objekt do listu podla typu
	public void spawn(JComponent objekt) {
		if (objekt instanceof Strela) {
			strely.add(objekt);
		}
	}

	// zmaze objekt z listu podla typu
	public void zmazObjekt(JComponent objekt) {
		if (objekt instanceof Strela) {
			strely.remove(objekt);
			//objekt.nezobrazuj()
		}
	}
}
