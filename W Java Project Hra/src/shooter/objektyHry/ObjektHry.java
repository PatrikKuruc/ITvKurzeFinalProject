package shooter.objektyHry;

import java.awt.*;

import javax.swing.JComponent;

import shooter.Platno;
import shooter.Handler;


/**
 * Trieda ObjektHry reprezentuje zaklad pre vsetky objekty hry - steny, hrac, strela, atd. 
 */
public abstract class ObjektHry extends JComponent {
	
	// x, y suradnice vykreslenia objektu
    protected int poziciaX,poziciaY;
    protected int centerX,centerY;

 // premenne potrebne na vykreslovanie objektu
 		protected Handler handler;
 		protected Image image;
 		protected int height;
 		protected int width;
 		protected Rectangle rectangle = new Rectangle();
   
 		int zivot;

    public int getCenterX() {
			return centerX;
		}


		public int getCenterY() {
			return centerY;
		}


	/**
     * Zostroji sa objekt hry
     * @param platno2 
     * @param x suradnica x, na ktorej sa ma objekt hry vytvorit
     * @param y suradnica y, na ktorej sa ma objekt hry vytvorit
     * @param id rozpoznovacie id, aby sa vedelo, o aky typ objektu sa jedna
     */
    public ObjektHry(int poziciaX, int poziciaY, Handler handler){
        this.poziciaX = poziciaX;
        this.poziciaY = poziciaY;
        this.handler = handler;
    }

 // abstraktne metody, ktore zdedia vsetky objekty hry, ktore dedia triedu ObjektHry
    public abstract void aktualizujObjektHry();
    
    public void vykresli(Graphics gr) {
    	Graphics2D g = (Graphics2D) gr.create();
		
		// vykresli stvorec okolo hraca
		g.draw(this.rectangle);
		// vykresli obrazok hraca
		g.drawImage(image, poziciaX, poziciaY, null);
		
		g.dispose();	
    };


	public void takeDamage(int dmg) {
		zivot -= dmg;
		
	}
    
    public Rectangle getBounds() {
        return new Rectangle(poziciaX-5,poziciaY-5,width+5,height+5);
    }
    
	public void setPoziciaX(int poziciaX) {
		this.poziciaX = poziciaX;
	}


	public void setPoziciaY(int poziciaY) {
		this.poziciaY = poziciaY;
	}


	public void setCenterX(int centerX) {
		this.centerX = centerX;
	}


	public void setCenterY(int centerY) {
		this.centerY = centerY;
	}


	public void setImage(Image image) {
		this.image = image;
	}


	public void setHeight(int height) {
		this.height = height;
	}


	public void setWidth(int width) {
		this.width = width;
	}


	public void setRectangle(Rectangle rectangle) {
		this.rectangle = rectangle;
	}
    
}
