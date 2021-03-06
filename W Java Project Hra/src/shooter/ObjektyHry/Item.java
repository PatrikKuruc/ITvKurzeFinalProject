package shooter.ObjektyHry;

import shooter.Hra.Handler;
/**
 * Trieda vytvara nepohyblivy objekt typu trava
 */
public class Item extends PohyblivyObjektHry{

	public static int pocetLekarniciek, pocetZasobnikov = 0;

	/**
	 * Vytvori objekt typu Item (prechodny, pouzitelny, bez pohybu)
	 * @param poziciaX pozicia objektu, X suradnica laveho horneho rohu
	 * @param poziciaY pozicia objektu, Y suradnica laveho horneho rohu
	 * @param newObjectHeight
	 * @param newObjectWidth
	 * @param handler handler
	 */
	public Item(double ID, int poziciaX, int poziciaY, int newObjectWidth, int newObjectHeight, Handler handler) {
		super(poziciaX, poziciaY, handler);
		this.ID=ID;
		this.width = newObjectWidth;
        this.height = newObjectHeight;
		super.nacitajObrazok();
	}

	@Override
	public void vykonajKoliznyEvent(ObjektHry objekt) {
		if(objekt instanceof Player){

			// prida 10 nabojov do zasobnika
			if(ID == 4.1){
				handler.zasobnik += 5;
				handler.removeObject(this);
				pocetZasobnikov--;
			}

			// ak je zivot rovny/mensi 50, prida 50 zivota, inak doplni len zvysok do maximalnej hodnoty 100
			if(ID == 4.2){
				if(handler.zivotPlayer <= 50){
					handler.zivotPlayer += 50;
				} else if (handler.zivotPlayer < 100){
					int zivot2 = 100 - handler.zivotPlayer;
					handler.zivotPlayer += zivot2;
				}
				handler.removeObject(this);
				pocetLekarniciek--;
			}
		}
	}

	@Override
	public void zistiSmer() {
		destinationX=centerX;
		destinationY=centerY;
	}
}
