package shooter.ObjektyHry;

import shooter.Game.Handler;
import shooter.Game.SoundEffect;

/**
 * Trieda vytvara nepohyblivy objekt typu trava
 */
public class Item extends PohyblivyObjektHry{

	public static int pocetLekarniciek, pocetZasobnikov = 0;
	private SoundEffect soundEffect;

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
				handler.ammo += 5;
				handler.removeObject(this);
				pocetZasobnikov--;

				soundEffect = new SoundEffect();
				soundEffect.setFileItemPickUp();
				soundEffect.play();
			}

			// ak je zivot rovny/mensi 50, prida 50 zivota, inak doplni len zvysok do maximalnej hodnoty 100
			if(ID == 4.2){
				if(handler.healthPlayer <= 50){
					handler.healthPlayer += 50;
				} else if (handler.healthPlayer < 100){
					int zivot2 = 100 - handler.healthPlayer;
					handler.healthPlayer += zivot2;
				}
				handler.removeObject(this);
				pocetLekarniciek--;

				soundEffect = new SoundEffect();
				soundEffect.setFileItemPickUp();
				soundEffect.play();
			}
		}
	}

	@Override
	public void zistiSmer() {
		destinationX=centerX;
		destinationY=centerY;
	}
}
