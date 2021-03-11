package shooter.Game;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.Timer;

import shooter.ObjektyHry.*;

/**
 * Class Handler loads and processes the game objects.
 * - adding / removing of the objects
 * - drawing / updating of the objects
 */
public class Handler {

    private int positionPlayerX, positionPlayerY;

    public ArrayList<ObjektHry> staticObjects = new ArrayList<>();
    public ArrayList<ObjektHry> movingObjects = new ArrayList<>();
    public int healthPlayer = 100;
    public int healthMama = 1000;
    public int ammo = 10;
    public int score = 0;
    public int blockSize = 32;
    public int damageTaken = 0;
    public SoundEffect soundEffect;
    private boolean isRunning = true;
    private Timer timer;
    PopupWindow popupWindow;

    /**
     * Creates handler.
     */
    public Handler(Timer timer) {
        this.timer = timer;
    }

    /**
     * Player shoots the shot.
     */
    public void playerShoot() {
        if (ammo > 0) {
            addObject(new Strela(5, positionPlayerX, positionPlayerY, this));
            ammo--;

            soundEffect = new SoundEffect();
            soundEffect.setFileShoot();
            soundEffect.play();
        }
    }

    /**
     * MamaZombie shoots the shot.
     */
    public void enemyShoot() {
        addObject(new StrelaEnemy(7, MamaZombie.getPoziciaEnemyX(), MamaZombie.getPoziciaEnemyY(), this));
    }

    /**
     * Updates the game objects.
     */
    public void updateGameObjects() {
        for (int i = 0; i < movingObjects.size(); i++) {
            ObjektHry newObject = movingObjects.get(i);
            if (newObject instanceof PohyblivyObjektHry) {
                ((PohyblivyObjektHry) newObject).aktualizujObjektHry();
            }
        }
    }

    /**
     * Draws all the objects from the object list; first static, then moving.
     *
     * @param g graphic canvas
     */
    public void drawGameObjects(Graphics g) {
        for (int i = 0; i < staticObjects.size(); i++) {
            ObjektHry newObject = staticObjects.get(i);
            newObject.vykresli(g);
        }

        for (int i = 0; i < movingObjects.size(); i++) {
            ObjektHry newObject = movingObjects.get(i);
            if (newObject instanceof Player) {

            } else {
                newObject.vykresli(g);
            }
        }

        // spawns random healthKit if the healthPlayer is equal or lower than 50
        if (healthPlayer <= 50) {
            Random randomHealthKit = new Random();

            int counter = 0;
            int random = randomHealthKit.nextInt(MapLoader.spawnPointHealthKit.size());

            for (Integer i : MapLoader.spawnPointHealthKit.keySet()) {
                if (counter == random && Item.pocetLekarniciek <= 0) {
                    addObject(new Item(4.2, i, MapLoader.spawnPointHealthKit.get(i), blockSize, blockSize, this));
                    Item.pocetLekarniciek++;
                }
                counter++;
            }
        }

        // calls gameover pop-up window, if player dies
        if (healthPlayer <= 0) {
            popupWindow = new PopupWindow(this);
        }

        // calls youWon pop-up window, if player beats mamaZombie
        if (healthMama <= 0) {
            popupWindow = new PopupWindow(this, healthMama);
        }

        // spawns random ammoKit if the ammo is equal or lower than 5
        if (ammo <= 5) {
            Random randomAmmoKit = new Random();

            int counter = 0;
            int random = randomAmmoKit.nextInt(MapLoader.spawnPointAmmoKit.size());

            for (Integer i : MapLoader.spawnPointAmmoKit.keySet()) {
                if (counter == random && Item.pocetZasobnikov <= 0) {
                    addObject(new Item(4.1, i, MapLoader.spawnPointAmmoKit.get(i), blockSize, blockSize, this));
                    Item.pocetZasobnikov++;
                }
                counter++;
            }
        }
    }

    /**
     * Draws player.
     *
     * @param g graphic canvas
     */
    public void drawPlayer(Graphics g) {
        for (int i = 0; i < movingObjects.size(); i++) {
            ObjektHry newObject = movingObjects.get(i);
            if (newObject instanceof Player) {
                newObject.vykresli(g);
            }
        }
    }

    /**
     * Returns true if game is running.
     *
     * @return true if game is running
     */
    public boolean isRunning() {
        return isRunning;
    }

    /**
     * Adds object to the game.
     *
     * @param newObject new object
     */
    public void addObject(ObjektHry newObject) {
        ObjektHry addObject = newObject;
        if (addObject instanceof PohyblivyObjektHry) {
            movingObjects.add(addObject);
        } else {
            staticObjects.add(addObject);
        }
    }

    /**
     * Removes object from the game.
     *
     * @param newObject new object
     */
    public void removeObject(ObjektHry newObject) {
        ObjektHry addObject = newObject;
        if (addObject instanceof PohyblivyObjektHry) {
            movingObjects.remove(addObject);
        } else {
            staticObjects.remove(addObject);
        }


        int enemyCounter = 0;
        for (ObjektHry gameObjects : movingObjects) {
            if (gameObjects instanceof Enemy) {
                enemyCounter++;
            }
        }
        if (newObject instanceof Enemy && enemyCounter <= 10) {

            // spawns MamaZombie
            if (score == 25) {
                for (Integer i : MapLoader.spawnPointMama.keySet()) {
                    addObject(new MamaZombie(6, i, MapLoader.spawnPointMama.get(i), blockSize, blockSize, this));

                    soundEffect = new SoundEffect();
                    soundEffect.setFileFinalBossSpawn();
                    soundEffect.play();
                }
            }

            // spawns enemies
            if (score <= 15) {
                Random randomEnemy = new Random();
                int counter = 0;
                int random = randomEnemy.nextInt(MapLoader.spawnPointEnemy.size());

                for (Integer i : MapLoader.spawnPointEnemy.keySet()) {

                    if (counter == random) {
                        addObject(new Enemy(3, i, MapLoader.spawnPointEnemy.get(i), blockSize, blockSize, this));
                    }

                    // speeds up the enemy movement
                    if (score >= 10) {
                        Settings.enemySpeed = 2;
                    }

                    // speeds up the enemy movement even more
                    if (score >= 10 && score % 2 == 0) {
                        Settings.enemySpeed = 2.5;
                    }

                    counter++;
                }
            }
        }
    }

    /**
     * Returns position X of the player.
     *
     * @return position X of the player
     */
    public int getPositionPlayerX() {
        return positionPlayerX;
    }

    /**
     * Returns position Y of the player.
     *
     * @return position Y of the player.
     */
    public int getPositionPlayerY() {
        return positionPlayerY;
    }

    /**
     * Sets the X position of the player.
     *
     * @param positionPlayerX position X of the player
     */
    public void setPositionPlayerX(int positionPlayerX) {
        this.positionPlayerX = positionPlayerX;
    }

    /**
     * Sets the Y position of the player.
     *
     * @param positionPlayerY position Y of the player
     */
    public void setPositionPlayerY(int positionPlayerY) {
        this.positionPlayerY = positionPlayerY;
    }

    /**
     * Pauses the game.
     */
    public void pauseGame() {
        if (isRunning) {
            isRunning = false;
            timer.stop();

        } else {
            isRunning = true;
            timer.start();
        }
    }

}
