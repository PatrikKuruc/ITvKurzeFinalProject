package shooter.mapGen;

import java.awt.Graphics2D;
import java.awt.Label;
import java.util.ArrayList;

import javax.swing.JLabel;

import shooter.ObjektyHry.ObjektHry;

public class HandlerMapGen {
	
	private ArrayList<ObjektJComp> defaultMap;
	//private ArrayList<ObjektMapGen> zoznamObjektov;
	private ArrayList<ObjektJComp> newMap;
	private double typObjektu = 1.0;
	protected int velkostPolicka = 32;
	
	private JLabel DOLabel;
	
	public HandlerMapGen() {
		defaultMap = new ArrayList<>();
		vytvorDefaultneObjektyMapy();
		
		//zoznamObjektov = new ArrayList<>();
		//vytvorZoznamObjektov();
		
		newMap = new ArrayList<>();
	}

	private void vytvorDefaultneObjektyMapy() {
		int vRiadku = 800/velkostPolicka;
		int vStlpci = 592/velkostPolicka;
		for (int i = 0; i < vRiadku; i++) {
			for (int j = 0; j < vStlpci; j++) {
				if (i == 0 || j == 0 || i == vRiadku-1 || j == vStlpci-1) {
					defaultMap.add(new ObjektJComp(1.0, i*velkostPolicka, j*velkostPolicka, this));
				}
				else {
					defaultMap.add(new ObjektJComp(0.0, i*velkostPolicka, j*velkostPolicka, this));
				}
			}
		}
	}
	
	public void pridajObjekt(double ID, int x, int y) {
		typObjektu = ID;
		int pozX = x/velkostPolicka;
		int pozY = y/velkostPolicka;
		newMap.add(new ObjektJComp(typObjektu, pozX*velkostPolicka, pozY*velkostPolicka, this));
	}
	
	public ArrayList<ObjektJComp> getNewMap() {
		return newMap;
	}
/*	
	public ArrayList<ObjektMapGen> getZoznamObjektov() {
		return zoznamObjektov;
	}
*/
	public ArrayList<ObjektJComp> getDefaultMap() {
		return defaultMap;
	}
}