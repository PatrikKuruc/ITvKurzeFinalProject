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
		width = 50;
		height = 50;
		zivot=100;
		
		try {
			image = ImageIO.read(new File("obr/zoimbie1_hold.png"));
			//this.height = image.getHeight(platno);
			//this.width = image.getWidth(platno)*2/3;
			this.rectangle.setBounds(poziciaX, poziciaY, width, height);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Rectangle getBoundsBig() {
        return new Rectangle(poziciaX -16 , poziciaY - 16,64,64);
    }
	
	@Override
	public void aktualizujObjektHry() {
		
        zistiKoliziu();
        
        pohni();
		
		rectangle.setBounds(poziciaX, poziciaY, width, height);
	}

	
	@Override
	public void vykresli(Graphics gr) {
		// vykresli obrazok o par pixelov mensi ako rectangle na kolizie
		
		Graphics2D g = (Graphics2D) gr.create();
		// vykresli stvorec okolo hraca
		g.draw(this.rectangle);
		// vykresli obrazok 
		g.drawImage(image, poziciaX, poziciaY, null);
		
		g.dispose();
	}
	@Override
	public void pohni() {
		poziciaX += vecX;
        poziciaY += vecY;
        pohyb = randomPohyb.nextInt(10);
		
	}
	@Override
	public void zistiKoliziu() {
		for(int i = 0; i < handler.objekty.size(); i++){
            ObjektHry objektHry = handler.objekty.get(i);
            if(objektHry instanceof Stena){
                if(getBoundsBig().intersects(objektHry.getBounds())){
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
