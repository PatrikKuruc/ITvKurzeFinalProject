package shooter.ObjektyHry;

import shooter.Game.Handler;

public class StrelaEnemy extends PohyblivyObjektHry {


    /**
     * Vytvara pohyblivy objekt hry
     *
     * @param poziciaX pozicia objektu, X suradnica laveho horneho rohu
     * @param poziciaY pozicia objektu, Y suradnica laveho horneho rohu
     * @param handler  handler
     */
    public StrelaEnemy(double ID, int poziciaX, int poziciaY, Handler handler) {
        super(poziciaX, poziciaY, handler);
        this.ID = ID;
        super.nacitajObrazok();
        this.destinationX = handler.getPositionPlayerY();
        this.destinationY = handler.getPositionPlayerX();
        this.width = 513 / 10;
        this.height = 173 / 10;
        this.velX = 1;
        this.velY = 1;

        // vypocitaj vektory uhla, pod ktorym bola strela vystrelena
        this.uholX = destinationX-poziciaX;
        this.uholY = destinationY-poziciaY;

        // vypocet vektorov pohybu strely (pytagorova veta)
        double vzdialenost = Math.sqrt(Math.pow(uholX, 2) + Math.pow(uholY, 2));
        vecX = (float) (uholX * 20 / vzdialenost);
        vecY = (float) (uholY * 20 / vzdialenost);
    }

    @Override
    public void koliziaSoStenouX() {
        super.koliziaSoStenouX();
        handler.removeObject(this);
        MamaZombie.mamaStriela = false;
    }

    @Override
    public void koliziaSoStenouY() {
        super.koliziaSoStenouY();
        handler.removeObject(this);
        MamaZombie.mamaStriela = false;
    }

    @Override
    public void vykonajKoliznyEvent(ObjektHry objekt) {
        if (objekt instanceof Player) {
            handler.removeObject(this);
        }

    }


    @Override
    public void aktualizujRotaciu() {
        rotacia = Math.atan2(uholY, uholX);
    }

    @Override
    public void zistiSmer() {
    }
}
