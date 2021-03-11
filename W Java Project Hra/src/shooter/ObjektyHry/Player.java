package shooter.ObjektyHry;

import java.awt.Color;
import java.awt.Graphics;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.imageio.ImageIO;

import shooter.Game.Handler;
import shooter.Game.UserInput;

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
	
	public Player(int poziciaX, int poziciaY, Handler handler) {
		super(poziciaX, poziciaY, handler);
		nacitajParametre();
	}
	
	
	private void nacitajParametre() {
		try (InputStream input = new FileInputStream("src/playerConfig.properties")) {
            Properties playerProp = new Properties();
            playerProp.load(input);
            
            System.out.println("Meno hraca: " + playerProp.getProperty("Name"));
            image = ImageIO.read(new File(playerProp.getProperty("ImagePath")));
            this.ID = Integer.parseInt(playerProp.getProperty("ID"));
            this.height = Integer.parseInt(playerProp.getProperty("Height"));
            this.width = Integer.parseInt(playerProp.getProperty("Width"));
            this.velX=Integer.parseInt(playerProp.getProperty("Speed"));
            this.velY=Integer.parseInt(playerProp.getProperty("Speed"));
            
		} catch (IOException ex) {
            ex.printStackTrace();
        }
		
	}

	@Override
	public void vykonajKoliznyEvent(ObjektHry objekt) {
		if(objekt instanceof Enemy) {
			handler.healthPlayer--;
			handler.damageTaken++;
			if(handler.healthPlayer <= 0){
				handler.healthPlayer = 0;
			}
		}

		if(objekt instanceof MamaZombie){
			handler.healthPlayer--;
			handler.damageTaken++;
			if(handler.healthPlayer <= 0){
				handler.healthPlayer = 0;
			}
		}

		if(objekt instanceof StrelaEnemy){
			handler.healthPlayer -= 20;
			handler.damageTaken += 20;
			if(handler.healthPlayer <= 0){
				handler.healthPlayer = 0;
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
		if(handler.healthPlayer >= 50) {
			gr.setColor(Color.lightGray);
			gr.fillRect(32, 5, 200, 22);
			gr.setColor(Color.GREEN);
			gr.fillRect(32, 5, (int) (handler.healthPlayer * 2), 22);
			gr.setColor(Color.BLACK);
			gr.drawRect(32, 5, 200, 22);
			gr.setColor(Color.black);
			gr.drawString((int) handler.healthPlayer + "/100", 110, 22);
		} else {
			gr.setColor(Color.lightGray);
			gr.fillRect(32, 5, 200, 22);
			gr.setColor(Color.red);
			gr.fillRect(32, 5, (int) (handler.healthPlayer * 2), 22);
			gr.setColor(Color.BLACK);
			gr.drawRect(32, 5, 200, 22);
			gr.setColor(Color.black);
			gr.drawString((int) handler.healthPlayer + "/100", 110, 22);
		}

		// vykreslenie nabojov
		gr.setColor(Color.white);
		gr.drawString("Naboje: " + handler.ammo, 250, 22);

		// vykreslenie score
		gr.setColor(Color.white);
		gr.drawString("Score: " + handler.score, 350, 22);

		// vykreslenie zraneni
		gr.setColor(Color.white);
		gr.drawString("Zranenia: " + handler.damageTaken, 800,22);
	}
	

	@Override
	public void aktualizujObjektHry() {
		destinationX = UserInput.getMouseX();
		destinationY = UserInput.getMouseY();
		poziciaHlavneX = centerX;
		poziciaHlavneY = centerY;
		
		handler.setPositionPlayerX(centerX);
		handler.setPositionPlayerY(centerY);
		
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
