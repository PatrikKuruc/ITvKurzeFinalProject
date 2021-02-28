package shooter.ObjektyHry;

import java.awt.Color;
import java.awt.Graphics;

import shooter.Hra.Handler;
import shooter.Hra.Settings;
import shooter.Hra.UserInput;

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
	
	public Player(double ID, int poziciaX, int poziciaY,  Handler handler) {
		super(poziciaX, poziciaY, handler);
		this.ID=ID;
		super.nacitajObrazok();
		this.velX=Settings.playerSpeed;
		this.velY=Settings.playerSpeed;
	}
	
	@Override
	public void vykonajKoliznyEvent(ObjektHry objekt) {
		if(objekt instanceof Enemy) {
			handler.zivot--;
			if(handler.zivot <= 0){
				handler.zivot = 0;
			}
		}
		
	}
	
	@Override
	public void vykresli(Graphics gr) {
		super.vykresli(gr);
		vykresliHealthbar(gr);
	}
	
	private void vykresliUdajeHraca(Graphics gr) {
		// vykreslenie health-baru
		gr.setColor(Color.lightGray);
		gr.fillRect(32,5,200,22);
		gr.setColor(Color.GREEN);
		gr.fillRect(32,5, handler.zivot*2, 22);
		gr.setColor(Color.BLACK);
		gr.drawRect(32,5,200,22);

		// vykreslenie nabojov
		gr.setColor(Color.white);
		gr.drawString("Naboje: " + handler.zasobnik , 250, 22);

		// vykreslenie score
		gr.setColor(Color.white);
		gr.drawString("Score: " + handler.score, 350, 22);
	}
	

	@Override
	public void aktualizujObjektHry() {
		destinationX = UserInput.getMouseX();
		destinationY = UserInput.getMouseY();
		poziciaHlavneX = centerX;
		poziciaHlavneY = centerY;
		
		handler.setPoziciaHracaX(centerX);
		handler.setPoziciaHracaY(centerY);
		
		super.aktualizujObjektHry();
	}
	
	public void zistiSmer() {
        if(UserInput.isUp()) vecY = -1;
        else if(!UserInput.isDown()) vecY = 0;

        if(UserInput.isDown()) vecY = 1;
        else if(!UserInput.isUp()) vecY = 0;

        if(UserInput.isRight()) vecX = 1;
        else if(!UserInput.isLeft()) vecX = 0;

        if(UserInput.isLeft()) vecX = -1;
        else if(!UserInput.isRight()) vecX = 0;	
	}

	public int getPoziciaHlavneX() {
		return poziciaHlavneX;
	}

	public int getPoziciaHlavneY() {
		return poziciaHlavneY;
	}
}
