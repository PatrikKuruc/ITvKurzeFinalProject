package shooter.mapGen;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseAdapterMapGen extends MouseAdapter {

	private HandlerMapGen handler;
	
	public MouseAdapterMapGen(HandlerMapGen handler) {
		this.handler = handler;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		if (e.getButton() == 1) {
			double ID = handler.getSelectedItemID();
			handler.addObjetOnCanvas(ID, x, y);
			}
		if (e.getButton() == 3) {
			handler.deleteObjectsAt(x, y);
		}
	}
}