package shooter.mapGen;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import javax.swing.JPanel;
import javax.swing.Timer;

public class PanelVyber extends JPanel {

	private Timer timer = new Timer(60, e -> repaint());
	private MGHandler handler;
	private List<File> ImageFileList;
	
	/**
	 * Create PanelVyber
	 * @param handler Map Generator Handler
	 */
	public PanelVyber(MGHandler handler) {
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
            if (file.isFile()) {
            } else if (file.isDirectory()) {
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
            imageSrc.forEach((key, value) -> add(new ObjectBtn(key.toString(), value.toString(), handler)));
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}