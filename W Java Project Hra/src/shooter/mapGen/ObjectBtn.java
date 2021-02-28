package shooter.mapGen;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class ObjectBtn extends JButton implements ActionListener{
	
	private HandlerMapGen handler;
	private Icon image;
	private double ID;
	
	public ObjectBtn(Double ID, HandlerMapGen handlerMapGen) {
		super();
		this.ID = ID;
		this.handler = handlerMapGen;
		nacitajObrazok();
		setIcon(image);
		setPreferredSize(new Dimension(48, 48));
		addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		handler.setKreslisObjektID(ID);
	}
	
	public void nacitajObrazok() {
    	if (ID==0) {
    			image = new ImageIcon("obr/trava/1.png");
    	}
    	else if (ID ==1) {
    			image = new ImageIcon("obr/stena/drevo/11.png");
		}
    	else if (ID==2) {
    		image = new ImageIcon("obr/hrac/modry/3.png");
    	}
    	else if (ID==3) {
    			image = new ImageIcon("obr/enemy/1.png");
    	}
    	
    	else if (ID==4.1) {
    			image = new ImageIcon("obr/item/1.png");
    	}
    	else if (ID==4.2) {
				image = new ImageIcon("obr/item/2.png");
    	}
	}
}
