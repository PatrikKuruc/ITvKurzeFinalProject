package shooter.Game;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;
import java.util.Scanner;

import shooter.GameObjects.*;

/**
 * Class MapLoader loads the images of the game.
 */
public class MapLoader {

    private Handler handler;
    private Scanner scanner;
    private File suborMapy;

    // HashMaps for saving the coordinates of spawning objects
    public static HashMap<Integer, Integer> spawnPointEnemy = new HashMap<>();
    public static HashMap<Integer, Integer> spawnPointMama = new HashMap<>();
    public static HashMap<Integer, Integer> spawnPointHealthKit = new HashMap<>();
    public static HashMap<Integer, Integer> spawnPointAmmoKit = new HashMap<>();

    /**
     * Creates MapLoader.
     *
     * @param handler handler
     * @throws FileNotFoundException FileNotFoundException
     */
    public MapLoader(Handler handler){
        this.handler = handler;
        loadMapFile();
    }

    private void loadMapFile() {
    	try {
    		InputStream input = new FileInputStream("src/gameConfig.properties");
    		Properties gameProp = new Properties();
    		gameProp.load(input);
    		
    		String mapPath = "mapy\\" + gameProp.getProperty("Map"); 
    		
	    	this.suborMapy = new File(mapPath);
			this.scanner = new Scanner(suborMapy);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
     * Loads the maps.
     */
    public void nahrajMapu() {

        // adds game objects to the objec list according to the map location
        while (scanner.hasNextLine()) {
            String riadok = scanner.nextLine();

            String[] riadokPole = riadok.split(",");            // splits the string into the aray of strings ","

            double newObjectID = Double.parseDouble(riadokPole[0]);
            int newObjectPoziciaX = Integer.parseInt(riadokPole[1]);
            int newObjectPoziciaY = Integer.parseInt(riadokPole[2]);
            int newObjectWidth = Integer.parseInt(riadokPole[3]);
            int newObjectHeight = Integer.parseInt(riadokPole[4]);

            int ID = (int) newObjectID;
            
            if (ID == 1) {
                handler.addObject(new Wall(newObjectID, newObjectPoziciaX, newObjectPoziciaY, newObjectWidth, newObjectHeight, handler));
            } else if (ID != 1) {
            	if (ID == 0) {
                handler.addObject(new Grass(newObjectID, newObjectPoziciaX, newObjectPoziciaY, newObjectWidth, newObjectHeight, handler));
            	}
                if (ID == 2) {
                    handler.addObject(new Player(newObjectPoziciaX, newObjectPoziciaY, handler));
                }
                if (ID == 3) {
                    handler.addObject(new Enemy(newObjectID, newObjectPoziciaX, newObjectPoziciaY, newObjectWidth, newObjectHeight, handler));
                    spawnPointEnemy.put(newObjectPoziciaX, newObjectPoziciaY);
                }
                if (newObjectID == 4.1) {
                    new Item(newObjectID, newObjectPoziciaX, newObjectPoziciaY, newObjectWidth, newObjectHeight, handler);
                    spawnPointAmmoKit.put(newObjectPoziciaX, newObjectPoziciaY);
                }
                if (newObjectID == 4.2) {
                    new Item(newObjectID, newObjectPoziciaX, newObjectPoziciaY, newObjectWidth, newObjectHeight, handler);
                    spawnPointHealthKit.put(newObjectPoziciaX, newObjectPoziciaY);
                }
                if (newObjectID == 6.0) {
                    new MamaZombie(newObjectID, newObjectPoziciaX, newObjectPoziciaY, newObjectWidth, newObjectHeight, handler);
                    spawnPointMama.put(newObjectPoziciaX, newObjectPoziciaY);
                }
            }
        }
    }
}
