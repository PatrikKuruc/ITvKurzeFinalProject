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
	
	private HandlerMapGen handler;
	private int posX;
	private int posY;
	private Image image;
	private double ID;
	private String imagePath;
	private String IDstring;
	
	public ObjektJComp(Double ID, int posX, int posY, HandlerMapGen handlerMapGen) {
		this.ID = ID;
		this.IDstring = Double.toString(this.ID);
		this.posX = posX;
		this.posY = posY;
		this.handler = handlerMapGen;
		nacitajObrazok();
	}
	
	public void nacitajObrazok() {
		try (InputStream input = new FileInputStream("resources/imageID.properties")) {

            Properties prop = new Properties();
            prop.load(input);
            
            this.imagePath = prop.getProperty(IDstring);
            image = ImageIO.read(new File(imagePath));
			image = image.getScaledInstance(handler.tileSize, handler.tileSize, Image.SCALE_FAST);
            
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
		g.drawImage(image, posX, posY, null);
		g.dispose();	
    }

	public int getPoziciaX() {
		return posX;
	}

	public int getPoziciaY() {
		return posY;
	}
    
	public void setX(int x) {
		posX = x;
	}
	public void setY(int y) {
		posY = y;
	}

	public Double getID() {
		return ID;
	}
}