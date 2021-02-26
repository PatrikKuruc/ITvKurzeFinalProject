package shooter.mapGen;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.EventListener;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComponent;

import shooter.Hra.nahravacMapy;
import shooter.ObjektyHry.ObjektHry;

public class ObjektMapGen {
	
	private int poziciaX;
	private int poziciaY;
	private Image image;
	private double ID;
	
	public ObjektMapGen(Double ID, int poziciaX, int poziciaY) {
		this.ID = ID;
		this.poziciaX = poziciaX;
		this.poziciaY = poziciaY;
		nacitajObrazok();
	}
	
	
	public void nacitajObrazok() {
    	int IDint = (int)ID;
    	if (IDint==0) {
    		try {
    			image = ImageIO.read(new File("obr/trava/1.png"));
    			image = image.getScaledInstance(16, 16, Image.SCALE_FAST);
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    	}
    	else if (IDint ==1) {
    		try {
    			image = ImageIO.read(new File("obr/stena/drevo/11.png"));
    			image = image.getScaledInstance(16, 16, Image.SCALE_FAST);
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
		}
    	else if (IDint==2) {
    		try {
    		image = ImageIO.read(new File("obr/hrac/modry/3.png"));
    		
    		}
    			catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    	}
    	else if (IDint==3) {
    		try {
    			image = ImageIO.read(new File("obr/enemy/1.png"));
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    	}
    	else if (IDint==4) {
    		try {
    			image = ImageIO.read(new File("obr/item/1.png"));
    			image = image.getScaledInstance(16, 16, Image.SCALE_FAST);
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    	}
    	else if (IDint==5) {
    		try {
    			this.image = ImageIO.read(new File("obr/strela/1.png"));
    			image = image.getScaledInstance(513/30, 173/30, Image.SCALE_FAST);
    			
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    	}
	}


	/**
     * Vykresli objekt
     * @param gr graficky kontext
     */
    public void vykresli(Graphics gr) {
    	Graphics2D g = (Graphics2D) gr.create();
		g.drawImage(image, poziciaX, poziciaY, null);
		g.dispose();	
    }
}
