package shooter.objektyHry;

import java.awt.Graphics;
import java.awt.Graphics2D;

import shooter.Platno;

public abstract class PohyblivyObjektHry extends ObjektHry {
	// premenne ktore maju vsetky pohyblive objekty - strely, hrac, enemy 
	protected double uholX;
	protected double uholY;
	protected double rotacia;
	protected double vecX;
	protected double vecY;
			
	public abstract void pohni();
	public abstract void zistiKoliziu();
	
	public PohyblivyObjektHry(int poziciaX, int poziciaY, Platno platno, Handler handler) {
		super(poziciaX, poziciaY, platno, handler);
	}

	@Override
	public void aktualizujObjektHry() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void vykresli(Graphics gr) {
		Graphics2D g = (Graphics2D) gr.create();
		g.rotate(rotacia, centerX, centerY);
		super.vykresli(g);
		g.dispose();
	}

}
