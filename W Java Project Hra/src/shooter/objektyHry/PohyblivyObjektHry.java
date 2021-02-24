package shooter.objektyHry;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import shooter.Handler;

/**
 * Abstraktna trieda pre pohyblive objekty hry ktora rozsiruje triedu objekt hry.
 */
public abstract class PohyblivyObjektHry extends ObjektHry {
	// premenne ktore maju len pohyblive objekty - strely, hrac, enemy 
		// premenne potrebne na vypocet uhlu rotacie obrazku
		protected double uholX;
		protected double uholY;
		protected double rotacia;
		protected double destinationX;
		protected double destinationY;
		// premenne potrebne pre pohyb objektu
		protected double vecX;
		protected double vecY;
		protected double velX;
		protected double velY;

	
	// abstraktne metody, ktore zdedia vsetky pohyblive objekty hry
    // metody, ktore sa pre rozne typy objektov lisia
	public abstract void zistiKoliziu();
	// aktualizuje uholX a uholY
	public abstract void zistiSmer();
	
	/**
	 * Vytvara pohyblivy objekt hry
	 * @param poziciaX pozicia objektu, X suradnica laveho horneho rohu
	 * @param poziciaY pozicia objektu, Y suradnica laveho horneho rohu
	 * @param handler handler
	 */
	public PohyblivyObjektHry(int poziciaX, int poziciaY, Handler handler) {
		super(poziciaX, poziciaY, handler);
		this.centerX = poziciaX + (width/2);
		this.centerY = poziciaY + (height/2);
		
	}

	public void aktualizujObjektHry() {
		zistiSmer();
		aktualizujRotaciu();
		zistiKoliziu();
		pohni();
	}
	
	public void aktualizujRotaciu() {
		uholX = destinationX - centerX;
		uholY = destinationY - centerY;
		rotacia=Math.atan2(uholY, uholX);
	}
	
	/**
	 * Aktualizuje poziciu objektu
	 */
	public void pohni() {
		// pohyb
		poziciaX += vecX*velX;
        poziciaY += vecY*velY;

        // aktualizacia parametrov
        this.centerX = (int) rectangle.getCenterX();
		this.centerY = (int) rectangle.getCenterY();
		rectangle.setBounds(poziciaX, poziciaY, width, height);
	}

	@Override
	public void vykresli(Graphics gr) {
		Graphics2D g = (Graphics2D) gr.create();
		g.rotate(rotacia, centerX, centerY);
		g.draw(getBounds());
		super.vykresli(g);
		g.dispose();
	}

}
