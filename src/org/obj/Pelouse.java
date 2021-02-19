package org.obj;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Classe Pelouse : permet d'initialiser les attributs d'une pelouse, elle contient des objet de classe Tondeuse.
 * Cette classe permet de gérer une partie des fonctionnalitées d'une pelouse.
 *
 * @author Amandine Carlier
 * @version 1.0
 */

public class Pelouse {
    // Grille qui va contenir soit :
    // ".", "T", le numéro de la Tondeuse (son id).
    private String[][] grille;
    private List<Tondeuse> tondeuses;
    // Coordonnées n,m de la case en haut à droite de la grille
    private int n;
    private int m;

    // Pour la gestion des Logs de la Classe Pelouse.
    Logger log = Logger.getLogger("org.obj.Pelouse");

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
     * Constructeur qui va permettre d'initialiser les attributs d'une Pelouse avec les coordonnées du point en haut à droite données.
     * @param n : Position en x de la case en haut à droite de la Pelouse.
     * @param m : Position en y de la case en haut à droite de la Pelouse.
     */
    public Pelouse(int n, int m) {
        this.n = n;
        this.m = m;

        this.grille = new String[this.n + 1][this.m + 1];
        this.tondeuses = new ArrayList<>();

        // Initialisation de la Pelouse avec des ".".
        // Un "." correspondra à une parcelle de Pelouse non tondue.
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
     */
    public void ajouterTondeuse(Tondeuse tondeuse)
    {

        if(this.grille[tondeuse.posX][tondeuse.posY].charAt(0) == '.')
        {
            this.tondeuses.add(tondeuse);
            this.grille[tondeuse.posX][tondeuse.posY] = String.valueOf(tondeuse.id);
        }
        else {
            log.info("La tondeuse " + (tondeuse.id + 1) + " n'a pas pu être ajoutée.");
        }
    }

    /**
     * Méthode qui permet d'effectuer une instruction donnée
     * avec une Tondeuse dont le numéro est passé en paramètre.
     * @param instruction : Instruction à effectuer ('D' ou 'G' ou 'A').
     * @param numero : Numéro de la Tondeuse qui va faire l'instruction.
     */
    public void instructionTondeuse(char instruction, int numero) {

        if (instruction == 'D') {
            this.tournerDroite(numero);
        } else if (instruction == 'G') {
            this.tournerGauche(numero);
        } else if (instruction == 'A') {
            this.avancer(numero);
        } else {
            log.info("Il y a eu un problème avec l'une des instructions de la Tondeuse " + (numero + 1));
        }
    }

    /**
     * Méthode qui permet de faire tourner à droite une Tondeuse (sans la faire avancer).
     * Cette méthode est private car elle n'est appelée que par la méthode instructionTondeuse(instruction, numero).
     * @param numero : Numéro de le Tondeuse qui va subir une rotation à droite.
     */
    private void tournerDroite(int numero) {
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
            log.info("Problème rencontré avec l'une des instructions de changement d'orientation à droite de la Tondeuse " + (numero + 1));
        }

        this.tondeuses.get(numero).orientation = orientation;
    }

    /**
     * Méthode qui permet de faire tourner à gauche une Tondeuse (sans la faire avancer).
     * Cette méthode est private car elle n'est appelée que par la méthode instructionTondeuse(instruction, numero).
     * @param numero : Numéro de le Tondeuse qui va subir une rotation à gauche.
     */
    private void tournerGauche(int numero) {
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
            log.info("Problème rencontré avec l'un des changements d'orientation à gauche de la Tondeuse " + (numero + 1));
        }

        this.tondeuses.get(numero).orientation = orientation;
    }

    /**
     * Méthode qui permet de faire avancer une Tondeuse sur la Pelouse.
     * Elle regarde si la Tondeuse ne sort pas de la Pelouse en avançant (selon l'orientation).
     * Puis elle regarde si une Tondeuse n'est pas déjà présente dans la parcelle où elle va avancer.
     * Si la Tondeuse avance, alors la lettre "T" sera placé à son ancien emplacement (nous saurons ainsi que cette parcelle a été tondue).
     * Cette méthode est private car elle n'est appelée que par la méthode instructionTondeuse(instruction, numero).
     * @param numero : Numéro de la Tondeuse que nous souhaitons faire avancer.
     */
    private void avancer(int numero) {
        int posX = this.tondeuses.get(numero).posX;
        int posY = this.tondeuses.get(numero).posY;
        char orientation = this.tondeuses.get(numero).orientation;

        if (((orientation == 'N') && (posY + 1 <= m) && ((this.grille[posX][posY+1].charAt(0) == '.') || (this.grille[posX][posY+1].charAt(0) == 'T')))
                || ((orientation == 'E') && (posX + 1 <= n) && ((this.grille[posX+1][posY].charAt(0) == '.') || (this.grille[posX+1][posY].charAt(0) == 'T')))
                || ((orientation == 'S') && (posY - 1 >= 0) && ((this.grille[posX][posY-1].charAt(0) == '.') || (this.grille[posX][posY-1].charAt(0) == 'T')))
                || ((orientation == 'O') && (posX - 1 >= 0) && ((this.grille[posX-1][posY].charAt(0) == '.') || (this.grille[posX-1][posY].charAt(0) == 'T')))){
            grille[this.tondeuses.get(numero).posX][this.tondeuses.get(numero).posY] = "T";
            this.tondeuses.get(numero).avancerTondeuse(orientation);
            this.grille[this.tondeuses.get(numero).posX][this.tondeuses.get(numero).posY] = String.valueOf(this.tondeuses.get(numero).id);
        }
        else {
            log.info("La Tondeuse " + (numero + 1) + " n'a pas pu avancer.");
        }
    }

    /**
     * Getter qui permet de récupérer la liste de Tondeuse
     * (utile pour l'écriture des résultats dans un nouveau fichier).
     * @return tondeuses : Liste de Tondeuse de la Pelouse.
     */
    public List<Tondeuse> getTondeuses() {
        return tondeuses;
    }
}
