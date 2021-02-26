package shooter.mapGen;

import java.util.ArrayList;

public class HandlerMapGen {
	
	private ArrayList<ObjektMapGen> defaultMap;
	private ArrayList<ObjektMapGen> zoznamObjektov;
	private ArrayList<ObjektMapGen> newMap;
	private double typObjektu = 1.0;
	
	public HandlerMapGen() {
		defaultMap = new ArrayList<>();
		vytvorDefaultneObjektyMapy();
		
		zoznamObjektov = new ArrayList<>();
		vytvorZoznamObjektov();
		
		newMap = new ArrayList<>();
	}

	private void vytvorDefaultneObjektyMapy() {
		int velkostPolicka = 16;
		int vRiadku = 800/velkostPolicka;
		int vStlpci = 592/velkostPolicka;
		for (int i = 0; i < vRiadku; i++) {
			for (int j = 0; j < vStlpci; j++) {
				if (i == 0 || j == 0 || i == vRiadku-1 || j == vStlpci-1) {
					defaultMap.add(new ObjektMapGen(1.0, i*16, j*16));
				}
				else {
					defaultMap.add(new ObjektMapGen(0.0, i*16, j*16));
				}
			}
		}
	}

	private void vytvorZoznamObjektov() {
		zoznamObjektov.add(new ObjektMapGen(0.0, 10, 10));
		zoznamObjektov.add(new ObjektMapGen(1.0, 60, 10));
		zoznamObjektov.add(new ObjektMapGen(2.0, 110, 10));
		zoznamObjektov.add(new ObjektMapGen(3.0, 160, 10));
		zoznamObjektov.add(new ObjektMapGen(4.0, 210, 10));
	}

	public void pridajObjekt(int x, int y) {
		int pozX = x/16;
		int pozY = y/16;
		newMap.add(new ObjektMapGen(typObjektu, pozX*16, pozY*16));
	}
	
	public ArrayList<ObjektMapGen> getNewMap() {
		return newMap;
	}
	
	public ArrayList<ObjektMapGen> getZoznamObjektov() {
		return zoznamObjektov;
	}

	public ArrayList<ObjektMapGen> getDefaultMap() {
		return defaultMap;
	}
}
