package com.nla.web;

public class monThread extends Thread {

    /* le thread dure entre 1 et 10 secondes*/
    public void run() {
        long start = System.currentTimeMillis();
        int lower = 1;
        int higher = 10;
        int random = (int) (Math.random() * (higher - lower)) + lower;

        // boucle tant que la dur�e de vie du thread est < � n secondes
        while (System.currentTimeMillis() < (start + (1000 * random))) {
            // traitement
            System.out.println("Ligne affich�e par le thread" + random);
            try {
                // pause

                Thread.sleep(1000);
            } catch (InterruptedException ex) {
            }
        }
        System.out.println("fin du thread");
    }
}
