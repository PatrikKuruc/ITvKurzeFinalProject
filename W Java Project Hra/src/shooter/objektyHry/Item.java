package shooter.objektyHry;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import shooter.Handler;

/**
 * Trieda vytvara nepohyblivy objekt typu trava
 */
public class Item extends ObjektHry{

	/**
	 * Vytvori objekt typu Item (prechodny, pouzitelny, bez pohybu)
	 * @param poziciaX pozicia objektu, X suradnica laveho horneho rohu
	 * @param poziciaY pozicia objektu, Y suradnica laveho horneho rohu
	 * @param handler handler
	 */
    public Item(int poziciaX, int poziciaY, Handler handler) {
        super(poziciaX, poziciaY, handler);
        this.width = 32;
        this.height = 32;
        
        try {
			image = ImageIO.read(new File("obr/item.png"));
			image = image.getScaledInstance(32/2, 32/2, Image.SCALE_FAST);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
