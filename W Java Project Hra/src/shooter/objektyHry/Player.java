package shooter.objektyHry;

import java.awt.Color;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import shooter.Handler;

/**
 * Trieda vytvara hraca
 */
public class Player extends PohyblivyObjektHry{

	/**
	 * Vytvori hraca
	 * @param poziciaX pozicia hraca, X suradnica laveho horneho rohu
	 * @param poziciaY pozicia hraca, Y suradnica laveho horneho rohu
	 * @param handler handler
	 */
	int poziciaHlavneX;
	int poziciaHlavneY;
	
	public Player(int poziciaX, int poziciaY,  Handler handler) {
		super(poziciaX, poziciaY, handler);
		this.height = 43;
		this.width = 49;
		
		try {
			image = ImageIO.read(new File("obr/hrac/modry/3.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void vykresli(Graphics gr) {
		super.vykresli(gr);
		vykresliHealthbar(gr);
	}
	
	
	private void vykresliHealthbar(Graphics gr) {
		// cele vykreslovanie score sem
		gr.setColor(Color.white);
		gr.drawString("Naboje: ", 250, 250);
	}

	@Override
	public void aktualizujObjektHry() {
		zistiSmer();
		aktualizujRotaciu();
		zistiKoliziu();
		pohni();
		
		poziciaHlavneX = centerX;
		poziciaHlavneY = centerY;
	}
	
	public int getPoziciaHlavneX() {
		return poziciaHlavneX;
	}

	public int getPoziciaHlavneY() {
		return poziciaHlavneY;
	}
	
	/**
	 * Metoda zisti smer pohybu hraca podla vstupov od uzivatela
	 */
	public void zistiSmer() {
        if(handler.isUp()) vecY = -5;
        else if(!handler.isDown()) vecY = 0;

        if(handler.isDown()) vecY = 5;
        else if(!handler.isUp()) vecY = 0;

        if(handler.isRight()) vecX = 5;
        else if(!handler.isLeft()) vecX = 0;

        if(handler.isLeft()) vecX = -5;
        else if(!handler.isRight()) vecX = 0;	
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

	public void zistiKoliziu() {
		 for(int i = 0; i < handler.objekty.size(); i++){

	            ObjektHry objektHry = handler.objekty.get(i);

	                if(objektHry instanceof Stena){
	                    if(getBounds().intersects(objektHry.getBounds())){
	                    	poziciaX += vecX*-1.5;
	                        poziciaY += vecY*-1.5;
	                }
	            }
	        }
	}
}
