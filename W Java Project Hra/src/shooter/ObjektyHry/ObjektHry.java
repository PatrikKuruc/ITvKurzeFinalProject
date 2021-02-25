package shooter.ObjektyHry;

import java.awt.*;

import javax.swing.JComponent;

import shooter.Hra.Handler;
/**
 * Trieda ObjektHry reprezentuje zaklad pre vsetky objekty hry - steny, hrac, strela, atd. 
 */
public abstract class ObjektHry extends JComponent {
	// x, y suradnice objektu
    protected int poziciaX,poziciaY;
    protected int centerX,centerY;

    // premenne potrebne na vykreslovanie objektu
 	protected Handler handler;
 	protected Image image;
 	protected int height;
 	protected int width;
 	protected Rectangle rectangle;;
   
 	int zivot;

	/**
     * Zostroji objekt hry
     * @param poziciaX suradnica x, na ktorej sa ma objekt hry vytvorit
     * @param poziciaY suradnica y, na ktorej sa ma objekt hry vytvorit
     * @param handler handler
     */
    public ObjektHry(int poziciaX, int poziciaY, Handler handler){
        this.poziciaX = poziciaX;
        this.poziciaY = poziciaY;
        this.centerX = poziciaX + (width/2);
		this.centerY = poziciaY + (height/2);
        this.handler = handler;
        this.rectangle = new Rectangle(poziciaX,poziciaY,width,height);
    }
    
    /**
     * Vykresli objekt
     * @param gr graficky kontext
     */
    public void vykresli(Graphics gr) {
    	Graphics2D g = (Graphics2D) gr.create();
		g.drawImage(image, poziciaX, poziciaY, null);
		g.dispose();	
    }

    /**
     * Zmeni zivot objektu
     * @param dmg damage
     */
	public void takeDamage(int dmg) {
		zivot -= dmg;
	}
    
	/**
	 * Vrati rectangle objektu pouzivany pri koliziach
	 */
    public Rectangle getBounds() {
        return new Rectangle(poziciaX,poziciaY,width,height);
    }
    
}
