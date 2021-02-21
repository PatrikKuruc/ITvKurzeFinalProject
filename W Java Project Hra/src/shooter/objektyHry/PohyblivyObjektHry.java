package shooter.objektyHry;

import java.awt.Graphics;

import shooter.Platno;

public abstract class PohyblivyObjektHry extends ObjektHry {

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
	public void vykresli(Graphics g) {
		// TODO Auto-generated method stub
		
	}

}
