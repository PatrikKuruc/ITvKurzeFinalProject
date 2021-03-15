package shooter.GameObjects;

import shooter.Game.Handler;
import shooter.Game.Settings;
import shooter.Game.SoundEffect;

/**
 * Class Enemy creates zombie enemies.
 */
public class Enemy extends MovingGameObject {

    private SoundEffect soundEffect;
    protected int healthZombie = 100;
    protected int radius;
    protected int score;

    /**
     * Creates object of enemy type.
     *
     * @param ID              ID of the object
     * @param positionX        object position, X coordinate of left upper corner
     * @param positionY        object position, Y coordinate of left upper corner
     * @param newObjectHeight height of new object
     * @param newObjectWidth  width of new object
     * @param handler         handler
     */
    public Enemy(double ID, int positionX, int positionY, int newObjectWidth, int newObjectHeight, Handler handler) {
        super(positionX, positionY, handler);
        this.ID = ID;
        this.width = newObjectWidth;
        this.height = newObjectHeight;
        super.loadImage();
        velX = Settings.enemySpeed;
        velY = Settings.enemySpeed;
        this.radius = 200;
        this.score = 1;
    }

    /**
     * Performs specific actions when collision between Enemy and other game object occurs.
     *
     * @param gameObject game object
     */
    @Override
    public void performCollisionEvent(GameObject gameObject) {
        if (gameObject instanceof Shot) {
            healthZombie -= 50;
            if (healthZombie <= 0) {
                handler.removeObject(this);
                handler.score+=this.score;

                soundEffect = new SoundEffect();
                soundEffect.setFileZomebieDeath();
                soundEffect.play();
            }
            handler.removeObject(gameObject);
        }
    }

    /**
     * Updates the direction of the Enemy.
     */
    @Override
    public void findDirection() {
        destinationX = handler.getPositionPlayerX();
        destinationY = handler.getPositionPlayerY();

        this.angleX = destinationX - positionX;
        this.angleY = destinationY - positionY;

        double distance = Math.sqrt(Math.pow(angleX, 2) + Math.pow(angleY, 2));

        if (Math.abs(distance) < radius) {

            if (Math.abs(angleX) > 1) {
                vecX = (angleX * 2 / distance);
            }
            if (Math.abs(angleY) > 1) {
                vecY = (angleY * 2 / distance);
            }
        }

        if (Math.abs(distance) > radius) {
            vecX = 0;
            vecY = 0;
        }
    }
}
