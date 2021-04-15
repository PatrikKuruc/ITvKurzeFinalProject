package shooter.GameObjects;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

import shooter.Game.Handler;

/**
 * Class GameObject represents the base of all game objects - walls, player, shots, etc.
 */
public abstract class GameObject extends JComponent {

    // X, Y coordinates of object
    protected int positionX, positionY;
    protected int centerX, centerY;

	// variables needed for object drawing
    protected Handler handler;
    protected Image image;
    protected Image image2;
    protected int height;
    protected int width;
    protected Rectangle rectangle;
    protected double ID;

    /**
     * Creates the game object.
     *
     * @param positionX coordinate X, where object is drawn
     * @param positionY coordinate Y, where object is drawn
     * @param handler   handler
     */
    public GameObject(int positionX, int positionY, Handler handler) {
        this.handler = handler;
        getMeasurements();
		this.positionX = positionX;
        this.positionY = positionY;
        adjustPosition();
        this.centerX = positionX + (height / 2);
        this.centerY = positionY + (width / 2);
        this.rectangle = new Rectangle(positionX, positionY, width, height);
    }

    
    private void adjustPosition() {
    	if (this instanceof Shot == false) {
    		this.positionX = positionX/32*handler.getTileSize();
            this.positionY = positionY/32*handler.getTileSize();
		}
	}

	private void getMeasurements() {
		if (this instanceof Enemy || this instanceof Player) {
			
			this.width = handler.getObjectSize();
			this.height = handler.getObjectSize();
		}
		else if (this instanceof Item){
			this.width = handler.getObjectSize()/2;
			this.height = handler.getObjectSize()/2;
		}
		else {
			this.width = handler.getTileSize();
			this.height = handler.getTileSize();
		}
		
	}

	/**
     * Loads the image of the game object.
     */
    public void loadImage() {
    	try (InputStream input = new FileInputStream("resources/imageID.properties")) {
            Properties imageIDprop = new Properties();
            imageIDprop.load(input);
            String objectID = String.valueOf(ID);
            String objectImagePath = imageIDprop.getProperty(objectID);
            
            image = ImageIO.read(new File(objectImagePath));
            
            image = image.getScaledInstance(width, height, Image.SCALE_FAST);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    	/*
    }

    /**
     * Draws the game object.
     *
     * @param gr graphic canvas
     */
    public void drawObject(Graphics gr) {
        Graphics2D g = (Graphics2D) gr.create();
        if (ID == 6 && MamaZombie.isMamaShooting) {        // animation of MamaZombie
            try {
                this.image2 = ImageIO.read(new File("obr/objektyHry/enemy/4.png"));
                image2 = image2.getScaledInstance(48, 48, Image.SCALE_FAST);
            } catch (IOException e) {
                e.printStackTrace();
            }
            g.drawImage(image2, positionX, positionY, null);
            g.dispose();
        } else {
            g.drawImage(image, positionX, positionY, null);
            g.dispose();
        }
    }

    /**
     * Returns rectangle of the game object that is used in collisions.
     */
    public Rectangle getBounds() {
        return new Rectangle(positionX, positionY, width, height);
    }
}
