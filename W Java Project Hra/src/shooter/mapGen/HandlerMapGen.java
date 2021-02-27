package shooter.mapGen;

import java.util.ArrayList;

public class HandlerMapGen {
	
	private ArrayList<ObjektMapGen> defaultMap;
	//private ArrayList<ObjektMapGen> zoznamObjektov;
	private ArrayList<ObjektMapGen> newMap;
	private double typObjektu = 1.0;
	protected int velkostPolicka = 32;
	private ObjektMapGen docasnyObjekt;
	
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
					defaultMap.add(new ObjektMapGen(1.0, i*velkostPolicka, j*velkostPolicka, this));
				}
				else {
					defaultMap.add(new ObjektMapGen(0.0, i*velkostPolicka, j*velkostPolicka, this));
				}
			}
		}
	}
/*
	private void vytvorZoznamObjektov() {
		zoznamObjektov.add(new ObjektMapGen(0.0, 10, 10, this));
		zoznamObjektov.add(new ObjektMapGen(1.0, 60, 10, this));
		zoznamObjektov.add(new ObjektMapGen(2.0, 110, 10, this));
		zoznamObjektov.add(new ObjektMapGen(3.0, 160, 10, this));
		zoznamObjektov.add(new ObjektMapGen(4.0, 210, 10, this));
	}
*/
	public void pridajObjekt(double ID, int x, int y) {
		typObjektu = ID;
		int pozX = x/velkostPolicka;
		int pozY = y/velkostPolicka;
		newMap.add(new ObjektMapGen(typObjektu, pozX*velkostPolicka, pozY*velkostPolicka, this));
	}
	
	public ArrayList<ObjektMapGen> getNewMap() {
		return newMap;
	}
/*	
	public ArrayList<ObjektMapGen> getZoznamObjektov() {
		return zoznamObjektov;
	}
*/
	public ArrayList<ObjektMapGen> getDefaultMap() {
		return defaultMap;
	}

	public void setDocasnyObjekt(ObjektMapGen objektMapGen) {
		docasnyObjekt = objektMapGen;
	}
}