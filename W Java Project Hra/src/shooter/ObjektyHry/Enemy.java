package shooter.ObjektyHry;

import shooter.Hra.Handler;
import shooter.Hra.Settings;

/**
 * Trieda vytvara pohyblivy objekt typu enemy  
 */
public class Enemy extends PohyblivyObjektHry {
	
	/**
	 * Vytvori objek typu Enemy
	 * @param poziciaX pozicia objektu, X suradnica laveho horneho rohu
	 * @param poziciaY pozicia objektu, Y suradnica laveho horneho rohu
	 * @param newObjectHeight 
	 * @param newObjectWidth 
	 * @param handler handler
	 */
	public Enemy(double ID, int poziciaX, int poziciaY, int newObjectWidth, int newObjectHeight, Handler handler) {
		super(poziciaX, poziciaY, handler);
		this.ID=ID;
		this.width = newObjectWidth;
        this.height = newObjectHeight;
		super.nacitajObrazok();

		zivot=100;
		velX = Settings.enemySpeed;
		velY = Settings.enemySpeed;
	}
	
	@Override
	public void vykonajKoliznyEvent(ObjektHry objekt) {
		if (objekt instanceof Strela) {
			zivot-=50;
			if (zivot<=0) {
				handler.removeObject(this);
				handler.score++;
			}
			handler.removeObject(objekt);
		}
	}
	
	
	@Override
	public void zistiSmer() {
		destinationX = handler.getPoziciaHracaX();
		destinationY = handler.getPoziciaHracaY();
		
		this.uholX = destinationX-poziciaX;
		this.uholY = destinationY-poziciaY;
		
		double vzdialenost = Math.sqrt(Math.pow(uholX, 2) + Math.pow(uholY, 2));
		
		if (Math.abs(vzdialenost)<200) {
			if (Math.abs(uholX)>1) {
				vecX = (float) (uholX * 2 / vzdialenost);
			}
			if (Math.abs(uholY)>1) {
				vecY = (float) (uholY * 2 / vzdialenost);
			}			
		}
		
		if (Math.abs(vzdialenost)>200) {
			vecX=0;
			vecY=0;
		}
		
        
        /* ak by sme chceli pohyb ako u hraca (v 8 smeroch)
        if (uholX < 0) {
			vecX = -1;
		}
		else {
			vecX = 1;
		}
		if (uholY<0) {
			vecY=-1;
		}
		else {
			vecY=1;
		}
		*/
	}
	
	public void aktualizujObjekHry() {
		if(zivot <= 0){
	        handler.removeObject(this);
		super.aktualizujObjektHry();
		}
    }
}
