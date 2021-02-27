package shooter.mapGen;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import shooter.ObjektyHry.ObjektHry;

public class PanelVyber extends JPanel {

	private Timer timer = new Timer(60, e -> repaint());
	private HandlerMapGen handler;
	private ArrayList<ObjektJComp> zoznamObjektov = new ArrayList<>();
	
	
	/**
	 * Create the panel.
	 * @param handler 
	 */
	public PanelVyber(HandlerMapGen handler) {
		this.handler=handler;
		//this.zoznamObjektov = this.handler.getZoznamObjektov();
		setBounds(210, 10, 600, 80);
		
		vytvorZoznamObjektov();
		timer.start();
	}

	private void vytvorZoznamObjektov() {
		add(new ObjectBtn(0.0, handler));
		add(new ObjectBtn(1.0, handler));
		add(new ObjectBtn(2.0, handler));
		add(new ObjectBtn(3.0, handler));
		add(new ObjectBtn(4.0, handler));
	}

	@Override
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		// TODO Auto-generated method stub
		super.paint(g2);
		for (int i = 0; i < zoznamObjektov.size(); i++) {
			ObjektJComp obj = zoznamObjektov.get(i);
			obj.vykresli(g2);
		}
	}
}