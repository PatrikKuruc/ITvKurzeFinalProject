package shooter;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Trieda vytvara strelu
 */
public class Strela extends Sprite {
	// premenne kazdeho objektu hry

	// premenne pre poziciu objektu
		private double poziciaX;
		private double poziciaY;
		private int centerX;
		private int centerY;
	
	// premenne potrebne na vykreslovanie objektu
		private Platno platno;
		private Image image;
		private int height;
		private int width;
		private Rectangle rectangle = new Rectangle();
	
	// premenne pohybliveho objektu
		private double uholX;
		private double uholY;
		private double rotacia;
		private double vecX;
		private double vecY;
	
		boolean zobrazit = false;
	
	private double speed=5;

	/**
	 * Vytvori strelu
	 * @param strelaParam parametre strely
	 * @param platno JPanel na ktorom sa strela bude vykreslovat
	 */
	public Strela(double[] strelaParam, Platno platno) {
		this.poziciaX = strelaParam[0];
		this.poziciaY = strelaParam[1];
		this.platno = platno;
		this.zobrazit = true;
		
		// nacitaj obrazok a ziskaj z obrazku parametre pre strelu
		//			 sirka, vyska, rect (pre kolizie)
		try {
			this.image = ImageIO.read(new File("obr/Bullet.png"));
			
			this.width = image.getWidth(platno);
			this.height = image.getHeight(platno);
			this.rectangle.setBounds((int)poziciaX, (int)poziciaY, width*1/40, height*1/40);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// zmensi obrazok
		this.width = (int)width*1/40;
		this.height = (int)height*1/40;
		image = image.getScaledInstance(width, height, Image.SCALE_FAST);
		
		// vypocitaj vektory uhla, pod ktorym bola strela vystrelena
		this.uholX = strelaParam[2];
		this.uholY = strelaParam[3];
		// zarotuj obrazok strely podla uhla (v radianoch)
		this.rotacia=Math.atan2(uholY, uholX);	
		
		// vypocet vektorov pohybu strely (pytagorova veta)
		double prep = Math.sqrt(Math.pow(uholX, 2)+Math.pow(uholY, 2));
		this.vecX = uholX / prep;
		this.vecY = uholY / prep;
	}

	/**
	 * Vykresli strelu
	 */
	public void draw(Graphics graf) {
		
		// aktualizuje parametre strely
		refresh();
		Graphics2D g = (Graphics2D) graf.create();	
		
		// otoci pod uhlom (v radianoch), okolo stredu
		g.rotate(rotacia, centerX, centerY);
		// vykresli stvorec okolo strely
		g.drawRect((int)poziciaX, (int)poziciaY, image.getWidth(platno), image.getHeight(platno));
		g.draw(rectangle);
		// vykresli obrazok strely
		g.drawImage(image, (int)poziciaX, (int)poziciaY, null);
		
		g.dispose();
	}

	/**
	 * Aktualizuje strelu - len pohyb
	 */
	private void refresh() {
		rectangle.setBounds((int)poziciaX, (int)poziciaY, image.getWidth(platno), image.getHeight(platno));
		pohni();
	}

	/**
	 * Pohne strelou
	 */
	private void pohni() {
		//pohyb
		poziciaX+=vecX*speed;
		poziciaY+=vecY*speed;
		// skontroluj ci neni mimo platna, ak ano zmaz strelu
		if (poziciaX>Settings.WINDOW_WIDTH || poziciaX<0 || poziciaY>Settings.WINDOW_HEIGHT || poziciaY<0) {
			platno.zmazObjekt(this);
		}
		this.centerX = (int)poziciaX + (width/2);
		this.centerY = (int)poziciaY + (height/2);
	}
}
