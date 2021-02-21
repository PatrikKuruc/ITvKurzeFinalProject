package shooter;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;
/**
 * Trieda vytvara objekty hry typu enemy  
 */
public class Enemy extends ObjektHry {

	public Enemy(int poziciaX, int poziciaY, Platno platno, Handler handler) {
		super(poziciaX, poziciaY, platno, handler);
		width = 50;
		height = 50;
	}
	
	@Override
	public void aktualizujObjektHry() {
		rectangle.setBounds(poziciaX, poziciaY, width, height);
		poziciaX+= new Random().nextInt(3- -3)+-2.5;
		poziciaY+= new Random().nextInt(3- -3)+-2.5;
	}

	@Override
	public void vykresli(Graphics gr) {
		Graphics2D g = (Graphics2D) gr.create();
		// vykresli stvorec okolo hraca
		g.draw(this.rectangle);
		// vykresli obrazok 
		//g.drawImage(image, poziciaX, poziciaY, null);
		
		g.dispose();
	}
}
