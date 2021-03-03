package shooter.Hra;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

import shooter.ObjektyHry.Enemy;
import shooter.ObjektyHry.ObjektHry;
import shooter.ObjektyHry.Player;
import shooter.ObjektyHry.PohyblivyObjektHry;
import shooter.ObjektyHry.Strela;

/**
 * Trieda Handler sluzi na nacitanie a spracovanie objektov hry.
 * - pridavanie objektov, mazanie objektov
 * - vykreslovanie a aktualizacia objektov
 */
public class Handler {
	
	private int poziciaHracaX, poziciaHracaY;

	public ArrayList<ObjektHry> statickeObjekty = new ArrayList<>();
	public ArrayList<ObjektHry> pohybliveObjekty = new ArrayList<>();
	public int zivot=100;
	public int zasobnik=100;
	public int score;
	public int velkostPolicka = 32;

	/**
	 * Vytvori handler
	 */
    public Handler(){
    	
	}

	public Handler(Object object) {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Hrac vystreli strelu
	 */
	public void playerShoot() {
		if (zasobnik>0) {
			addObject(new Strela(5, poziciaHracaX, poziciaHracaY, this));
			zasobnik--;
		}
	}

	/**
     * Aktualizuje objekty hry.
     */
    public void aktualizujObjektyHry(){
        for(int i = 0; i < pohybliveObjekty.size(); i++){
            ObjektHry novyObjekt = pohybliveObjekty.get(i);
            if (novyObjekt instanceof PohyblivyObjektHry) {
            	((PohyblivyObjektHry)novyObjekt).aktualizujObjektHry();
			}
            else {
				continue;
			}
        }
        
        
    }

    /**
     * Vykresluje vsetky objekty zo zoznamu objekty. Najprv staticke potom pohyblive.
     * @param g graficke platno
     */
    public void vykresliObjektyHry(Graphics g){
    	 for(int i = 0; i < statickeObjekty.size(); i++){
             ObjektHry novyObjekt = statickeObjekty.get(i);
             novyObjekt.vykresli(g);
 			}

        for(int i = 0; i < pohybliveObjekty.size(); i++){
            ObjektHry novyObjekt = pohybliveObjekty.get(i);
            if (novyObjekt instanceof Player) {
				
			}
            else {
            	novyObjekt.vykresli(g);
			}
			}
    }

	public void vykresliHraca(Graphics g) {
		for(int i = 0; i < pohybliveObjekty.size(); i++){
            ObjektHry novyObjekt = pohybliveObjekty.get(i);
            if (novyObjekt instanceof Player) {
            	novyObjekt.vykresli(g);
			}
            else {
            	
			}
			}
	}
    
    /**
     * Prida objekt do hry.
     * @param novyObjekt novy objekt
     */
    public void addObject(ObjektHry novyObjekt){   	
        ObjektHry objektNaPridanie = novyObjekt;
        if (objektNaPridanie instanceof PohyblivyObjektHry) {
			pohybliveObjekty.add(objektNaPridanie);
		}
        else {
        	statickeObjekty.add(objektNaPridanie);
        }
    }

    /**
     * Vymaze objekt z hry.
     * @param objektHry objekt hry
     */
    public void removeObject(ObjektHry objektHry){
    	ObjektHry objektNaPridanie = objektHry;
        if (objektNaPridanie instanceof PohyblivyObjektHry) {
			pohybliveObjekty.remove(objektNaPridanie);
		}
        else {
        	statickeObjekty.remove(objektNaPridanie);
        }
        
        
        int pocetzombies = 0;
        for (ObjektHry objektHryy : pohybliveObjekty) {
        	if (objektHryy instanceof Enemy) {
        		pocetzombies++;
        	}
		}
        if (objektHry instanceof Enemy && pocetzombies <=10) {
        	Random rnd = new Random();
        	int j = (int) (rnd.nextInt(3)+1);
        	while (j >= 0) {
        		if (pocetzombies<=10) {
        			int y = rnd.nextInt(1024);
                	int z = rnd.nextInt(800);
                	addObject(new Enemy(3, y, z, velkostPolicka, velkostPolicka, this));
                	j--;
				}
        	}
		}
    }
    
    // gettre a settre
    
    public int getPoziciaHracaY() {
		return poziciaHracaY;
	}

	public void setPoziciaHracaY(int poziciaHracaY) {
		this.poziciaHracaY = poziciaHracaY;
	}

	public int getPoziciaHracaX() {
		return poziciaHracaX;
	}

	public void setPoziciaHracaX(int poziciaHracaX) {
		this.poziciaHracaX = poziciaHracaX;
	}
}
