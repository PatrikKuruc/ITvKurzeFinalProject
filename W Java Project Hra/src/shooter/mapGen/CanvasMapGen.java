package shooter.mapGen;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.LinkedList;

import javax.swing.JPanel;
import javax.swing.Timer;

public class CanvasMapGen extends JPanel{

	private Timer timer = new Timer(60, e -> repaint());
	private MouseAdapterMapGen mouseAdapter;
	private HandlerMapGen handler;
	
	private LinkedList<Rectangle> mriezka;

	public CanvasMapGen(HandlerMapGen handler) {
		this.handler=handler;
		setBounds(10, 110, 1024, 800);
		setLayout(null);
		vytvorMriezku();
		timer.start();
		
		this.mouseAdapter = new MouseAdapterMapGen(handler);
		addMouseListener(mouseAdapter);
		addMouseMotionListener(mouseAdapter);
	}

	private void vytvorMriezku() {
		mriezka = new LinkedList<>();
		int velkostPolicka = handler.velkostPolicka;
		int vRiadku = 1024/handler.velkostPolicka;
		int vStlpci = 800/handler.velkostPolicka;
		for (int i = 0; i < vRiadku; i++) {
			for (int j = 0; j < vStlpci; j++) {
				//mriezka.add(new Rectangle(i*handler.velkostPolicka, j*handler.velkostPolicka, handler.velkostPolicka, handler.velkostPolicka));
			}
		}
	}

	@Override
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		// TODO Auto-generated method stub
		super.paint(g2);
		handler.drawObjects(g2);
	}
}