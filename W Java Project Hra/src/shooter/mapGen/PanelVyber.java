package shooter.mapGen;

import javax.swing.JPanel;
import javax.swing.Timer;

public class PanelVyber extends JPanel {

	private Timer timer = new Timer(60, e -> repaint());
	private MGHandler handler;
	
	
	/**
	 * Create the panel.
	 * @param handler 
	 */
	public PanelVyber(MGHandler handler) {
		this.handler=handler;
		setBounds(210, 10, 600, 80);
		
		vytvorZoznamObjektov();
		timer.start();
	}

	private void vytvorZoznamObjektov() {
		add(new ObjectBtn(0.0, handler));
		add(new ObjectBtn(1.0, handler));
		add(new ObjectBtn(2.0, handler));
		add(new ObjectBtn(3.0, handler));
		add(new ObjectBtn(4.1, handler));
		add(new ObjectBtn(4.2, handler));
	}
}