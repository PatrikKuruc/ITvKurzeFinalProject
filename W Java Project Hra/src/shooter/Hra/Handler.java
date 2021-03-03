package shooter.Hra;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.Timer;

import shooter.ObjektyHry.*;

/**
 * Trieda Handler sluzi na nacitanie a spracovanie objektov hry.
 * - pridavanie objektov, mazanie objektov
 * - vykreslovanie a aktualizacia objektov
 */
public class Handler {

    private int poziciaHracaX, poziciaHracaY;

    public ArrayList<ObjektHry> statickeObjekty = new ArrayList<>();
    public ArrayList<ObjektHry> pohybliveObjekty = new ArrayList<>();
    public int zivot = 100;
    public int zasobnik = 10;
    public int score;
    public int velkostPolicka = 32;
    public int zranenia = 0;

    private boolean bezi = true;
    private Timer timer;

    /**
     * Vytvori handler
     */
    public Handler() {

    }

    public Handler(Timer timer) {
        this.timer = timer;
    }

    /**
     * Hrac vystreli strelu
     */
    public void playerShoot() {
        if (zasobnik > 0) {
            addObject(new Strela(5, poziciaHracaX, poziciaHracaY, this));
            zasobnik--;
        }
    }

    /*
    MamaZombie vystreli strelu.
     */
    public void enemyShoot() {
        addObject(new StrelaEnemy(7, MamaZombie.getPoziciaEnemyX(), MamaZombie.getPoziciaEnemyY(), this));
    }

    /**
     * Aktualizuje objekty hry.
     */
    public void aktualizujObjektyHry() {
        for (int i = 0; i < pohybliveObjekty.size(); i++) {
            ObjektHry novyObjekt = pohybliveObjekty.get(i);
            if (novyObjekt instanceof PohyblivyObjektHry) {
                ((PohyblivyObjektHry) novyObjekt).aktualizujObjektHry();
            } else {
                continue;
            }
        }


    }

    /**
     * Vykresluje vsetky objekty zo zoznamu objekty. Najprv staticke potom pohyblive.
     *
     * @param g graficke platno
     */
    public void vykresliObjektyHry(Graphics g) {
        for (int i = 0; i < statickeObjekty.size(); i++) {
            ObjektHry novyObjekt = statickeObjekty.get(i);
            novyObjekt.vykresli(g);
        }

        for (int i = 0; i < pohybliveObjekty.size(); i++) {
            ObjektHry novyObjekt = pohybliveObjekty.get(i);
            if (novyObjekt instanceof Player) {

            } else {
                novyObjekt.vykresli(g);
            }
        }

        // spawne random lekarnicku, ak zivot klesne pod 50
        if (zivot <= 50) {
            Random randomZivot = new Random();

            int pocitadlo = 0;
            int nahodne = randomZivot.nextInt(nahravacMapy.spawnPointLekarnika.size());

            for (Integer i : nahravacMapy.spawnPointLekarnika.keySet()) {
                if (pocitadlo == nahodne && Item.pocetLekarniciek <= 0) {
                    addObject(new Item(4.2, i, nahravacMapy.spawnPointLekarnika.get(i), velkostPolicka, velkostPolicka, this));
                    Item.pocetLekarniciek++;
                }
                pocitadlo++;
            }
        }

        // spawne novy zasobnik, ak klesne pod 5
        if (zasobnik <= 5) {
            Random randomZivot = new Random();

            int pocitadlo = 0;
            int nahodne = randomZivot.nextInt(nahravacMapy.spawnPointZasobnik.size());

            for (Integer i : nahravacMapy.spawnPointZasobnik.keySet()) {
                if (pocitadlo == nahodne && Item.pocetZasobnikov <= 0) {
                    addObject(new Item(4.1, i, nahravacMapy.spawnPointZasobnik.get(i), velkostPolicka, velkostPolicka, this));
                    Item.pocetZasobnikov++;
                }
                pocitadlo++;
            }
        }
    }

    public void vykresliHraca(Graphics g) {
        for (int i = 0; i < pohybliveObjekty.size(); i++) {
            ObjektHry novyObjekt = pohybliveObjekty.get(i);
            if (novyObjekt instanceof Player) {
                novyObjekt.vykresli(g);
            } else {

            }
        }
    }

    public boolean isBezi() {
        return bezi;
    }

    /**
     * Prida objekt do hry.
     *
     * @param novyObjekt novy objekt
     */
    public void addObject(ObjektHry novyObjekt) {
        ObjektHry objektNaPridanie = novyObjekt;
        if (objektNaPridanie instanceof PohyblivyObjektHry) {
            pohybliveObjekty.add(objektNaPridanie);
        } else {
            statickeObjekty.add(objektNaPridanie);
        }
    }

    /**
     * Vymaze objekt z hry.
     *
     * @param objektHry objekt hry
     */
    public void removeObject(ObjektHry objektHry) {
        ObjektHry objektNaPridanie = objektHry;
        if (objektNaPridanie instanceof PohyblivyObjektHry) {
            pohybliveObjekty.remove(objektNaPridanie);
        } else {
            statickeObjekty.remove(objektNaPridanie);
        }


        int pocetzombies = 0;
        for (ObjektHry objektHryy : pohybliveObjekty) {
            if (objektHryy instanceof Enemy) {
                pocetzombies++;
            }
        }
        if (objektHry instanceof Enemy && pocetzombies <= 10) {

            // spawne MamaZombie
            if (score == 24) {
                for (Integer i : nahravacMapy.spawnPointMama.keySet()) {
                    addObject(new MamaZombie(6, i, nahravacMapy.spawnPointMama.get(i), velkostPolicka, velkostPolicka, this));
                }
            }

            // spawnuje zombikov
            if (score <= 14) {
                Random randomZombie = new Random();
                int pocitadlo = 0;
                int nahodne = randomZombie.nextInt(nahravacMapy.spawnPointEnemy.size());

                for (Integer i : nahravacMapy.spawnPointEnemy.keySet()) {

                    if (pocitadlo == nahodne) {
                        addObject(new Enemy(3, i, nahravacMapy.spawnPointEnemy.get(i), velkostPolicka, velkostPolicka, this));
                    }

                    // zrychli pohyb zombikov
                    if (score >= 9) {
                        Settings.enemySpeed = 2;
                    }

                    // rychly este viac kazdeho druheho
                    if (score >= 9 && score % 2 == 0) {
                        Settings.enemySpeed = 2.5;
                    }

                    pocitadlo++;
                }
            }
        }
    }

    // gettre a settre

    public int getPoziciaHracaY() {
        return poziciaHracaY;
    }

    public void setPoziciaHracaY(int poziciaHracaY) {
        this.poziciaHracaY = poziciaHracaY;
    }

    public int getPoziciaHracaX() {
        return poziciaHracaX;
    }

    public void setPoziciaHracaX(int poziciaHracaX) {
        this.poziciaHracaX = poziciaHracaX;
    }

    public void pauseGame() {
        if (bezi) {
            bezi = false;
            timer.stop();

        } else {
            bezi = true;
            timer.start();
        }

    }
}
