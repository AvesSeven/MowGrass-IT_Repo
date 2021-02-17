package org.obj;

import java.util.ArrayList;
import java.util.List;

public class Pelouse {
    // Grille qui va contenir soit : " ", "Tondu", le nom de la Tondeuse
    private String grille[][];
    // Liste des tondeuses de cette pelouse
    private List<Tondeuse> tondeuses;
    // coordonnées n,m de la case en haut à gauche de la grille
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

        for (int i = 0; i <= 5; i++) {
            for (int j = 0; j <= 5; j++) {
                this.grille[i][j] = ".";
            }
        }
    }

    public Pelouse(int n, int m) {
        this.n = n;
        this.m = m;

        this.grille = new String[this.n + 1][this.m + 1];
        this.tondeuses = new ArrayList<>();

        for (int i = 0; i <= this.n; i++) {
            for (int j = 0; j <= this.m; j++) {
                this.grille[i][j] = ".";
            }
        }
    }

    /*
     * Methods
     * */
    public void afficherPelouse() {

        for (int j = m; j >= 0; j--) {
            for (int i = 0; i <= n; i++) {
                System.out.print("\t|\t" + this.grille[i][j]);
            }
            System.out.println("\t|");
        }
        System.out.println();
    }

    public int ajouterTondeuse(Tondeuse tondeuse)
    {

        if(this.grille[tondeuse.posX][tondeuse.posY].charAt(0) == '.')
        {
            this.tondeuses.add(tondeuse);
            this.grille[tondeuse.posX][tondeuse.posY] = String.valueOf(tondeuse.id);
            return 1;
        }
        // return 0 si on ne peut pas ajouter la tondeuse
        return 0;
    }

    public int instructionTondeuse(char instruction, int numero) {
        // -3 valeur par défaut
        int res = -3;

        if (instruction == 'D') {
            res = this.tournerDroite(numero);
        } else if (instruction == 'G') {
            res = this.tournerGauche(numero);
        } else if (instruction == 'A') {
            res = this.avancer(numero);
        } else {
            // -2 = problème sur le nom de l'instruction
            return -2;
        }
        return res;
    }

    private int tournerDroite(int numero) {
        char orientation = this.tondeuses.get(numero).getOrientation();

        if (orientation == 'N') {
            orientation = 'E';
        } else if (orientation == 'E') {
            orientation = 'S';
        } else if (orientation == 'S') {
            orientation = 'O';
        } else if (orientation == 'O') {
            orientation = 'N';
        } else {
            // 0 = problème avec l'orientation
            return 0;
        }

        this.tondeuses.get(numero).setOrientation(orientation);
        return 1;
    }

    private int tournerGauche(int numero) {
        char orientation = this.tondeuses.get(numero).getOrientation();

        if (orientation == 'N') {
            orientation = 'O';
        } else if (orientation == 'O') {
            orientation = 'S';
        } else if (orientation == 'S') {
            orientation = 'E';
        } else if (orientation == 'E') {
            orientation = 'N';
        } else {
            // 0 = problème avec l'orientation
            return 0;
        }

        this.tondeuses.get(numero).setOrientation(orientation);
        return 1;
    }

    private int avancer(int numero) {
        int posX = this.tondeuses.get(numero).posX;
        int posY = this.tondeuses.get(numero).posY;
        char orientation = this.tondeuses.get(numero).orientation;

        if (((orientation == 'N') && (posY + 1 <= m) && ((this.grille[posX][posY+1].charAt(0) == '.') || (this.grille[posX][posY+1].charAt(0) == "T".charAt(0))))
                || ((orientation == 'E') && (posX + 1 <= n) && ((this.grille[posX+1][posY].charAt(0) == '.') || (this.grille[posX+1][posY].charAt(0) == "T".charAt(0))))
                || ((orientation == 'S') && (posY - 1 >= 0) && ((this.grille[posX][posY-1].charAt(0) == '.') || (this.grille[posX][posY-1].charAt(0) == "T".charAt(0))))
                || ((orientation == 'O') && (posX - 1 >= 0) && ((this.grille[posX-1][posY].charAt(0) == '.') || (this.grille[posX-1][posY].charAt(0) == "T".charAt(0))))){
            grille[this.tondeuses.get(numero).posX][this.tondeuses.get(numero).posY] = "T";
            this.tondeuses.get(numero).avancerTondeuse(orientation);
            this.grille[this.tondeuses.get(numero).posX][this.tondeuses.get(numero).posY] = String.valueOf(this.tondeuses.get(numero).id);
            return 1;
        }
        // -1 = ne peut pas avancer
        return -1;
    }

    public List<Tondeuse> getTondeuses() {
        return tondeuses;
    }
}
