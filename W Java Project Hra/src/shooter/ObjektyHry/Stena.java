package shooter.ObjektyHry;

import java.awt.Graphics;
import java.awt.Graphics2D;

import shooter.Hra.Handler;
/**
 * Trieda vytvara nepohyblivy objekt typu Stena
 */
public class Stena extends ObjektHry{
	
	/**
	 * Vytvori objekt typu Stena (neprechodny, nepouzitelny, bez pohybu)
	 * @param ID 
	 * @param poziciaX pozicia objektu, X suradnica laveho horneho rohu
	 * @param poziciaY pozicia objektu, Y suradnica laveho horneho rohu
	 * @param newObjectHeight 
	 * @param newObjectWidth 
	 * @param handler handler
	 */
    public Stena(double ID, int poziciaX, int poziciaY,  int newObjectWidth, int newObjectHeight, Handler handler) {
        super(poziciaX, poziciaY, handler);
        this.ID=ID;
        this.width = newObjectWidth;
        this.height = newObjectHeight;
        super.nacitajObrazok();
        
    }

    //prepisana metoda vykresli, len kvoli tomu aby pri tychto objektoch nekreslilo stvorec okolo - kedze aj tak kolizie neriesime
    @Override
    public void vykresli(Graphics g2) {
    	Graphics2D g = (Graphics2D) g2.create();
		g.drawImage(image, poziciaX, poziciaY, null);
		g.dispose();
    }
}
