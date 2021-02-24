package shooter.objektyHry;

import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import shooter.Handler;
import shooter.Settings;

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
	public void zistiSmer() {
		destinationX = handler.getPoziciaHracaX();
		destinationY = handler.getPoziciaHracaY();
		
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

		// ak by sme chceli pohyb enemy v uhloch (momentalne sa hybu ako hrac - v 8 smeroch)
		/*
		this.uholX = destinationX-poziciaX;
		this.uholY = destinationY-poziciaY;
		double vzdialenost = Math.sqrt(Math.pow(uholX, 2) + Math.pow(uholY, 2));
        vecX = (float) (uholX * 2 / vzdialenost);
        vecY = (float) (uholY * 2 / vzdialenost);
        */
		
	}
	
	@Override
	public void zistiKoliziu() {
		for(int i = 0; i < handler.objekty.size(); i++){
            ObjektHry objektHry = handler.objekty.get(i);
            if(objektHry instanceof Stena){
                if(getBounds().intersects(objektHry.getBounds())){
                    vecX *= -1;
                    vecY *= -1;
                }
            }
        }
        if(zivot <= 0){
            handler.removeObject(this);
        }
	}
}
