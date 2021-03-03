package shooter.ObjektyHry;

import shooter.Hra.Handler;
import shooter.Hra.Settings;

import java.awt.*;
import java.util.Random;

public class MamaZombie extends PohyblivyObjektHry{

    //private int poziciaEnemyX, poziciaEnemyY;
    public static int poziciaEnemyX, poziciaEnemyY;
    public static boolean mamaStriela = false;

    /**
     * Vytvara pohyblivy objekt hry
     *
     * @param poziciaX pozicia objektu, X suradnica laveho horneho rohu
     * @param poziciaY pozicia objektu, Y suradnica laveho horneho rohu
     * @param handler  handler
     */
    public MamaZombie(double ID, int poziciaX, int poziciaY, int newObjectWidth, int newObjectHeight, Handler handler) {
        super(poziciaX, poziciaY, handler);
        this.ID = ID;

        poziciaEnemyX = poziciaX;
        poziciaEnemyY = poziciaY;

        this.width = newObjectWidth;
        this.height = newObjectHeight;

        zivot = 1000;
        super.nacitajObrazok();

        velX = Settings.enemySpeed*0.7;
        velY = Settings.enemySpeed*0.7;

    }

    public static int getPoziciaEnemyX(){
        return poziciaEnemyX;
    }

    public static int getPoziciaEnemyY(){
        return poziciaEnemyY;
    }

    @Override
    public void vykonajKoliznyEvent(ObjektHry objekt) {

        if (objekt instanceof Strela) {
            zivot-=50;
            if (zivot<=0) {
                handler.removeObject(this);
                handler.score += 20;
            }
            handler.removeObject(objekt);
        }
    }

    @Override
    public void zistiSmer() {
        destinationX = handler.getPoziciaHracaX();
        destinationY = handler.getPoziciaHracaY();

        this.uholX = destinationX - poziciaX;
        this.uholY = destinationY - poziciaY;

        double vzdialenost = Math.sqrt(Math.pow(uholX, 2) + Math.pow(uholY, 2));

        if (Math.abs(vzdialenost) > 1) {
            if (Math.abs(uholX) > 1) {
                vecX = (float) (uholX * 2 / vzdialenost);
            }
            if (Math.abs(uholY) > 1) {
                vecY = (float) (uholY * 2 / vzdialenost);
            }
        }

        if (Math.abs(vzdialenost) == 0) {
            vecX = 0;
            vecY = 0;
        }
    }

    public void vykresli(Graphics gr) {
        super.vykresli(gr);
        vykresliUdajeHraca(gr);
    }

    private void vykresliUdajeHraca(Graphics gr) {
        // vykreslenie health-baru
        gr.setColor(Color.lightGray);
        gr.fillRect(462,5,200,22);
        gr.setColor(Color.GREEN);
        gr.fillRect(462,5, (zivot/5), 22);
        gr.setColor(Color.BLACK);
        gr.drawRect(462,5,200,22);

        gr.setColor(Color.black);
        gr.drawString(zivot + " / 1000", 530,22);

        Random random = new Random();
        int pocitadlo = 0;
        int nahodne = random.nextInt(Settings.FPS/2);

        if(pocitadlo == nahodne) {
            handler.enemyShoot();
            mamaStriela = true;
        }
    }

    @Override
    public void aktualizujObjektHry() {
        destinationX = handler.getPoziciaHracaX();
        destinationY = handler.getPoziciaHracaY();
        poziciaEnemyX = centerX;
        poziciaEnemyY = centerY;

        super.aktualizujObjektHry();
    }
}
