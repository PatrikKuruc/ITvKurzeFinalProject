package shooter.objektyHry;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import shooter.Platno;

/**
 * Trieda vytvara strelu
 */
public class Strela extends ObjektHry {
	
	// premenne pohybliveho objektu
		private double uholX;
		private double uholY;
		private double rotacia;
		private double vecX;
		private double vecY;
	
		boolean zobrazit = false;
	

	/**
	 * Vytvori strelu
	 * @param strelaParam parametre strely
	 * @param platno JPanel na ktorom sa strela bude vykreslovat
	 */
	public Strela(int poziciaX, int poziciaY, Platno platno, Handler handler) {
		super(poziciaX, poziciaY, platno, handler);
		int smerX = handler.getMouseX();
		int smerY = handler.getMouseY();
		this.zobrazit = true;
		
		// nacitaj obrazok a ziskaj z obrazku parametre pre strelu
		//			 sirka, vyska, rect (pre kolizie)
		try {
			this.image = ImageIO.read(new File("obr/Bullet.png"));
			
			this.width = image.getWidth(platno);
			this.height = image.getHeight(platno);
			this.rectangle.setBounds(poziciaX, poziciaY, width*1/40, height*1/40);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// zmensi obrazok
		this.width = (int)width*1/40;
		this.height = (int)height*1/40;
		image = image.getScaledInstance(width, height, Image.SCALE_FAST);
		
		// vypocitaj vektory uhla, pod ktorym bola strela vystrelena
		this.uholX = smerX-poziciaX;
		this.uholY = smerY-poziciaY;
		// zarotuj obrazok strely podla uhla (v radianoch)
		this.rotacia=Math.atan2(uholY, uholX);	
		
		// vypocet vektorov pohybu strely (pytagorova veta)
		double vzdialenost = Math.sqrt(Math.pow(uholX, 2) + Math.pow(uholY, 2));
        double rychlost = 15;
        vecX = (float) (uholX * rychlost / vzdialenost);
        vecY = (float) (uholY * rychlost / vzdialenost);
	}

	/**
	 * Pohne strelou
	 */
	private void pohni() {
		poziciaX += vecX;
        poziciaY += vecY;
		
		this.centerX = poziciaX + (width/2);
		this.centerY = poziciaY + (height/2);
	}

	@Override
	public void aktualizujObjektHry() {
		pohni();
		rectangle.setBounds(poziciaX, poziciaY, image.getWidth(platno), image.getHeight(platno));
		for(int i = 0; i < handler.objekty.size(); i++){
            ObjektHry objektHry = handler.objekty.get(i);
		        if(getBounds().intersects(objektHry.getBounds())) {
		        	if(objektHry instanceof Enemy){
			        	objektHry.takeDamage(50);
			            handler.removeObject(this);
			        }
		        	if (objektHry instanceof Stena) {
						handler.removeObject(this);
					}
			    }
				
				
		}
	}

	@Override
	public void vykresli(Graphics g2) {
		// aktualizuje parametre strely
		Graphics2D g = (Graphics2D) g2.create();	
		
		// otoci pod uhlom (v radianoch), okolo stredu
		g.rotate(rotacia, centerX, centerY);
		// vykresli stvorec okolo strely
		g.drawRect(poziciaX, poziciaY, image.getWidth(platno), image.getHeight(platno));
		g.draw(rectangle);
		// vykresli obrazok strely
		g.drawImage(image, poziciaX, poziciaY, null);
		
		g.dispose();
		
	}
}
