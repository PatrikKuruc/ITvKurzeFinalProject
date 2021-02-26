package shooter.ObjektyHry;

import shooter.Hra.Handler;

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
	 * @param handler handler
	 */
    public Trava(int ID, int poziciaX, int poziciaY, Handler handler) {
        super(poziciaX, poziciaY, handler);
        this.ID=ID;
        super.nacitajObrazok();
    }
    
    public int getX() {
    	return poziciaX;
    }
    
    public int getY() {
    	return poziciaX;
    }
    
}
