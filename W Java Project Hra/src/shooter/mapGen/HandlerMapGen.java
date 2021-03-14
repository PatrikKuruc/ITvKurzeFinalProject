package shooter.mapGen;

import java.awt.Graphics2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class HandlerMapGen {
	
	private ArrayList<ObjektJComp> defaultMap;
	private ArrayList<ObjektJComp> currentMap;
	private ArrayList<ObjektJComp> objektyNaZmazanie;
	protected int velkostPolicka = 32;
	private double SelectedItemID=1.1D;
	private PrintWriter zapisovac;
	
	public HandlerMapGen() {
		defaultMap = new ArrayList<>();
		currentMap = new ArrayList<>();
		objektyNaZmazanie = new ArrayList<>();
		loadDefaultMap();
	}

	private void loadDefaultMap() {
		File suborMapy = new File("mapy/defaultMap.txt");
		try {
			Scanner scanner = new Scanner(suborMapy);
		
        // adds game objects to the object list according to the map location
        while (scanner.hasNextLine()) {
            String riadok = scanner.nextLine();

            String[] riadokPole = riadok.split(",");            // splits the string into the array of strings ","

            double newObjectID = Double.parseDouble(riadokPole[0]);
            int newObjectPoziciaX = Integer.parseInt(riadokPole[1]);
            int newObjectPoziciaY = Integer.parseInt(riadokPole[2]);

            defaultMap.add(new ObjektJComp(newObjectID, newObjectPoziciaX, newObjectPoziciaY, this));
        }
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
	public void addObjetOnCanvas(double ID, int x, int y) {
		SelectedItemID = ID;
		int pozX = x/velkostPolicka;
		int pozY = y/velkostPolicka;
		currentMap.add(new ObjektJComp(SelectedItemID, pozX*velkostPolicka, pozY*velkostPolicka, this));
	}
	
	public void deleteObjectsAt(int x, int y) {
		int pozX = x/velkostPolicka;
		int pozY = y/velkostPolicka;
		for (ObjektJComp objektJComp : currentMap) {
			ObjektJComp obj = objektJComp;
			if (obj.getPoziciaX()/velkostPolicka == pozX && obj.getPoziciaY()/velkostPolicka==pozY) {
				objektyNaZmazanie.add(objektJComp);
			}
		}
		currentMap.removeAll(objektyNaZmazanie);
	}
	
	public void setDefaultMap() {
		deleteCurrentMap();
		currentMap.addAll(defaultMap);
	}

	public void deleteCurrentMap() {
		currentMap.removeAll(currentMap);
	}
	
	public void drawObjects(Graphics2D g2) {
		for (ObjektJComp objektJComp : currentMap) {
			objektJComp.vykresli(g2);
		}
	}
	
	public double getSelectedItemID() {
		return SelectedItemID;
	}

	public void setSelectedItemID(double SelectedItemID) {
		this.SelectedItemID = SelectedItemID;
	}

	public void saveMap() {
		String newMapName = JOptionPane.showInputDialog("zadaj nazov mapy");
		File newMap = new File("mapy/"+newMapName+".txt");
		
		try {
			newMap.createNewFile();
			zapisovac = new PrintWriter(newMap);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for (ObjektJComp objektMapy : currentMap) {
			zapisovac.write(objektMapy.getID() + "," + objektMapy.getPoziciaX() + "," + objektMapy.getPoziciaY() + "," + velkostPolicka + "," + velkostPolicka + "\n");
		}
		zapisovac.flush();
		zapisovac.close();
	}
}