package shooter.mapGen;

import java.awt.Graphics2D;
import java.awt.Label;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.JLabel;

import shooter.ObjektyHry.ObjektHry;

public class HandlerMapGen {
	
	private LinkedList<ObjektJComp> defaultMap;
	private LinkedList<ObjektJComp> newMap;
	private double typObjektu = 1.0;
	protected int velkostPolicka = 16;
	
	private JLabel DOLabel;
	private double kreslisObjektID;
	
	public HandlerMapGen() {
		defaultMap = new LinkedList<>();
		vytvorDefaultneObjektyMapy();
		
		//zoznamObjektov = new ArrayList<>();
		//vytvorZoznamObjektov();
		
		newMap = new LinkedList<>();
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
	
	public void zmazObjekty(int x, int y) {
		int pozX = x/velkostPolicka;
		int pozY = y/velkostPolicka;
		System.out.println(pozX + " " + pozY);
		for (ObjektJComp objektJComp : newMap) {
			ObjektJComp obj = objektJComp;
			if (obj.getPoziciaX()/velkostPolicka == pozX && obj.getPoziciaY()/velkostPolicka==pozY) {
				newMap.remove(obj);
			}
		}
	}
	
	public LinkedList<ObjektJComp> getNewMap() {
		return newMap;
	}

	public double getKreslisObjektID() {
		return kreslisObjektID;
	}

	public void setKreslisObjektID(double kreslisObjektID) {
		this.kreslisObjektID = kreslisObjektID;
	}

	public LinkedList<ObjektJComp> getDefaultMap() {
		return defaultMap;
	}
}