package shooter.ObjektyHry;

import java.awt.Rectangle;

import shooter.Hra.Handler;
import shooter.Hra.UserInput;

/**
 * Trieda vytvara pohyblivy objekt typu strela
 */
public class Strela extends PohyblivyObjektHry {
	
	/**
	 * Vytvori objekt typu strela
	 * @param poziciaX pozicia objektu, X suradnica laveho horneho rohu
	 * @param poziciaY pozicia objektu, Y suradnica laveho horneho rohu
	 * @param handler handler
	 */
	public Strela(double ID, int poziciaX, int poziciaY, Handler handler) {
		super(poziciaX, poziciaY, handler);
		this.ID=ID;
		super.nacitajObrazok();
		this.destinationX = UserInput.getMouseX();
		this.destinationY = UserInput.getMouseY();
		this.width=15;
		this.height=5;
		this.velY = 1;
		this.velX = 1;
		
		// vypocitaj vektory uhla, pod ktorym bola strela vystrelena
		this.uholX = destinationX-poziciaX;
		this.uholY = destinationY-poziciaY;
		
		// vypocet vektorov pohybu strely (pytagorova veta)
		double vzdialenost = Math.sqrt(Math.pow(uholX, 2) + Math.pow(uholY, 2));
        vecX = (float) (uholX * 20 / vzdialenost);
        vecY = (float) (uholY * 20 / vzdialenost);
        this.rectangle.setBounds(poziciaX, poziciaY, 10, 10);
	}
	
	@Override
	public void koliziaSoStenouX() {
		handler.removeObject(this);
	}
	@Override
	public void koliziaSoStenouY() {
		handler.removeObject(this);
	}
	
	@Override
	public void zistiSmer() {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void aktualizujRotaciu() {
		rotacia=Math.atan2(uholY, uholX);
	}

	@Override
	public void vykonajKoliznyEvent(ObjektHry objekt) {
		if (objekt instanceof Enemy) {
			handler.removeObject(this);
		}
	}
}
