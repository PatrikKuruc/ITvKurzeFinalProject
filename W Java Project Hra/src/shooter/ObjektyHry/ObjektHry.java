package shooter.ObjektyHry;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
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
 	protected int height=32;
 	protected int width=32;
 	protected Rectangle rectangle;;
 	
 	protected double ID;
   
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
    
    public void nacitajObrazok() {
    	if (ID==0) {
    		try {
    			image = ImageIO.read(new File("obr/trava/1.png"));
    			image = image.getScaledInstance(32, 32, Image.SCALE_FAST);
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    	}
    	else if (ID==1) {
    		try {
    			image = ImageIO.read(new File("obr/stena/drevo/11.png"));
    			image = image.getScaledInstance(32, 32, Image.SCALE_FAST);
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
		}
    	else if (ID==2) {
    		try {
    		image = ImageIO.read(new File("obr/hrac/modry/3.png"));
    		
    		}
    			catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    	}
    	else if (ID==3) {
    		try {
    			image = ImageIO.read(new File("obr/enemy/1.png"));
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    	}
    	else if (ID==4) {
    		try {
    			image = ImageIO.read(new File("obr/item/1.png"));
    			image = image.getScaledInstance(32/2, 32/2, Image.SCALE_FAST);
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    	}
    	else if (ID==5) {
    		try {
    			this.image = ImageIO.read(new File("obr/strela/1.png"));
    			image = image.getScaledInstance(513/30, 173/30, Image.SCALE_FAST);
    			
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    	}
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