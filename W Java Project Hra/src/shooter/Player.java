package shooter;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Trieda vytvara hraca
 */
public class Player extends ObjektHry{
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
	public Player(int poziciaX, int poziciaY, Platno platno, Handler handler) {
		super(poziciaX, poziciaY, platno, handler);
		
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
	 * Pohne hracom
	 */
	private void pohni() {
		poziciaX += vecX;
        poziciaY += vecY;
        
        // pohyb hraca
        if(handler.isUp()) vecY = -5;
        else if(!handler.isDown()) vecY = 0;

        if(handler.isDown()) vecY = 5;
        else if(!handler.isUp()) vecY = 0;

        if(handler.isRight()) vecX = 5;
        else if(!handler.isLeft()) vecX = 0;

        if(handler.isLeft()) vecX = -5;
        else if(!handler.isRight()) vecX = 0;	
		
		
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
		uholX = handler.getMouseX() - centerX;
		uholY = handler.getMouseY() - centerY;
		rotacia=Math.atan2(uholY, uholX);
	}

	@Override
	public void aktualizujObjektHry() {
		aktualizujRotaciu();
		//aktualizujVektoryPohybu();
		pohni();
		rectangle.setBounds(poziciaX, poziciaY, width, height);
	}

	@Override
	public void vykresli(Graphics gr) {
		// aktualizuje parametre hraca

		Graphics2D g = (Graphics2D) gr.create();
		// otoci pod uhlom (v radianoch), okolo stredu
		g.rotate(rotacia, centerX, centerY);
		// vykresli stvorec okolo hraca
		g.draw(this.rectangle);
		// vykresli obrazok hraca
		g.drawImage(image, poziciaX, poziciaY, null);
		
		g.dispose();
		
	}

}
