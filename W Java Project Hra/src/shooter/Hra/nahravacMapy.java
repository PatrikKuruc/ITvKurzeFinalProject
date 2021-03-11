package shooter.Hra;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Properties;
import java.util.Scanner;

import shooter.ObjektyHry.*;

public class nahravacMapy {

	private Handler handler;
    private Scanner scanner;
	private File suborMapy;

	// HashMapy na ukladanie suradnic x,y pre spawnovanie objektov
	public static HashMap<Integer, Integer> spawnPointEnemy = new HashMap<>();
	public static HashMap<Integer, Integer> spawnPointMama = new HashMap<>();
	public static HashMap<Integer, Integer> spawnPointLekarnika = new HashMap<>();
	public static HashMap<Integer, Integer> spawnPointZasobnik = new HashMap<>();
	
	public nahravacMapy(Handler handler) throws FileNotFoundException {
		this.handler = handler;
		this.suborMapy = new File("mapy/mapa.txt");
		this.scanner = new Scanner(suborMapy);
	}

	public void nahrajMapu() {
		/**
	     * Prida objekty hry do listu objektov podla ich lokacie v mape
	     */
	    while (scanner.hasNextLine()) {
	    	String riadok = scanner.nextLine();
	    	
	    	String[] riadokPole = riadok.split(",");			// rozdeli string do pola stringov podla ","

	    	double newObjectID = Double.parseDouble(riadokPole[0]);
	    	int newObjectPoziciaX = Integer.parseInt(riadokPole[1]);
	    	int newObjectPoziciaY = Integer.parseInt(riadokPole[2]);
	    	int newObjectWidth = Integer.parseInt(riadokPole[3]);
	    	int newObjectHeight = Integer.parseInt(riadokPole[4]);
	    	
	    	int ID = (int) newObjectID;
	    	
	    	if(newObjectID == 1){
	            handler.addObject(new Stena(newObjectID,newObjectPoziciaX,newObjectPoziciaY, newObjectWidth, newObjectHeight, handler));
	        }
	        else if (ID != 1){
        		handler.addObject(new Trava(0,newObjectPoziciaX,newObjectPoziciaY, newObjectWidth, newObjectHeight, handler));
        	    if (ID == 2) {
	            	handler.addObject(new Player(newObjectPoziciaX,newObjectPoziciaY, handler));
	            }
	            if (ID == 3) {
	            	handler.addObject(new Enemy(newObjectID,newObjectPoziciaX,newObjectPoziciaY, newObjectWidth, newObjectHeight, handler));
					//new Enemy(newObjectID,newObjectPoziciaX,newObjectPoziciaY, newObjectWidth, newObjectHeight, handler);
	            	spawnPointEnemy.put(newObjectPoziciaX, newObjectPoziciaY);
	            }
	            if (newObjectID == 4.1) {
					new Item(newObjectID,newObjectPoziciaX,newObjectPoziciaY, newObjectWidth, newObjectHeight, handler);
					spawnPointZasobnik.put(newObjectPoziciaX, newObjectPoziciaY);
	            	//handler.addObject(new Item(newObjectID,newObjectPoziciaX,newObjectPoziciaY, newObjectWidth, newObjectHeight, handler));
	            }
				if (newObjectID == 4.2) {
					new Item(newObjectID,newObjectPoziciaX,newObjectPoziciaY, newObjectWidth, newObjectHeight, handler);
					spawnPointLekarnika.put(newObjectPoziciaX, newObjectPoziciaY);
				}
				if (newObjectID == 6.0) {
					new MamaZombie(newObjectID,newObjectPoziciaX,newObjectPoziciaY, newObjectWidth, newObjectHeight, handler);
					spawnPointMama.put(newObjectPoziciaX, newObjectPoziciaY);
				}
	        }
		}
	}
}
