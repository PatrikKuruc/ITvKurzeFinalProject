package shooter.objektyHry;

import java.awt.*;

import javax.swing.JComponent;

import shooter.Handler;

/**
 * Trieda ObjektHry reprezentuje zaklad pre vsetky objekty hry - steny, hrac, strela, atd. 
 */
public abstract class ObjektHry extends JComponent {
	// x, y suradnice objektu
    protected int poziciaX,poziciaY;
    protected int centerX,centerY;

    // premenne potrebne na vykreslovanie objektu   *P: momentalne nacitanie obr prebieha v kazdom objekte zvlast.. tiez to moze prist sem
 	protected Handler handler;
 	protected Image image;
 	protected int height;
 	protected int width;
 	protected Rectangle rectangle = new Rectangle();
   
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
        this.handler = handler;
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
        return new Rectangle(poziciaX-1,poziciaY-1,width+2,height+2);
    }
    
}
