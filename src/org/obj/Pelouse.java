package org.obj;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe Pelouse : permet d'initialiser les attributs d'une pelouse, qui contient des objet de classe Tondeuse,
 * et de gérer une partie des fonctionnalitées de celle-ci.
 *
 * @author Amandine Carlier
 * @version 1.0
 */

public class Pelouse {
    // Grille qui va contenir soit :
    // ".", "T", le numéro de la Tondeuse (id).
    private String[][] grille;
    private List<Tondeuse> tondeuses;
    // coordonnées n,m de la case en haut à droite de la grille
    private int n;
    private int m;

    /**
     * Constructeur vide qui va permettre d'initialiser un objet Pelouse
     * avec des valeurs par défaut.
     */
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

    /**
     * Constructeur qui va permettre d'initialiser les attributs d'une Pelouse.
     * @param n : Position en x de la case en haut à droite de la Pelouse.
     * @param m : Position en y de la case en haut à droite de la Pelouse.
     */
    public Pelouse(int n, int m) {
        this.n = n;
        this.m = m;

        this.grille = new String[this.n + 1][this.m + 1];
        this.tondeuses = new ArrayList<>();

        // Initialisation de la Pelouse avec des ".", cela correspondra à une parcelle de Pelouse non tondue.
        for (int i = 0; i <= this.n; i++) {
            for (int j = 0; j <= this.m; j++) {
                this.grille[i][j] = ".";
            }
        }
    }

    /**
     * Méthode qui va permettre d'afficher la Pelouse en console
     * en respectant l'orientation du repère (origine en bas à gauche).
     */
    public void afficherPelouse() {

        for (int j = m; j >= 0; j--) {
            for (int i = 0; i <= n; i++) {
                System.out.print("\t|\t" + this.grille[i][j]);
            }
            System.out.println("\t|");
        }
        System.out.println();
    }

    /**
     * Méthode qui permet d'ajouter une Tondeuse à la liste tondeuses de Pelouse.
     * Elle vérifie s'il n'y a pas déjà une Tondeuse aux coordonnées de la nouvelle Tondeuse.
     * @param tondeuse : Nouvelle Tondeuse à ajouter.
     * @return Un entier qui va permettre de savoir si l'ajout a été réalisée.
     */
    public int ajouterTondeuse(Tondeuse tondeuse)
    {

        if(this.grille[tondeuse.posX][tondeuse.posY].charAt(0) == '.')
        {
            this.tondeuses.add(tondeuse);
            this.grille[tondeuse.posX][tondeuse.posY] = String.valueOf(tondeuse.id);

            // return 1 si la Tondeuse s'est bien ajoutée à la liste.
            return 1;
        }

        // return 0 si on ne peut pas ajouter la tondeuse.
        return 0;
    }

    /**
     * Méthode qui permet d'effectuer une instruction donnée
     * avec une Tondeuse dont le numéro est passé en paramètre.
     * @param instruction : Instruction à effectuer ('D' ou 'G' ou 'A').
     * @param numero : Numéro de la Tondeuse qui va faire l'instruction.
     * @return Un entier qui permettra de vérifier le bon déroulement de l'instruction.
     */
    public int instructionTondeuse(char instruction, int numero) {
        // -3 valeur par défaut de res.
        int res = -3;

        if (instruction == 'D') {
            res = this.tournerDroite(numero);
        } else if (instruction == 'G') {
            res = this.tournerGauche(numero);
        } else if (instruction == 'A') {
            res = this.avancer(numero);
        } else {
            // return -2 s'il y a un problème avec le nom de l'instruction.
            return -2;
        }

        // return res pour que nous puissions savoir dans le main si tout s'est bien fait,
        // sinon grâce à la valeur de res, nous saurons d'où vient l'erreur (fichierInfo.log).
        return res;
    }

    /**
     * Méthode qui permet de faire tourner à droite une Tondeuse (sans la faire avancer).
     * Cette méthode est private car elle n'est appelée que par la méthode instructionTondeuse(instruction, numero).
     * @param numero : Numéro de le Tondeuse à faire tourner à droite.
     * @return Un entier qui va permettre de avoir si l'orientation a bien eu lieu.
     */
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

            // return 0 s'il y a un problème avec l'orientation
            return 0;
        }

        this.tondeuses.get(numero).orientation = orientation;

        // return 1 si tout s'est bien effectué
        return 1;
    }

    /**
     * Méthode qui permet de faire tourner à gauche une Tondeuse (sans la faire avancer).
     * Cette méthode est private car elle n'est appelée que par la méthode instructionTondeuse(instruction, numero).
     * @param numero : Numéro de le Tondeuse à faire tourner à gauche.
     * @return Un entier qui va permettre de avoir si l'orientation a bien eu lieu.
     */
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
            // return 0 s'il y a un problème avec l'orientation
            return 0;
        }

        this.tondeuses.get(numero).orientation = orientation;

        // return 1 si tout s'est bien effectué
        return 1;
    }

    /**
     * Méthode qui permet de faire avancer une Tondeuse sur la Pelouse.
     * Elle regarde si la Tondeuse ne sort pas de la Pelouse en avançant (selon l'orientation).
     * Si la Tondeuse avance, son numéro à son ancien emplacement sera remplacé dans la grille par la lettre 'T'.
     * Puis elle regarde si une Tondeuse n'est pas déjà présente dans la parcelle où elle va avancer.
     * Cette méthode est private car elle n'est appelée que par la méthode instructionTondeuse(instruction, numero).
     * @param numero : Numéro de la Tondeuse que nous souhaitons faire avancer.
     * @return Un entier qui permet de s'assurer que tout s'est bien passé.
     */
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

        // return -1 si la Tondeuse ne peut pas avancer
        return -1;
    }

    /**
     * Getter qui permet de récupérer la liste de Tondeuse (utile pour l'écriture des résultats).
     * @return tondeuses : Liste de Tondeuse de la Pelouse.
     */
    public List<Tondeuse> getTondeuses() {
        return tondeuses;
    }
}
