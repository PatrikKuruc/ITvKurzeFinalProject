package shooter.objektyHry;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import shooter.Platno;

/**
 * Trieda vytvara objekty hry typu enemy  
 */
public class Enemy extends ObjektHry {
	// premenne ktore maju vsetky pohyblive objekty - strely, hrac, enemy 
			private double uholX;
			private double uholY;
			private double rotacia;
			private double vecX;
			private double vecY;
			Random randomPohyb = new Random();
			
			int pohyb=0, zivot=100;
			
	public Enemy(int poziciaX, int poziciaY, Platno platno, Handler handler) {
		super(poziciaX, poziciaY, platno, handler);
		width = 50;
		height = 50;
		
		try {
			image = ImageIO.read(new File("obr/zoimbie1_hold.png"));
			this.height = image.getHeight(platno);
			this.width = image.getWidth(platno)*2/3;
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
		poziciaX += vecX;
        poziciaY += vecY;
        pohyb = randomPohyb.nextInt(10);
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
            if(objektHry instanceof Strela){

                if(getBounds().intersects(objektHry.getBounds())) {
                    zivot -= 50;
                    handler.removeObject(objektHry);
                }
            }
        }
        if(zivot <= 0){
            handler.removeObject(this);
        }
		
		rectangle.setBounds(poziciaX, poziciaY, width, height);
		poziciaX+= new Random().nextInt(3- -3)+-2.5;
		poziciaY+= new Random().nextInt(3- -3)+-2.5;
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
}