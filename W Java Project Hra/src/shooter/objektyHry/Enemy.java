package shooter.objektyHry;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import shooter.Platno;
import shooter.Handler;

/**
 * Trieda vytvara objekty hry typu enemy  
 */
public class Enemy extends PohyblivyObjektHry {

	Random randomPohyb = new Random();
			
			int pohyb=0;
			
	public Enemy(int poziciaX, int poziciaY, Handler handler) {
		super(poziciaX, poziciaY, handler);
		width = 35;
		height = 43;
		zivot=100;
		
		try {
			image = ImageIO.read(new File("obr/zoimbie1_hold.png"));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void aktualizujObjektHry() {
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
	
	@Override
	public void pohni() {
		poziciaX += vecX;
        poziciaY += vecY;
        pohyb = randomPohyb.nextInt(10);
        rectangle.setBounds(poziciaX, poziciaY, width, height);
	}

}
