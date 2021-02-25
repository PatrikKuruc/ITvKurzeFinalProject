package shooter.ObjektyHry;

import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

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
	 * @param handler handler
	 */
	public Enemy(int poziciaX, int poziciaY, Handler handler) {
		super(poziciaX, poziciaY, handler);
		width = 35;
		height = 43;
		zivot=100;
		velX = Settings.enemySpeed;
		velY = Settings.enemySpeed;
		
		try {
			image = ImageIO.read(new File("obr/enemy/1.png"));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void vykonajKoliznyEvent() {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void zistiSmer() {
		destinationX = handler.getPoziciaHracaX();
		destinationY = handler.getPoziciaHracaY();
		
		this.uholX = destinationX-poziciaX;
		this.uholY = destinationY-poziciaY;
		
		double vzdialenost = Math.sqrt(Math.pow(uholX, 2) + Math.pow(uholY, 2));
		if (Math.abs(uholX)>1) {
			vecX = (float) (uholX * 2 / vzdialenost);
		}
		if (Math.abs(uholY)>1) {
			 vecY = (float) (uholY * 2 / vzdialenost);
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
