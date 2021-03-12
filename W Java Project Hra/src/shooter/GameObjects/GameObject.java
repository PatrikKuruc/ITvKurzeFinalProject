package shooter.GameObjects;

import java.awt.*;
import java.io.File;
import java.io.IOException;

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
    protected int height = 32;
    protected int width = 32;
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
        this.positionX = positionX;
        this.positionY = positionY;
        this.centerX = positionX + (width / 2);
        this.centerY = positionY + (height / 2);
        this.handler = handler;
        this.rectangle = new Rectangle(positionX, positionY, width, height);
    }

    /**
     * Loads the image of the game object.
     */
    public void loadImage() {

        double IDint = ID;

        if (IDint == 0) {
            try {
                image = ImageIO.read(new File("obr/objektyHry/trava/5.png"));
                image = image.getScaledInstance(32, 32, Image.SCALE_FAST);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (IDint == 1) {
            try {
                image = ImageIO.read(new File("obr/objektyHry/stena/11.png"));
                image = image.getScaledInstance(32, 32, Image.SCALE_FAST);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (IDint == 3) {
            try {
                image = ImageIO.read(new File("obr/objektyHry/enemy/1.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (IDint == 4.1) {
            try {
                image = ImageIO.read(new File("obr/objektyHry/item/1.png"));
                image = image.getScaledInstance(32, 32, Image.SCALE_FAST);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (IDint == 4.2) {
            try {
                image = ImageIO.read(new File("obr/objektyHry/item/2.png"));
                image = image.getScaledInstance(32, 32, Image.SCALE_FAST);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (IDint == 5) {
            try {
                this.image = ImageIO.read(new File("obr/strela/1.png"));
                image = image.getScaledInstance(15, 5, Image.SCALE_FAST);

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (IDint == 6) {
            try {
                this.image = ImageIO.read(new File("obr/objektyHry/enemy/3.png"));
                image = image.getScaledInstance(32, 32, Image.SCALE_FAST);

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (ID == 7) {
            try {
                this.image = ImageIO.read(new File("obr/strela/4.png"));
                image = image.getScaledInstance(513 / 10, 173 / 10, Image.SCALE_FAST);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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
                image2 = image2.getScaledInstance(32, 32, Image.SCALE_FAST);
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
