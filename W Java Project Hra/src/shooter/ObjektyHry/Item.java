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
	}

	@Override
	public void vykonajKoliznyEvent(ObjektHry objekt) {
		if(objekt instanceof Player){

			// prida 10 nabojov do zasobnika
			if(ID == 4.1){
				handler.zasobnik += 10;
				handler.removeObject(this);
			}

			// ak je zivot rovny/mensi 50, prida 50 zivota, inak doplni len zvysok do maximalnej hodnoty 100
			if(ID == 4.2){
				if(handler.zivot <= 50){
					handler.zivot += 50;
				} else if (handler.zivot < 100){
					int zivot2 = 100 - handler.zivot;
					handler.zivot += zivot2;
				}
				handler.removeObject(this);
			}
		}
	}

	@Override
	public void zistiSmer() {
		destinationX=centerX;
		destinationY=centerY;
	}
}
