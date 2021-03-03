package shooter.Hra;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import shooter.ObjektyHry.Enemy;
import shooter.ObjektyHry.Item;
import shooter.ObjektyHry.Player;
import shooter.ObjektyHry.Stena;
import shooter.ObjektyHry.Trava;

public class nahravacMapy {

	private Handler handler;
    private Scanner scanner;
	private File suborMapy;
	
	public nahravacMapy(Handler handler) throws FileNotFoundException {
		this.handler = handler;
		this.suborMapy = new File("mapy/newMap.txt");
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
	            	handler.addObject(new Player(newObjectID,newObjectPoziciaX,newObjectPoziciaY, newObjectWidth, newObjectHeight, handler));
	            }
	            if (ID == 3) {
	            	handler.addObject(new Enemy(newObjectID,newObjectPoziciaX,newObjectPoziciaY, newObjectWidth, newObjectHeight, handler));
	            }
	            if (ID == 4) {
	            	handler.addObject(new Item(newObjectID,newObjectPoziciaX,newObjectPoziciaY, newObjectWidth, newObjectHeight, handler));
	            }
	        }
		}
	}
}
