package shooter.GameObjects;

import shooter.Game.Handler;
import shooter.Game.Settings;

import java.awt.*;
import java.util.Random;

/**
 * Class MamaZombie represents the final boss in the game.
 */
public class MamaZombie extends MovingGameObject {

    public static int positionEnemyX, positionEnemyY;
    public static boolean isMamaShooting = false;


    /**
     * Creates MamaZombie in the game.
     *
     * @param ID              ID of the object
     * @param positionX       object position, X coordinate of left upper corner
     * @param positionY       object position, Y coordinate of left upper corner
     * @param newObjectWidth  height of new object
     * @param newObjectHeight width of new object
     * @param handler         handler
     */
    public MamaZombie(double ID, int positionX, int positionY, int newObjectWidth, int newObjectHeight, Handler handler) {
        super(positionX, positionY, handler);
        this.ID = ID;

        positionEnemyX = positionX;
        positionEnemyY = positionY;

        this.width = newObjectWidth;
        this.height = newObjectHeight;

        super.loadImage();

        velX = Settings.enemySpeed * 0.7;
        velY = Settings.enemySpeed * 0.7;
    }

    /**
     * Returns the X coordinate of Enemy.
     *
     * @return the X coordinate of Enemy
     */
    public static int getPositionEnemyX() {
        return positionEnemyX;
    }

    /**
     * Returns the Y coordinate of Enemy.
     *
     * @return the Y coordinate of Enemy
     */
    public static int getPositionEnemyY() {
        return positionEnemyY;
    }

    /**
     * Performs specific actions when collision between MamaZombie and other game object occurs.
     *
     * @param gameObject game object
     */
    @Override
    public void performCollisionEvent(GameObject gameObject) {

        if (gameObject instanceof Shot) {
            handler.healthMama -= 50;
            if (handler.healthMama <= 0) {
                handler.removeObject(this);
                handler.score += 25;
            }
            handler.removeObject(gameObject);
        }
    }

    /**
     * Updates the direction of the MamaZombie.
     */
    @Override
    public void findDirection() {
        destinationX = handler.getPositionPlayerX();
        destinationY = handler.getPositionPlayerY();

        this.angleX = destinationX - positionX;
        this.angleY = destinationY - positionY;

        double distance = Math.sqrt(Math.pow(angleX, 2) + Math.pow(angleY, 2));

        if (Math.abs(distance) > 1) {
            if (Math.abs(angleX) > 1) {
                vecX = (float) (angleX * 2 / distance);
            }
            if (Math.abs(angleY) > 1) {
                vecY = (float) (angleY * 2 / distance);
            }
        }

        if (Math.abs(distance) == 0) {
            vecX = 0;
            vecY = 0;
        }
    }

    /**
     * Draws the MamaZombie.
     *
     * @param gr graphic canvas.
     */
    public void drawObject(Graphics gr) {
        super.drawObject(gr);
        drawMamaZombieInfo(gr);
    }

    /**
     * Draws the information about MamaZombie; health.
     *
     * @param gr graphic canvas
     */
    private void drawMamaZombieInfo(Graphics gr) {
        // draws health-bar
        gr.setColor(Color.lightGray);
        gr.fillRect(462, 5, 200, 22);
        gr.setColor(Color.GREEN);
        gr.fillRect(462, 5, (handler.healthMama / 5), 22);
        gr.setColor(Color.BLACK);
        gr.drawRect(462, 5, 200, 22);

        gr.setColor(Color.black);
        gr.drawString(handler.healthMama + " / 1000", 530, 22);

        Random random = new Random();
        int counter = 0;
        int random2 = random.nextInt(60);

        // shoots random shots of MamaZombie
        if (counter == random2) {
            handler.enemyShoot();
            isMamaShooting = true;
        }
    }

    /**
     * Updates the MamaZombie object.
     */
    @Override
    public void updateGameObject() {
        destinationX = handler.getPositionPlayerX();
        destinationY = handler.getPositionPlayerY();
        positionEnemyX = centerX;
        positionEnemyY = centerY;

        super.updateGameObject();
    }
}
