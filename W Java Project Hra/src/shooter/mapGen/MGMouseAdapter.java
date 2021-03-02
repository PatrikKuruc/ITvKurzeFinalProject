package shooter.mapGen;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class MGMouseAdapter extends MouseAdapter {

	private MGHandler handler;
	
	public MGMouseAdapter(MGHandler handler) {
		this.handler = handler;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		
		if (e.getButton() == 1) {
			double ID = handler.getKreslisObjektID();
			handler.pridajObjekt(ID, x, y);
			}
		if (e.getButton() == 3) {
			handler.zmazObjekty(x, y);
		}
	}
}