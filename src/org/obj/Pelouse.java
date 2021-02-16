package org.obj;

import java.util.ArrayList;
import java.util.List;

public class Pelouse {
    // Grille qui va contenir soit : " ", "Tondu", le nom de la Tondeuse
    private String grille[][];
    // Liste des tondeuses de cette pelouse
    private List<Tondeuse> tondeuses;
    // coordonnée n,m de la case en haut à gauche de la grille
    private int n;
    private int m;

    /*
     * Constructors
     * */
    public Pelouse() {
        this.grille = new String[6][6];
        this.tondeuses = new ArrayList<>();
        this.n = 5;
        this.m = 5;
    }

    public Pelouse(int n, int m) {
        this.grille = new String[n+1][m+1];
        this.tondeuses = new ArrayList<>();
        this.n = n;
        this.m = m;
    }

    /*
     * Methods
     * */

}
