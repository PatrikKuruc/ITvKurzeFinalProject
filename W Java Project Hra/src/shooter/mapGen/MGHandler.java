package shooter.mapGen;

import java.awt.Graphics2D;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class MGHandler {
	
	private ArrayList<ObjektJComp> defaultMap;
	private ArrayList<ObjektJComp> newMap;
	private ArrayList<ObjektJComp> objektyNaZmazanie;
	private double typObjektu = 1.0;
	protected int velkostPolicka = 32;
	
	private double kreslisObjektID;
	private PrintWriter zapisovac;
	
	public MGHandler() {
		defaultMap = new ArrayList<>();
		newMap = new ArrayList<>();
		objektyNaZmazanie = new ArrayList<>();
		vytvorDefaultnuMapu();
	}

	private void vytvorDefaultnuMapu() {
		int vRiadku = 1024/velkostPolicka;
		int vStlpci = (800)/velkostPolicka;
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
				objektyNaZmazanie.add(objektJComp);
			}
		}
		newMap.removeAll(objektyNaZmazanie);
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
		
		for (ObjektJComp objektMapy : newMap) {
			zapisovac.write(objektMapy.getID() + "," + objektMapy.getPoziciaX() + "," + objektMapy.getPoziciaY() + "," + velkostPolicka + "," + velkostPolicka + "\n");
		}
		zapisovac.flush();
		zapisovac.close();
	}
}