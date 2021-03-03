package shooter.ObjektyHry;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.Timer;

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
	
	public Player(double ID, int poziciaX, int poziciaY,  int newObjectWidth, int newObjectHeight, Handler handler) {
		super(poziciaX, poziciaY, handler);
		this.ID=ID;
		this.height = newObjectHeight;
		this.width = newObjectWidth;
		super.nacitajObrazok();
		this.velX=Settings.playerSpeed;
		this.velY=Settings.playerSpeed;
	}
	
	@Override
	public void vykonajKoliznyEvent(ObjektHry objekt) {
		if(objekt instanceof Enemy) {
			handler.zivot--;
			handler.zranenia++;
			if(handler.zivot <= 0){
				handler.zivot = 0;
			}
		}

		if(objekt instanceof MamaZombie){
			handler.zivot--;
			handler.zranenia++;
			if(handler.zivot <= 0){
				handler.zivot = 0;
			}
		}

		if(objekt instanceof StrelaEnemy){
			handler.zivot -= 20;
			handler.zranenia += 20;
			if(handler.zivot <= 0){
				handler.zivot = 0;
			}
		}
		
	}
	@Override
	public void vykresli(Graphics gr) {
		super.vykresli(gr);
		vykresliUdajeHraca(gr);
	}
	
	private void vykresliUdajeHraca(Graphics gr) {
		// vykreslenie health-baru
		if(handler.zivot >= 50) {
			gr.setColor(Color.lightGray);
			gr.fillRect(32, 5, 200, 22);
			gr.setColor(Color.GREEN);
			gr.fillRect(32, 5, (int) (handler.zivot * 2), 22);
			gr.setColor(Color.BLACK);
			gr.drawRect(32, 5, 200, 22);
			gr.setColor(Color.black);
			gr.drawString((int) handler.zivot + "/100", 110, 22);
		} else {
			gr.setColor(Color.lightGray);
			gr.fillRect(32, 5, 200, 22);
			gr.setColor(Color.red);
			gr.fillRect(32, 5, (int) (handler.zivot * 2), 22);
			gr.setColor(Color.BLACK);
			gr.drawRect(32, 5, 200, 22);
			gr.setColor(Color.black);
			gr.drawString((int) handler.zivot + "/100", 110, 22);
		}

		// vykreslenie nabojov
		gr.setColor(Color.white);
		gr.drawString("Naboje: " + handler.zasobnik , 250, 22);

		// vykreslenie score
		gr.setColor(Color.white);
		gr.drawString("Score: " + handler.score, 350, 22);

		// vykreslenie zraneni
		gr.setColor(Color.white);
		gr.drawString("Zranenia: " + handler.zranenia, 800,22);
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
