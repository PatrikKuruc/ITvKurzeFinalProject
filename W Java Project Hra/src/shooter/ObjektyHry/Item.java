package shooter.ObjektyHry;

import shooter.Hra.Handler;
/**
 * Trieda vytvara nepohyblivy objekt typu trava
 */
public class Item extends PohyblivyObjektHry{

	/**
	 * Vytvori objekt typu Item (prechodny, pouzitelny, bez pohybu)
	 * @param poziciaX pozicia objektu, X suradnica laveho horneho rohu
	 * @param poziciaY pozicia objektu, Y suradnica laveho horneho rohu
	 * @param handler handler
	 */
    public Item(double ID, int poziciaX, int poziciaY, Handler handler) {
        super(poziciaX, poziciaY, handler);
        this.ID=ID;
        super.nacitajObrazok();
        this.width = 15;
        this.height = 15;
        System.out.println(ID);
    }

	@Override
	public void vykonajKoliznyEvent(ObjektHry objekt) {
		
	}

	@Override
	public void zistiSmer() {
		destinationX=centerX;
        destinationY=centerY;
	}
}
