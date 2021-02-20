package shooter;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.imageio.ImageIO;

/**
 * Trieda vytvara hraca
 */
public class Player extends Sprite{
	//premenne ktore budu mat vsetky objekty hry - steny, mapa, zbrane, nepriatelia, vsetko...
	
		// premenne pre poziciu objektu
		private int poziciaX;
		private int poziciaY;
		private int centerX;
		private int centerY;
		private int test;
		
		// premenne potrebne na vykreslovanie objektu
		private Platno platno;
		private Image image;
		private int height;
		private int width;
		private Rectangle rectangle = new Rectangle();
		
	// premenne ktore maju vsetky pohyblive objekty - strely, hrac, enemy 
		private double uholX;
		private double uholY;
		private double rotacia;
		private double vecX;
		private double vecY;

	/**
	 * Vytvori hraca
	 * @param poziciaX pozicia hraca, X suradnica laveho horneho rohu
	 * @param poziciaY pozicia hraca, Y suradnica laveho horneho rohu
	 * @param platno JPanel na ktory sa hrac vykresli
	 */
	public Player(int poziciaX, int poziciaY, Platno platno) {
		this.poziciaX = poziciaX;
		this.poziciaY = poziciaY;
		this.platno = platno;
		
		// nacitaj obrazok a ziskaj z obrazku parametre pre hraca
		// 			sirka, vyska, rect (pre kolizie)
		try {
			image = ImageIO.read(new File("obr/player_gun.png"));
			this.height = image.getHeight(platno);
			this.width = image.getWidth(platno)*2/3;
			this.rectangle.setBounds(poziciaX, poziciaY, width, height);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Vykresli hraca
	 */
	public void draw(Graphics graf) {
		// aktualizuje parametre hraca
		refresh();

		Graphics2D g = (Graphics2D) graf.create();
		// otoci pod uhlom (v radianoch), okolo stredu
		g.rotate(rotacia, centerX, centerY);
		// vykresli stvorec okolo hraca
		g.draw(rectangle);
		// vykresli obrazok hraca
		g.drawImage(image, poziciaX, poziciaY, null);
		
		g.dispose();
	}

	/**
	 * Aktualizuje hraca
	 */
	private void refresh() {
		aktualizujRotaciu();
		//aktualizujVektoryPohybu();
		pohni();
		rectangle.setBounds(poziciaX, poziciaY, width, height);
	}

	/**
	 * Pohne hracom
	 */
	private void pohni() {

		if (vecX!=0) {
			poziciaX += vecX*Settings.PLAYER_SPEED;	
		}
		if (vecY!=0) {
			poziciaY += vecY*Settings.PLAYER_SPEED;	
		}
		
		this.centerX = poziciaX + (width/2);
		this.centerY = poziciaY + (height/2);
	}

	public void setVecX(double vecX) {
		this.vecX = vecX;
	}

	public void setVecY(double vecY) {
		this.vecY = vecY;
	}

	
	/**
	 * Aktualizuje uhol rotacie hraca
	 */
	private void aktualizujRotaciu() {
		uholX = PohybMysou.getMouseX() - centerX;
		uholY = PohybMysou.getMouseY() - centerY;
		rotacia=Math.atan2(uholY, uholX);
	}

	/**
	 * Vytvori objekt triedy Strela na hracovej pozicii
	 */
	public void shoot() {
		
		for (int i = 0; i < 1; i++) {
			double[] x = {centerX+5*i, centerY+5*i, uholX, uholY};
			platno.spawn(new Strela(x, platno));
		}
	}
}
