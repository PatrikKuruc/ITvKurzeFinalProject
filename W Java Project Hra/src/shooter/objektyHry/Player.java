package shooter.objektyHry;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import shooter.Handler;
import shooter.Settings;

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
		this.velX=Settings.playerSpeed;
		this.velY=Settings.playerSpeed;
		
		try {
			image = ImageIO.read(new File("obr/hrac/modry/3.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void aktualizujObjektHry() {
		destinationX = handler.getMouseX();
		destinationY = handler.getMouseY();
		
		poziciaHlavneX = centerX;
		poziciaHlavneY = centerY;
		
		handler.setPoziciaHracaX(centerX);
		handler.setPoziciaHracaY(centerY);
		super.aktualizujObjektHry();
	}
	
	public void zistiSmer() {
        if(handler.isUp()) vecY = -1;
        else if(!handler.isDown()) vecY = 0;

        if(handler.isDown()) vecY = 1;
        else if(!handler.isUp()) vecY = 0;

        if(handler.isRight()) vecX = 1;
        else if(!handler.isLeft()) vecX = 0;

        if(handler.isLeft()) vecX = -1;
        else if(!handler.isRight()) vecX = 0;	
	}

	public int getPoziciaHlavneX() {
		return poziciaHlavneX;
	}

	public int getPoziciaHlavneY() {
		return poziciaHlavneY;
	}

	public void zistiKoliziu() {
		 for(int i = 0; i < handler.objekty.size(); i++){
	            ObjektHry objektHry = handler.objekty.get(i);
	                if(objektHry instanceof Stena){
	                    if(getBounds().intersects(objektHry.getBounds())){
	                    	vecX=vecX*-1;
	                    	vecY=vecY*-1;
	                }
	            }
	        }
	}
}
