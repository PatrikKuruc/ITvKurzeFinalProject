package shooter.mapGen;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MyskaVyber extends MouseAdapter{
	
	private HandlerMapGen handler;
	
	public MyskaVyber(HandlerMapGen handler) {
		this.handler = handler;
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		System.out.println("Vyberas objekt");
	}

}
