package shooter.objektyHry;

import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import shooter.Handler;

/**
 * Trieda vytvara pohyblivy objekt typu enemy  
 */
public class Enemy extends PohyblivyObjektHry {
	
	Random randomPohyb = new Random();
	int pohyb=0;
	
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
		
		try {
			image = ImageIO.read(new File("obr/enemy/1.png"));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void aktualizujObjektHry() {
		pohyb = randomPohyb.nextInt(10);
        zistiKoliziu();
        pohni();
	}
	
	@Override
	public void zistiKoliziu() {
		for(int i = 0; i < handler.objekty.size(); i++){
            ObjektHry objektHry = handler.objekty.get(i);
            if(objektHry instanceof Stena){
                if(getBounds().intersects(objektHry.getBounds())){
                    poziciaX += (vecX*5) * -1;
                    poziciaY += (vecY*5) * -1;
                    vecX *= -1;
                    vecY *= -1;
                }
            } else if(pohyb == 0){
            	vecX = (randomPohyb.nextInt(3- -3) + -3);
            	vecY = (randomPohyb.nextInt(3- -3) + -3);
            }
        }
        if(zivot <= 0){
            handler.removeObject(this);
        }
	}
}
