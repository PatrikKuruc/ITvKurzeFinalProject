package shooter.mapGen;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class MyskaKresli extends MouseAdapter {

	private HandlerMapGen handler;
	
	public MyskaKresli(HandlerMapGen handler) {
		this.handler = handler;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		
		if(e.getButton() == 1) {
			handler.pridajObjekt(1.0, x, y);
		}
		else if (e.getButton() == 2) {
			handler.pridajObjekt(3.0, x, y);
		}
		else if(e.getButton() == 3) {
			handler.pridajObjekt(0.0, x, y);
		}
	}
}
