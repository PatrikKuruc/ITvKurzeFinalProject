package shooter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import shooter.objektyHry.Enemy;
import shooter.objektyHry.Item;
import shooter.objektyHry.Player;
import shooter.objektyHry.Stena;
import shooter.objektyHry.Trava;

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
	    	int ID = scanner.nextInt();
	        if(ID == 1){
	            handler.addObject(new Stena(x,y, handler));
	            x += 32;
	        }
	        else if (ID != 1) {
	        	handler.addObject(new Trava(x,y, handler));
	            x += 32;
	            if (ID == 4) {
	            	handler.addObject(new Enemy(x,y, handler));
	            }
	            if (ID == 2) {
	            	handler.addObject(new Player(x,y, handler));
	            }
	            if (ID == 5) {
	            	handler.addObject(new Item(x,y, handler));
	            }
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
