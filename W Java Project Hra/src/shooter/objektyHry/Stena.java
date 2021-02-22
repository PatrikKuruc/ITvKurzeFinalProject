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
import shooter.Handler;

public class Stena extends ObjektHry{
    /**
     * Zostroji sa objekt hry
     *
     * @param x  suradnica x, na ktorej sa ma objekt hry vytvorit
     * @param y  suradnica y, na ktorej sa ma objekt hry vytvorit
     * @param id rozpoznovacie id, aby sa vedelo, o aky typ objektu sa jedna
     */
    public Stena(int x, int y, Handler handler) {
        super(x, y, handler);
        
        try {
			image = ImageIO.read(new File("obr/stena.png"));
			image = image.getScaledInstance(32, 32, Image.SCALE_FAST);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
