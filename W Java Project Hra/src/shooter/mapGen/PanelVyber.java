package shooter.mapGen;

import java.awt.GridLayout;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.swing.JPanel;
import javax.swing.Timer;

public class PanelVyber extends JPanel {

	private Timer timer = new Timer(60, e -> repaint());
	private HandlerMapGen handler;
	private List<File> ImageFileList;
	
	private ArrayList<ObjectBtn> groundList = new ArrayList<>();
	private ArrayList<ObjectBtn> wallList = new ArrayList<>();
	private ArrayList<ObjectBtn> playerList = new ArrayList<>();
	private ArrayList<ObjectBtn> enemyList = new ArrayList<>();
	private ArrayList<ObjectBtn> itemsList = new ArrayList<>();
	
	
	/**
	 * Create PanelVyber
	 * @param handler Map Generator Handler
	 */
	public PanelVyber(HandlerMapGen handler) {
		this.handler=handler;
        setLayout(new GridLayout(0, 15, 10, 10));
        
        // sirka je 650
        ImageFileList = new ArrayList<File>();
        ImageFileList = loadImages("obr/objektyHry");

        writeImagesToProperties();
		createMapGenObjectBtns();
		timer.start();
	}
	
	/**
	 * Nacita zoznam obrazkov do listu ImageFileList
	 * @param directoryName 
	 * @return
	 */
	private List<File> loadImages(String directoryName) {
		File directory = new File(directoryName);
        List<File> resultList = new ArrayList<File>();

        // get all the files from a directory
        File[] fList = directory.listFiles();
        resultList.addAll(Arrays.asList(fList));
        for (File file : fList) {
            if (file.isDirectory()) {
                resultList.addAll(loadImages(file.getPath()));
            }
        }
        return resultList;
	}
	
	private void writeImagesToProperties() {
		try {
			OutputStream output = new FileOutputStream("src/imageID.properties");
			
			Properties imageSrc = new Properties();
			
			// rozsekaj imagePath na ID, typ objektu a imagepath
			for (File file : ImageFileList) {
				String imagePath = file.getPath();
				String imageID = file.getPath();
				String[] imageIDSubStrings = imageID.split("\\\\");
				String imageTyp = imageIDSubStrings[2];
				imageID = imageIDSubStrings[imageIDSubStrings.length-1];
				if (!Character.isDigit(imageID.charAt(0))) {
					continue;			
				}
				imageID = imageID.substring(0, imageID.length()-4);
				
				if (imageTyp.equalsIgnoreCase("trava")) {
					imageTyp = "0.";
				}
				else if (imageTyp.equalsIgnoreCase("stena")) {
					imageTyp = "1.";
				}
				else if (imageTyp.equalsIgnoreCase("hrac")) {
					imageTyp = "2.";
				}
				else if (imageTyp.equalsIgnoreCase("enemy")) {
					imageTyp = "3.";
				}
				else if (imageTyp.equalsIgnoreCase("item")) {
					imageTyp = "4.";
				}
				imageID = imageTyp+imageID;
				
				// System.out.println(imageID + " " + imagePath);
				// zapis do properties
				imageSrc.setProperty(imageID, imagePath);
			}
			imageSrc.store(output, null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void createMapGenObjectBtns() {
		try (InputStream input = new FileInputStream("src/imageID.properties")) {
            Properties imageSrc = new Properties();
            imageSrc.load(input);

            // vytvori vsetky objekty v imageID.Properties
            imageSrc.forEach((key, value) -> addObject(key.toString(), value.toString()));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

	private void addObject(String ID, String imagePath) {
		if (ID.startsWith("0")) {
			groundList.add(new ObjectBtn(ID, imagePath, handler));
		}
		else if (ID.startsWith("1")) {
			wallList.add(new ObjectBtn(ID, imagePath, handler));
		}
		else if (ID.startsWith("2")) {
			playerList.add(new ObjectBtn(ID, imagePath, handler));
		}
		else if (ID.startsWith("3")) {
			enemyList.add(new ObjectBtn(ID, imagePath, handler));
		}
		else if (ID.startsWith("4")) {
			itemsList.add(new ObjectBtn(ID, imagePath, handler));
		}
		
		
		for (int i = 0; i < groundList.size(); i++) {
			add(groundList.get(i));
		}
		for (int i = 0; i < wallList.size(); i++) {
			add(wallList.get(i));
		}
		for (int i = 0; i < enemyList.size(); i++) {
			add(enemyList.get(i));
		}
		for (int i = 0; i < itemsList.size(); i++) {
			add(itemsList.get(i));
		}
		for (int i = 0; i < playerList.size(); i++) {
			add(playerList.get(i));
		}
	}

	public void setVybranyZoznam(String selectedValue) {
		// removeAll();
		// selected value = walls/ground/enemy/items/player
		System.out.println(selectedValue);
	}
}