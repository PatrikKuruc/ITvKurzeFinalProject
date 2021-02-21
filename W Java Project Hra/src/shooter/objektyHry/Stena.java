package shooter.objektyHry;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import shooter.Platno;

public class Stena extends ObjektHry{
    /**
     * Zostroji sa objekt hry
     *
     * @param x  suradnica x, na ktorej sa ma objekt hry vytvorit
     * @param y  suradnica y, na ktorej sa ma objekt hry vytvorit
     * @param id rozpoznovacie id, aby sa vedelo, o aky typ objektu sa jedna
     */
    public Stena(int x, int y, Platno platno, Handler handler) {
        super(x, y, platno, handler);
    }


    @Override
    public void vykresli(Graphics g2) {
    	Graphics2D g = (Graphics2D) g2.create();
        g.setColor(Color.black);
        g.fillRect(poziciaX,poziciaY,32,32);
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
