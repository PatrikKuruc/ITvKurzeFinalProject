package shooter.ObjektyHry;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import shooter.Hra.Handler;
/**
 * Trieda vytvara nepohyblivy objekt typu Stena
 */
public class Stena extends ObjektHry{
	
	/**
	 * Vytvori objekt typu Stena (neprechodny, nepouzitelny, bez pohybu)
	 * @param poziciaX pozicia objektu, X suradnica laveho horneho rohu
	 * @param poziciaY pozicia objektu, Y suradnica laveho horneho rohu
	 * @param handler handler
	 */
    public Stena(int poziciaX, int poziciaY,  Handler handler) {
        super(poziciaX, poziciaY, handler);
        this.width = 32;
        this.height = 32;
        try {
			image = ImageIO.read(new File("obr/stena/drevo/11.png"));
			image = image.getScaledInstance(32, 32, Image.SCALE_FAST);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    //prepisana metoda vykresli, len kvoli tomu aby pri tychto objektoch nekreslilo stvorec okolo - kedze aj tak kolizie neriesime
    @Override
    public void vykresli(Graphics g2) {
    	Graphics2D g = (Graphics2D) g2.create();
		g.drawImage(image, poziciaX, poziciaY, null);
		g.dispose();
    }
}
