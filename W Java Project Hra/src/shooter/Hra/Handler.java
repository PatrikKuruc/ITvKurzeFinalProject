package shooter.Hra;

import java.awt.Graphics;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

import shooter.ObjektyHry.Enemy;
import shooter.ObjektyHry.Item;
import shooter.ObjektyHry.ObjektHry;
import shooter.ObjektyHry.Player;
import shooter.ObjektyHry.PohyblivyObjektHry;
import shooter.ObjektyHry.Strela;


/**
 * Trieda Handler sluzi na nacitanie a spracovanie objektov hry.
 * - pridavanie objektov, mazanie objektov
 * - vykreslovanie a aktualizacia objektov
 * - ukladanie userInputs
 */
public class Handler {
	
	private boolean up = false, down = false, left = false, right = false;
	private int mouseX;
	private int mouseY;
	
	private int poziciaHracaX, poziciaHracaY;
	
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

	public UserInput UserInput;

	public LinkedList<ObjektHry> objekty = new LinkedList<>();
	public int zivot=100;
	public int zasobnik=100;


	/**
	 * Vytvori handler
	 */
    public Handler(){
    	
	}

	/**
	 * Hrac vystreli strelu
	 */
	public void playerShoot() {
		for (int i = 0; i < objekty.size(); i++) {
			ObjektHry tempObject = objekty.get(i);
			if (tempObject instanceof Player) {
				int x = ((Player)tempObject).getPoziciaHlavneX();
				int y = ((Player)tempObject).getPoziciaHlavneY();
				addObject(new Strela(x, y, this));
				zasobnik--;
				break;
			}
		}
	}

	/**
     * Aktualizuje objekty hry.
     */
    public void aktualizujObjektyHry(){
        for(int i = 0; i < objekty.size(); i++){
            ObjektHry novyObjekt = objekty.get(i);
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
        for(int i = 0; i < objekty.size(); i++){
            ObjektHry novyObjekt = objekty.get(i);
            if (novyObjekt instanceof PohyblivyObjektHry || novyObjekt instanceof Item)
            {
            	continue;
            }
            else {
            	novyObjekt.vykresli(g);
			}
        }
        for(int i = 0; i < objekty.size(); i++){
        	ObjektHry novyObjekt = objekty.get(i);
     
        	 if (novyObjekt instanceof PohyblivyObjektHry || novyObjekt instanceof Item)
            {
        		novyObjekt.vykresli(g);
            }
        }
    }

    /**
     * Prida objekt do hry.
     * @param novyObjekt novy objekt
     */
    public void addObject(ObjektHry novyObjekt){
        objekty.add(novyObjekt);
    }

    /**
     * Vymaze objekt z hry.
     * @param objektHry objekt hry
     */
    public void removeObject(ObjektHry objektHry){
        objekty.remove(objektHry);
        
        int pocetzombies = 0;
        for (ObjektHry objektHryy : objekty) {
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
                	addObject(new Enemy(y, z, this));
                	j--;
				}
        	}
		}
    }
    
    // gettre a settre
    
    public boolean isUp() {
		return up;
	}

	public void setUp(boolean up) {
		this.up = up;
	}

	public boolean isDown() {
		return down;
	}

	public void setDown(boolean down) {
		this.down = down;
	}

	public boolean isLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}

	public boolean isRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

	public int getMouseX() {
		return mouseX;
	}

	public void setMouseX(int mouseX) {
		this.mouseX = mouseX;
	}

	public int getMouseY() {
		return mouseY;
	}

	public void setMouseY(int mouseY) {
		this.mouseY = mouseY;
	}

}
