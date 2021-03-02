package shooter.mapGen;

import java.awt.Graphics2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.LinkedList;

import javax.swing.JLabel;

import shooter.ObjektyHry.ObjektHry;

public class MGHandler {
	
	private LinkedList<ObjektJComp> defaultMap;
	private LinkedList<ObjektJComp> newMap;
	private double typObjektu = 1.0;
	protected int velkostPolicka = 8;
	
	private double kreslisObjektID;
	private PrintWriter zapisovac;
	
	public MGHandler() {
		defaultMap = new LinkedList<>();
		newMap = new LinkedList<>();
		vytvorDefaultnuMapu();
	}

	private void vytvorDefaultnuMapu() {
		int vRiadku = 800/velkostPolicka;
		int vStlpci = (600- velkostPolicka)/velkostPolicka;
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
		for (ObjektJComp objektJComp : newMap) {
			ObjektJComp obj = objektJComp;
			if (obj.getPoziciaX()/velkostPolicka == pozX && obj.getPoziciaY()/velkostPolicka==pozY) {
				newMap.remove(obj);
			}
		}
	}
	
	public void stavNaDefaultMap() {
		newMap.addAll(defaultMap);
	}

	public void zmazMapu() {
		newMap.removeAll(newMap);
	}
	
	public void vykresliObjekty(Graphics2D g2) {
		for (ObjektJComp objektJComp : newMap) {
			objektJComp.vykresli(g2);
		}
	}
	

	public double getKreslisObjektID() {
		return kreslisObjektID;
	}

	public void setKreslisObjektID(double kreslisObjektID) {
		this.kreslisObjektID = kreslisObjektID;
	}

	public void ulozMapu() {
		try {
			zapisovac = new PrintWriter("mapy/newMap.txt");
			System.out.println("Subor newMap.txt vytvoreny");
		} catch (FileNotFoundException e) {
		}
		
		zapisovac.write("velkost policka: " + velkostPolicka + "\n");
		for (ObjektJComp objektMapy : newMap) {
			zapisovac.write("ObjektID: " + objektMapy.getID() + " PoziciaX: " + objektMapy.getPoziciaX() + " PoziciaY: " + objektMapy.getPoziciaY() + " Width: " + velkostPolicka + " Height: " + velkostPolicka + "\n");
		}
		zapisovac.flush();
		zapisovac.close();
	}
}