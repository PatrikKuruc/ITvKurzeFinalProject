package shooter.mapGen;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JPanel;
import javax.swing.Timer;

import shooter.Hra.Settings;

public class PanelMapa extends JPanel{

	private Timer timer = new Timer(60, e -> repaint());
	private Myska mys;
	private HandlerMapGen handler;
	private ArrayList<Rectangle> mriezka = new ArrayList<>();
	
	private ArrayList<ObjektMapGen> objekty1 = new ArrayList<>();
	private ArrayList<ObjektMapGen> objekty2 = new ArrayList<>();

	public PanelMapa(HandlerMapGen handler) {
		this.handler=handler;
		setBounds(10, 110, 800, 592);
		setLayout(null);
		vytvorMriezku();
		timer.start();
		
		objekty1 = handler.getDefaultMap();
		objekty2 = handler.getNewMap();
		
		this.mys = new Myska(handler);
		addMouseListener(mys);
		addMouseMotionListener(mys);
	}

	private void vytvorMriezku() {
		int velkostPolicka = 16;
		int vRiadku = 800/velkostPolicka;
		int vStlpci = 592/velkostPolicka;
		for (int i = 0; i < vRiadku; i++) {
			for (int j = 0; j < vStlpci; j++) {
				mriezka.add(new Rectangle(i*16, j*16, 16, 16));
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
		
		for (int i = 0; i < objekty2.size(); i++) {
			objekty2.get(i).vykresli(g2);
		}
	}

	public void stavNaDefaultMap() {
		objekty2.clear();
		objekty2.addAll(0, objekty1);
	}

	public void zmazMapu() {
		objekty2.clear();
	}
}
