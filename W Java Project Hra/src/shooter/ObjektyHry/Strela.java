package shooter.ObjektyHry;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import shooter.Hra.Handler;


/**
 * Trieda vytvara pohyblivy objekt typu strela
 */
public class Strela extends PohyblivyObjektHry {
	
	/**
	 * Vytvori objekt typu strela
	 * @param poziciaX pozicia objektu, X suradnica laveho horneho rohu
	 * @param poziciaY pozicia objektu, Y suradnica laveho horneho rohu
	 * @param handler handler
	 */
	public Strela(int poziciaX, int poziciaY, Handler handler) {
		super(poziciaX, poziciaY, handler);
		this.destinationX = handler.getMouseX();
		this.destinationY = handler.getMouseY();
		this.width=513/30;
		this.height=173/30;
		this.velY = 1;
		this.velX = 1;
		
		try {
			this.image = ImageIO.read(new File("obr/strela/1.png"));
			image = image.getScaledInstance(width, height, Image.SCALE_FAST);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// vypocitaj vektory uhla, pod ktorym bola strela vystrelena
		this.uholX = destinationX-poziciaX;
		this.uholY = destinationY-poziciaY;
		
		// vypocet vektorov pohybu strely (pytagorova veta)
		double vzdialenost = Math.sqrt(Math.pow(uholX, 2) + Math.pow(uholY, 2));
        vecX = (float) (uholX * 20 / vzdialenost);
        vecY = (float) (uholY * 20 / vzdialenost);
	}

	@Override
	public void koliziaSoStenou() {
		handler.removeObject(this);
	}
	
	@Override
	public void zistiSmer() {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void aktualizujRotaciu() {
		rotacia=Math.atan2(uholY, uholX);
	}

	@Override
	public void vykonajKoliznyEvent() {
		// TODO Auto-generated method stub
	}
}
