package shooter.mapGen;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Myska extends MouseAdapter {

	private HandlerMapGen handler;
	
	public Myska(HandlerMapGen handler) {
		this.handler = handler;
	}

	
	@Override
	public void mousePressed(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		handler.pridajObjekt(x,y);
	}
}
