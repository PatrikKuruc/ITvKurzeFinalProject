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
	private ArrayList<ObjektJComp> objectToRemove;
	protected int tileSize = 32;
	private double SelectedItemID = 1.1;
	private PrintWriter writer;
	
	public HandlerMapGen() {
		defaultMap = new ArrayList<>();
		currentMap = new ArrayList<>();
		objectToRemove = new ArrayList<>();
		loadDefaultMap();
	}

	/**
	 * Loads items for default map.
	 */
	private void loadDefaultMap() {
		File suborMapy = new File("mapy/defaultMapGenMap.txt");
		try {
			Scanner scanner = new Scanner(suborMapy);
		
        // adds game objects to the object list according to the map location
        while (scanner.hasNextLine()) {
            String[] splitLine = scanner.nextLine().split(",");            // splits the string into the array of strings ","

            double newObjectID = Double.parseDouble(splitLine[0]);
            int newObjectPozX = Integer.parseInt(splitLine[1]);
            int newObjectPozY = Integer.parseInt(splitLine[2]);

            defaultMap.add(new ObjektJComp(newObjectID, newObjectPozX, newObjectPozY, this));
        }
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
	public void addObjetOnCanvas(double ID, int x, int y) {
		SelectedItemID = ID;
		int pozX = x/tileSize;
		int pozY = y/tileSize;
		currentMap.add(new ObjektJComp(SelectedItemID, pozX*tileSize, pozY*tileSize, this));
	}
	
	public void deleteObjectsAt(int x, int y) {
		int pozX = x/tileSize;
		int pozY = y/tileSize;
		for (ObjektJComp object : currentMap) {
			ObjektJComp obj = object;
			if (obj.getPoziciaX()/tileSize == pozX && obj.getPoziciaY()/tileSize==pozY) {
				objectToRemove.add(object);
			}
		}
		currentMap.removeAll(objectToRemove);
	}
	
	public void setDefaultMap() {
		deleteCurrentMap();
		currentMap.addAll(defaultMap);
	}

	public void deleteCurrentMap() {
		currentMap.removeAll(currentMap);
	}
	
	public void drawObjects(Graphics2D g2) {
		for (ObjektJComp object : currentMap) {
			object.vykresli(g2);
		}
	}
	
	public double getSelectedItemID() {
		return SelectedItemID;
	}

	public void setSelectedItemID(double SelectedItemID) {
		this.SelectedItemID = SelectedItemID;
	}

	public void saveMap() {
		String newMapName = JOptionPane.showInputDialog("Choose map name:");
		File newMap = new File("mapy/custom/"+newMapName+".txt");
		
		try {
			newMap.createNewFile();
			writer = new PrintWriter(newMap);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		for (ObjektJComp object : currentMap) {
			writer.write(object.getID() + "," + object.getPoziciaX() + "," + object.getPoziciaY() + "," + tileSize + "," + tileSize + "\n");
		}
		writer.flush();
		writer.close();
	}
}