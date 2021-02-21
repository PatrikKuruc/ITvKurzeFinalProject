package shooter.objektyHry;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import shooter.Platno;

public class Trava extends ObjektHry{
    /**
     * Zostroji sa objekt hry
     *
     * @param x  suradnica x, na ktorej sa ma objekt hry vytvorit
     * @param y  suradnica y, na ktorej sa ma objekt hry vytvorit
     * @param id rozpoznovacie id, aby sa vedelo, o aky typ objektu sa jedna
     */
    public Trava(int x, int y, Platno platno, Handler handler) {
        super(x, y, platno, handler);
        
        try {
			image = ImageIO.read(new File("obr/trava.png"));
			image = image.getScaledInstance(32, 32, Image.SCALE_FAST);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }


    @Override
    public void vykresli(Graphics gr) {
    	Graphics2D g = (Graphics2D) gr.create();
		g.drawImage(image, poziciaX, poziciaY, null);
		g.dispose();
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(poziciaX,poziciaY,32,32);
    }

	@Override
	public void aktualizujObjektHry() {
		// TODO Auto-generated method stub
		
	}
}
