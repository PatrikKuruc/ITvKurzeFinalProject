package shooter.ObjektyHry;

import java.awt.Graphics;
import java.awt.Graphics2D;

import shooter.Hra.Handler;
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

	
	// rozne typy objektov sa budu pri kolizii s nymi objektami (okrem stien) spravat inak
	public abstract void vykonajKoliznyEvent();
	// aktualizuje uhly v zavislosti od typu objektu
	public abstract void zistiSmer();
	
	/**
	 * Vytvara pohyblivy objekt hry
	 * @param poziciaX pozicia objektu, X suradnica laveho horneho rohu
	 * @param poziciaY pozicia objektu, Y suradnica laveho horneho rohu
	 * @param handler handler
	 */
	public PohyblivyObjektHry(int poziciaX, int poziciaY, Handler handler) {
		super(poziciaX, poziciaY, handler);
		
	}

	public void aktualizujObjektHry() {
		zistiSmer();
		aktualizujRotaciu();
		
		zistiKoliziuPohyblivychObjektov();
		pohni();
		zistiKoliziuSoStenami();
	}
	
	/**
	 * Kolizia objektu so stenou
	 */
	public void koliziaSoStenou() {
		poziciaX -= vecX*(velX+1);
        poziciaY -= vecY*(velY+1);
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
	
	/**
	 * Aktualizuje uhol rotacie objektu
	 */
	public void aktualizujRotaciu() {
		uholX = destinationX - centerX;
		uholY = destinationY - centerY;
		rotacia=Math.atan2(uholY, uholX);
	}
	
	/**
	 * Zisti koliziu medzi pohyblivymi objektmi
	 */
	private void zistiKoliziuPohyblivychObjektov() {
		for(int i = 0; i < handler.pohybliveObjekty.size(); i++) {
			ObjektHry objekt = handler.pohybliveObjekty.get(i);
			if (objekt!=this) {
				if(getBounds().intersects(objekt.getBounds())) {
					vykonajKoliznyEvent();
				}
			}
		}
	}
	
	/**
	 * Zisti koliziu pohyblivych objektov so statickymi
	 */
	public void zistiKoliziuSoStenami() {
		for(int i = 0; i < handler.statickeObjekty.size(); i++){
            ObjektHry objektHry = handler.statickeObjekty.get(i);
            if(objektHry instanceof Stena){
                if(getBounds().intersects(objektHry.getBounds())){
                    koliziaSoStenou();
                }
            }
        }
	}
	
	@Override
	public void vykresli(Graphics gr) {
		Graphics2D g = (Graphics2D) gr.create();
		//g.draw(getBounds());
		g.rotate(rotacia, centerX, centerY);
		super.vykresli(g);
		g.dispose();
	}
}
