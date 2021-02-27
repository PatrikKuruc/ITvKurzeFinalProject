package shooter.mapGen;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;

import shooter.ObjektyHry.ObjektHry;

public class PanelVyber extends JPanel {

	private HandlerMapGen handler;
	private ArrayList<ObjektMapGen> zoznamObjektov = new ArrayList<>();
	
	/**
	 * Create the panel.
	 * @param handler 
	 */
	public PanelVyber(HandlerMapGen handler) {
		this.handler=handler;
		//this.zoznamObjektov = this.handler.getZoznamObjektov();
		setBounds(210, 10, 600, 80);
		
		vytvorZoznamObjektov();
	}

	private void vytvorZoznamObjektov() {
		add(new IconBtn(0.0, handler));
		add(new IconBtn(1.0, handler));
		add(new IconBtn(2.0, handler));
		add(new IconBtn(3.0, handler));
		add(new IconBtn(4.0, handler));
	}

	@Override
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		// TODO Auto-generated method stub
		super.paint(g2);
		for (int i = 0; i < zoznamObjektov.size(); i++) {
			ObjektMapGen obj = zoznamObjektov.get(i);
			obj.vykresli(g2);
		}
	}
	
	
}