package org.obj;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Classe Pelouse : permet d'initialiser les attributs d'une pelouse, elle contient des objet de la classe Tondeuse.
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
    // Coordonnées (n, m) du coin supérieur droit de la pelouse.
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
     * Constructeur qui va permettre d'initialiser les attributs d'une Pelouse avec les coordonnées du coin supérieur droit de la Pelouse.
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
     * @return boolean : Si true, l'ajout a été effectué, sinon non.
     */
    public boolean ajouterTondeuse(Tondeuse tondeuse)
    {

        if(((tondeuse.posX <= n) && (tondeuse.posX >= 0)) && ((tondeuse.posY <= m) && (tondeuse.posY >= 0))
                && ((this.grille[tondeuse.posX][tondeuse.posY].charAt(0) == '.') || (this.grille[tondeuse.posX][tondeuse.posY].charAt(0) == 'T')))
        {
            this.tondeuses.add(tondeuse);
            this.grille[tondeuse.posX][tondeuse.posY] = String.valueOf(tondeuse.id);

            return true;
        }
        else {
            log.info("La tondeuse " + tondeuse.id + " n'a pas pu être ajoutée.");

            return false;
        }
    }

    /**
     * Méthode qui permet d'effectuer une instruction donnée
     * avec une Tondeuse dont le numéro est passé en paramètre.
     * @param instruction : Instruction à effectuer ('D' ou 'G' ou 'A').
     * @param indice : Indice de la Tondeuse qui va faire l'instruction.
     */
    public void instructionTondeuse(char instruction, int indice) {
        if (instruction == 'D') {
            this.tournerDroite(indice);
        } else if (instruction == 'G') {
            this.tournerGauche(indice);
        } else if (instruction == 'A') {
            this.avancer(indice);
        } else {
            log.info("Il y a eu un problème avec l'une des instructions de la Tondeuse " + this.tondeuses.get(indice).id);
        }
    }

    /**
     * Méthode qui permet de faire tourner à droite une Tondeuse (sans la faire avancer).
     * @param indice : Indice de le Tondeuse qui va subir une rotation à droite.
     */
    private void tournerDroite(int indice) {
        char orientation = this.tondeuses.get(indice).getOrientation();

        if (orientation == 'N') {
            orientation = 'E';
        } else if (orientation == 'E') {
            orientation = 'S';
        } else if (orientation == 'S') {
            orientation = 'O';
        } else if (orientation == 'O') {
            orientation = 'N';
        } else {
            log.info("Problème rencontré avec l'une des instructions de changement d'orientation à droite de la Tondeuse " + this.tondeuses.get(indice).id);
        }

        this.tondeuses.get(indice).orientation = orientation;
    }

    /**
     * Méthode qui permet de faire tourner à gauche une Tondeuse (sans la faire avancer).
     * @param indice : Indice de le Tondeuse qui va subir une rotation à gauche.
     */
    private void tournerGauche(int indice) {
        char orientation = this.tondeuses.get(indice).getOrientation();

        if (orientation == 'N') {
            orientation = 'O';
        } else if (orientation == 'O') {
            orientation = 'S';
        } else if (orientation == 'S') {
            orientation = 'E';
        } else if (orientation == 'E') {
            orientation = 'N';
        } else {
            log.info("Problème rencontré avec l'un des changements d'orientation à gauche de la Tondeuse " + this.tondeuses.get(indice).id);
        }

        this.tondeuses.get(indice).orientation = orientation;
    }

    /**
     * Méthode qui permet de faire avancer une Tondeuse sur la Pelouse.
     * Elle regarde si la Tondeuse ne sort pas de la Pelouse en avançant (selon l'orientation).
     * Puis elle regarde si une Tondeuse n'est pas déjà présente dans la parcelle où elle va avancer.
     * Si la Tondeuse avance, alors la lettre "T" sera placé à son ancien emplacement (nous saurons ainsi que la parcelle a été tondue).
     * @param indice : Indice de la Tondeuse que nous souhaitons faire avancer.
     */
    private void avancer(int indice) {
        int posX = this.tondeuses.get(indice).posX;
        int posY = this.tondeuses.get(indice).posY;
        char orientation = this.tondeuses.get(indice).orientation;

        if (((orientation == 'N') && (posY + 1 <= m) && ((this.grille[posX][posY+1].charAt(0) == '.') || (this.grille[posX][posY+1].charAt(0) == 'T')))
                || ((orientation == 'E') && (posX + 1 <= n) && ((this.grille[posX+1][posY].charAt(0) == '.') || (this.grille[posX+1][posY].charAt(0) == 'T')))
                || ((orientation == 'S') && (posY - 1 >= 0) && ((this.grille[posX][posY-1].charAt(0) == '.') || (this.grille[posX][posY-1].charAt(0) == 'T')))
                || ((orientation == 'O') && (posX - 1 >= 0) && ((this.grille[posX-1][posY].charAt(0) == '.') || (this.grille[posX-1][posY].charAt(0) == 'T')))){
            grille[this.tondeuses.get(indice).posX][this.tondeuses.get(indice).posY] = "T";
            this.tondeuses.get(indice).avancerTondeuse(orientation);
            this.grille[this.tondeuses.get(indice).posX][this.tondeuses.get(indice).posY] = String.valueOf(this.tondeuses.get(indice).id);
        }
        else {
            log.info("La Tondeuse " + this.tondeuses.get(indice).id + " n'a pas pu avancer.");
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
