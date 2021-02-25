package shooter.ObjektyHry;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import shooter.Hra.Handler;
import shooter.Hra.Settings;

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
	private int poziciaHlavneX;
	private int poziciaHlavneY;
	
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
	public void vykonajKoliznyEvent() {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void vykresli(Graphics gr) {
		super.vykresli(gr);
		vykresliHealthbar(gr);
	}
	
	private void vykresliHealthbar(Graphics gr) {
		// cele vykreslovanie score sem

				gr.setColor(Color.lightGray);
				gr.fillRect(0,5,200,22);
				gr.setColor(Color.GREEN);
				gr.fillRect(0,5, handler.zivot*2, 22);
				gr.setColor(Color.BLACK);
				gr.drawRect(0,5,200,22);

				gr.setColor(Color.white);
				gr.drawString("Naboje: " + handler.zasobnik , 220, 22);
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
}
