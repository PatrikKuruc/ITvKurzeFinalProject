package shooter.objektyHry;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import shooter.Handler;

/**
 * Trieda vytvara nepohyblivy objekt typu trava
 */
public class Trava extends ObjektHry{

	/**
	 * Vytvori objekt typu Trava (prechodny, nepouzitelny, bez pohybu)
	 * P: zem, pozadie, treba lepsi - genericky nazov pre objekty po ktorych sa da chodit a nebudu sa nijak pouzivat..
	 * P: casom to moze byt aj napr. voda, ktora bude mat nejaku animaciu a tak.. 
	 * @param poziciaX pozicia objektu, X suradnica laveho horneho rohu
	 * @param poziciaY pozicia objektu, Y suradnica laveho horneho rohu
	 * @param handler handler
	 */
    public Trava(int poziciaX, int poziciaY, Handler handler) {
        super(poziciaX, poziciaY, handler);
        this.width = 32;
        this.height = 32;
        
        try {
			image = ImageIO.read(new File("obr/trava.png"));
			image = image.getScaledInstance(32, 32, Image.SCALE_FAST);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @Override
    public void vykresli(Graphics g2) {
    	Graphics2D g = (Graphics2D) g2.create();
		g.drawImage(image, poziciaX, poziciaY, null);
		g.dispose();
    }
}
