package shooter.mapGen;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import shooter.Hra.Settings;

public class PanelMapa extends JPanel{

	private Timer timer = new Timer(60, e -> repaint());
	private MGMouseAdapter mys;
	private MGHandler handler;
	
	private LinkedList<Rectangle> mriezka;

	public PanelMapa(MGHandler handler) {
		this.handler=handler;
		setBounds(10, 110, 1024, 800);
		setLayout(null);
		vytvorMriezku();
		timer.start();
		
		this.mys = new MGMouseAdapter(handler);
		addMouseListener(mys);
		addMouseMotionListener(mys);
	}

	private void vytvorMriezku() {
		mriezka = new LinkedList<>();
		int velkostPolicka = handler.velkostPolicka;
		int vRiadku = 1024/handler.velkostPolicka;
		int vStlpci = 800/handler.velkostPolicka;
		for (int i = 0; i < vRiadku; i++) {
			for (int j = 0; j < vStlpci; j++) {
				mriezka.add(new Rectangle(i*handler.velkostPolicka, j*handler.velkostPolicka, handler.velkostPolicka, handler.velkostPolicka));
			}
		}
	}

	@Override
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		// TODO Auto-generated method stub
		super.paint(g2);
		for (int i = 0; i < mriezka.size(); i++) {
			g2.draw(mriezka.get(i).getBounds());
		}
		
		handler.vykresliObjekty(g2);
	}
}