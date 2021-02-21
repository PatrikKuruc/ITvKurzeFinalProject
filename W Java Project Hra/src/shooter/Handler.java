package shooter;

import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Random;

/**
 * Trieda Handler sluzi na spracovanie roznych objekov hry.
 * - pridávanie objektov
 * - mazanie objektov
 * - vykreslovanie a pohyb objektov
 */
public class Handler {

    LinkedList<ObjektHry> objekty = new LinkedList<>();

    private boolean up = false, down = false, right = false, left = false;
    private int mouseX, mouseY;

	private Platno platno;

    public Handler(Platno platno) {
		this.platno = platno;
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
	
	public void playerShoot() {
		for (int i = 0; i < objekty.size(); i++) {
			ObjektHry tempObject = objekty.get(i);
			if (tempObject instanceof Player) {
				int x = tempObject.getCenterX();
				int y = tempObject.getCenterY();
				addObject(new Strela(x, y, platno, this));
				break;
			}
		}
	}

	public void nahrajPozadie() {
		for (int i = 0; i < 1000/32+1; i++) {
			addObject(new Stena(i*32,0,platno,this));
			addObject(new Stena(i*32,800-32,platno,this));
		}
		for (int i = 0; i < 800/32+1; i++) {
			addObject(new Stena(0,i*32,platno,this));
			addObject(new Stena(1000-32,i*32,platno,this));
		}
	}
	

	public void nahrajObjekty() {
		for (int i = 0; i < 10; i++) {
			int x = new Random().nextInt(900)+50;
			int y = new Random().nextInt(700)+50;
			addObject(new Enemy(x,y,platno,this));
		}
	}
	
	/**
     * Akutalizuje okno hry s objektami 60 krat za sekundu.
     */
    public void aktualizujObjektyHry(){
        for(int i = 0; i < objekty.size(); i++){
            ObjektHry novyObjekt = objekty.get(i);
            novyObjekt.aktualizujObjektHry();
        }
    }

    /**
     * Vykresluje vsetky objekty zo zoznamu objekty.
     * @param g graficke platno
     */
    public void vykresli(Graphics g){
        for(int i = 0; i < objekty.size(); i++){
            ObjektHry novyObjekt = objekty.get(i);
            novyObjekt.vykresli(g);
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
     * @param objektHry objekt hry.
     */
    public void removeObject(ObjektHry objektHry){
        objekty.remove(objektHry);
    }

    // getre a settre, ktore vracaju true / false, urcene pre pohyb WASD hlavnej postavy

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

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }
}
