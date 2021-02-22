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
	protected double uholX;
	protected double uholY;
	protected double rotacia;
	protected double vecX;
	protected double vecY;
	
	// abstraktne metody, ktore zdedia vsetky pohyblive objekty hry
    // metody, ktore sa pre rozne typy objektov lisia
	public abstract void zistiKoliziu();
	public abstract void aktualizujObjektHry();
	
	/**
	 * Vytvara pohyblivy objekt hry
	 * @param poziciaX pozicia objektu, X suradnica laveho horneho rohu
	 * @param poziciaY pozicia objektu, Y suradnica laveho horneho rohu
	 * @param handler handler
	 */
	public PohyblivyObjektHry(int poziciaX, int poziciaY, Handler handler) {
		super(poziciaX, poziciaY, handler);
	}

	/**
	 * Aktualizuje poziciu objektu
	 */
	public void pohni() {
		poziciaX += vecX;
        poziciaY += vecY;
		
		this.centerX = poziciaX + (width/2);
		this.centerY = poziciaY + (height/2);
		rectangle.setBounds(poziciaX, poziciaY, width, height);
	}
	
	@Override
	public Rectangle getBounds() {
        return new Rectangle(poziciaX-5,poziciaY-5,width+10,height+10);
    }

	@Override
	public void vykresli(Graphics gr) {
		Graphics2D g = (Graphics2D) gr.create();
		g.rotate(rotacia, centerX, centerY);
		super.vykresli(g);
		g.dispose();
	}

}
