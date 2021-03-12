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
	private String imagePath;
	
	
	public ObjectBtn(String ID, String imagePath, HandlerMapGen handlerMapGen) {
		super();
		this.ID = Double.parseDouble(ID);
		this.handler = handlerMapGen;
		this.imagePath = imagePath;
		this.handler = handlerMapGen;
		nacitajObr();
		
		setIcon(imageIcon);
		setPreferredSize(new Dimension(handlerMapGen.velkostPolicka, handlerMapGen.velkostPolicka));
		setSize(getPreferredSize());
		addActionListener(this);
	}

	private void nacitajObr() {
		try {
			image = ImageIO.read(new File(this.imagePath));
			image = image.getScaledInstance(handler.velkostPolicka, handler.velkostPolicka, Image.SCALE_FAST);
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
