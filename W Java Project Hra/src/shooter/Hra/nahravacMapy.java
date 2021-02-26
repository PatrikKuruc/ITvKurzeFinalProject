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
		this.suborMapy = new File("mapy/mapa.txt");
		this.scanner = new Scanner(suborMapy);
	}

	public void nahrajMapu() {
		/**
	     * Prida objekty hry do listu objektov podla ich lokacie v mape
	     */
		int x = 0;
	    int y = 0;
	    while (scanner.hasNextLine()){
	    	double IDfile = scanner.nextDouble();
	    	int ID = (int) IDfile;
	    	
	        if(ID == 1){
	            handler.addObject(new Stena(ID,x,y, handler));
	            x += 32;
	        }
	        else if (ID != 1){
        		handler.addObject(new Trava(0,x,y, handler));
        	    if (ID == 2) {
	            	handler.addObject(new Player(ID,x,y, handler));
	            }
	            if (ID == 3) {
	            	handler.addObject(new Enemy(ID,x,y, handler));
	            }
	            if (ID == 4) {
	            	handler.addObject(new Item(ID,x,y, handler));
	            }
	            x += 32;
	        }
	        else {
	        	x += 32;
	        }
	                
	        if (x > 1000){
	            y += 32;
	            x = 0;
	        }
	    }
	}
}
