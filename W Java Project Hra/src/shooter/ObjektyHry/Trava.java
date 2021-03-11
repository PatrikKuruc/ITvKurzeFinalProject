package shooter.ObjektyHry;

import shooter.Game.Handler;

/**
 * Trieda vytvara nepohyblivy objekt typu trava
 */
public class Trava extends ObjektHry{

	boolean isSpawnPoint = false;
	
	public boolean isSpawnPoint() {
		return isSpawnPoint;
	}

	/**
	 * Vytvori objekt typu Trava (prechodny, nepouzitelny, bez pohybu)
	 * P: zem, pozadie, treba lepsi - genericky nazov pre objekty po ktorych sa da chodit a nebudu sa nijak pouzivat..
	 * P: casom to moze byt aj napr. voda, ktora bude mat nejaku animaciu a tak.. 
	 * @param poziciaX pozicia objektu, X suradnica laveho horneho rohu
	 * @param poziciaY pozicia objektu, Y suradnica laveho horneho rohu
	 * @param newObjectHeight 
	 * @param newObjectWidth 
	 * @param handler handler
	 */
    public Trava(double ID, int poziciaX, int poziciaY, int newObjectWidth, int newObjectHeight, Handler handler) {
        super(poziciaX, poziciaY, handler);
        this.ID=ID;
        
        if (ID >0) {
        	isSpawnPoint = true;
		}
        	
        this.width = newObjectWidth;
        this.height = newObjectHeight;
        super.nacitajObrazok();
    }
    
    public int getX() {
    	return poziciaX;
    }
    
    public int getY() {
    	return poziciaX;
    }
    
}
