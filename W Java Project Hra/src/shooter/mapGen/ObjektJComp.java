package shooter.mapGen;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

public class ObjektJComp extends JComponent{
	
	private MGHandler handler;
	private int poziciaX;
	private int poziciaY;
	private Image image;
	private double ID;
	private String imagePath;
	private String IDstring;
	
	public ObjektJComp(Double ID, int poziciaX, int poziciaY, MGHandler handlerMapGen) {
		this.ID = ID;
		this.IDstring = Double.toString(this.ID);
		this.poziciaX = poziciaX;
		this.poziciaY = poziciaY;
		this.handler = handlerMapGen;
		nacitajObrazok();
	}
	
	public void nacitajObrazok() {
		try (InputStream input = new FileInputStream("src/imageID.properties")) {

            Properties prop = new Properties();
            prop.load(input);
            
            this.imagePath = prop.getProperty(IDstring);
            //System.out.println("Vytvoreny objekt s ID: " + IDstring + " a imagePath: " + imagePath);
            image = ImageIO.read(new File(imagePath));
			image = image.getScaledInstance(handler.velkostPolicka, handler.velkostPolicka, Image.SCALE_FAST);
            
		} catch (IOException ex) {
			 ex.printStackTrace();
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