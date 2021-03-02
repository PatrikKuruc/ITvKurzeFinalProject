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

public class ObjektJComp extends JComponent{
	
	private MGHandler handler;
	private int poziciaX;
	private int poziciaY;
	private Image image;
	private double ID;
	
	public ObjektJComp(Double ID, int poziciaX, int poziciaY, MGHandler handlerMapGen) {
		this.ID = ID;
		this.poziciaX = poziciaX;
		this.poziciaY = poziciaY;
		this.handler = handlerMapGen;
		nacitajObrazok();
	}
	
	public void nacitajObrazok() {
    	double IDint = ID;
    	if (IDint==0) {
    		try {
    			image = ImageIO.read(new File("obr/trava/1.png"));
    			image = image.getScaledInstance(handler.velkostPolicka, handler.velkostPolicka, Image.SCALE_FAST);
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    	}
    	else if (IDint ==1) {
    		try {
    			image = ImageIO.read(new File("obr/stena/drevo/11.png"));
    			image = image.getScaledInstance(handler.velkostPolicka, handler.velkostPolicka, Image.SCALE_FAST);
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
		}
    	else if (IDint==2) {
    		try {
    		image = ImageIO.read(new File("obr/hrac/modry/3.png"));
    		image = image.getScaledInstance(handler.velkostPolicka, handler.velkostPolicka, Image.SCALE_FAST);
    		}
    			catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    	}
    	else if (IDint==3) {
    		try {
    			image = ImageIO.read(new File("obr/enemy/1.png"));
    			image = image.getScaledInstance(handler.velkostPolicka, handler.velkostPolicka, Image.SCALE_FAST);
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    	}
    	else if (IDint==4.1) {
    		try {
    			image = ImageIO.read(new File("obr/item/1.png"));
    			image = image.getScaledInstance(handler.velkostPolicka/2, handler.velkostPolicka/2, Image.SCALE_FAST);
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    	}
    	else if (IDint==4.2) {
    		try {
    			image = ImageIO.read(new File("obr/item/2.png"));
    			image = image.getScaledInstance(handler.velkostPolicka/2, handler.velkostPolicka/2, Image.SCALE_FAST);
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

	public int getPoziciaX() {
		return poziciaX;
	}

	public int getPoziciaY() {
		return poziciaY;
	}
    
	public void setX(int x) {
		poziciaX = x;
	}
	public void setY(int y) {
		poziciaY = y;
	}

	public Double getID() {
		return ID;
	}
}