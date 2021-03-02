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

    Scanner scanner;
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
	    	riadok.split(",");			// rozdeli string do pola stringov podla ","
	    	int zaciatok1= riadok.indexOf(":")+1;
	    	int koniec1 = riadok.indexOf(" ");
	    	String NewOjektID = riadok.substring(zaciatok1, koniec1);
	    	riadok = riadok.substring(koniec1+1);
	    	
	    	int zaciatok2= riadok.indexOf(":")+1;
	    	int koniec2 = riadok.indexOf(" ");
	    	String NewOjektPoziciaX = riadok.substring(zaciatok2, koniec2);
	    	riadok = riadok.substring(koniec2+1);
	    	
	    	int zaciatok3= riadok.indexOf(":")+1;
	    	int koniec3 = riadok.indexOf(" ");
	    	String NewOjektPoziciaY = riadok.substring(zaciatok3, koniec3);
	    	riadok = riadok.substring(koniec3+1);
	    	
	    	int zaciatok4= riadok.indexOf(":")+1;
	    	int koniec4 = riadok.indexOf(" ");
	    	String NewOjektWidth = riadok.substring(zaciatok4, koniec4);
	    	riadok = riadok.substring(koniec4+1);
	    	
	    	int zaciatok5= riadok.indexOf(":")+1;
	    	String NewObjektHeight = riadok.substring(zaciatok5);
	    	
	    	//System.out.println(NewOjektID + " " + NewOjektPoziciaX + " " + NewOjektPoziciaY + " " + NewOjektWidth + " " + NewOjektWidth);
	    	
	    	double newObjectID = Double.parseDouble(NewOjektID);
	    	int newObjectPoziciaX = Integer.parseInt(NewOjektPoziciaX);
	    	int newObjectPoziciaY = Integer.parseInt(NewOjektPoziciaY);
	    	int newObjectWidth = Integer.parseInt(NewOjektWidth);
	    	int newObjectHeight = Integer.parseInt(NewObjektHeight);
	    	
	    	//System.out.println(newObjectID + " " + newObjectPoziciaX + " " + newObjectPoziciaY + " " + newObjectWidth + " " + newObjectHeight);
	    	
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
