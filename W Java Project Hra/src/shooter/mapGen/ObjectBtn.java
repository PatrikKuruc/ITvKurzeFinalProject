package shooter.mapGen;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class ObjectBtn extends JButton implements ActionListener{
	
	private HandlerMapGen handler;
	private Icon imageIcon;
	private Image image;
	private double ID;
	
	public String getID() {
		return String.valueOf(ID);
	}

	public String getImagePath() {
		return imagePath;
	}

	private String imagePath;
	
	
	public ObjectBtn(String ID, String imagePath, HandlerMapGen handlerMapGen) {
		super();
		this.ID = Double.parseDouble(ID);
		this.handler = handlerMapGen;
		this.imagePath = imagePath;
		this.handler = handlerMapGen;
		loadImage();
		
		setIcon(imageIcon);
		setPreferredSize(new Dimension(handlerMapGen.tileSize, handlerMapGen.tileSize));
		setSize(getPreferredSize());
		addActionListener(this);
	}

	private void loadImage() {
		try {
			image = ImageIO.read(new File(this.imagePath));
			image = image.getScaledInstance(handler.tileSize, handler.tileSize, Image.SCALE_FAST);
			imageIcon = new ImageIcon(image);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		handler.setSelectedItemID(this.ID);
	}
}
