package org.obj;

import java.util.logging.Logger;

/**
 * Classe Tondeuse : permet d'initialiser les attributs d'une tondeuse
 * et de la faire avancer selon une orientation.
 *
 * @author Amandine Carlier
 * @version 1.0
 */

public class Tondeuse {
    // Attributs protected :
    // Cela va permettre à classe Pelouse d'avoir accès rapidement aux caractéristiques de ses Tondeuse.
    protected int posX;
    protected int posY;
    protected char orientation;
    protected int id;
    private static int cpt;

    // Pour la gestion des Logs de la Classe Tondeuse.
    Logger log = Logger.getLogger("org.obj.Tondeuse");

    /**
     * Constructeur vide qui va permettre d'initialiser un objet Tondeuse
     * avec des valeurs par défaut.
     */
    public Tondeuse() {
        this.posX = 0;
        this.posY = 0;
        this.orientation = 'N';
        cpt += 1;
        this.id = cpt;
    }

    /**
     * Constructeur qui va permettre d'initialiser les attributs d'une Tondeuse avec une position et une orientation données.
     * @param posX : Position initiale en x de la Tondeuse.
     * @param posY : Position initiale en y de la Tondeuse.
     * @param orientation : Orientation initiale de la Tondeuse.
     */
    public Tondeuse(int posX, int posY, char orientation) {
        this.posX = posX;
        this.posY = posY;
        this.orientation = Character.toUpperCase(orientation);
        cpt += 1;
        this.id = cpt;
    }

    /**
     * Méthode qui va permettre de faire avancer la Tondeuse dans une direction selon son orientation donnée.
     * @param orientation : Orientation qui va permettre de faire avancer la Tondeuse dans la bonne direction.
     */
    protected void avancerTondeuse(char orientation){
        if (orientation == 'N') {
            this.posY += 1;
        } else if (orientation == 'E') {
            this.posX += 1;
        } else if (orientation == 'S') {
            this.posY -= 1;
        } else if (orientation == 'O') {
            this.posX -= 1;
        }
    }

    /**
     * Méthode qui permet de faire tourner à droite une Tondeuse (sans la faire avancer).
     */
    protected void tournerDroite() {
        char orientation = this.orientation;

        if (orientation == 'N') {
            orientation = 'E';
        } else if (orientation == 'E') {
            orientation = 'S';
        } else if (orientation == 'S') {
            orientation = 'O';
        } else if (orientation == 'O') {
            orientation = 'N';
        } else {
            log.info("Problème rencontré avec l'une des instructions de changement d'orientation à droite de la Tondeuse " + this.id);
        }

        this.orientation = orientation;
    }

    /**
     * Méthode qui permet de faire tourner à gauche une Tondeuse (sans la faire avancer).
     */
    protected void tournerGauche() {
        char orientation = this.orientation;

        if (orientation == 'N') {
            orientation = 'O';
        } else if (orientation == 'O') {
            orientation = 'S';
        } else if (orientation == 'S') {
            orientation = 'E';
        } else if (orientation == 'E') {
            orientation = 'N';
        } else {
            log.info("Problème rencontré avec l'un des changements d'orientation à gauche de la Tondeuse " + this.id);
        }

        this.orientation = orientation;
    }

    /**
     * Getteur qui va permettre de récupérer la position finale en x de la Tondeuse (pour le main).
     * @return posX : Position finale en x de la Tondeuse.
     */
    public int getPosX() {
        return posX;
    }

    /**
     * Getteur qui va permettre de récupérer la position finale en y de la Tondeuse (pour le main).
     * @return posY : Position finale en y de la Tondeuse.
     */
    public int getPosY() {
        return posY;
    }

    /**
     * Getteur qui va permettre de récupérer l'orientation finale de la Tondeuse (pour le main).
     * @return orientation : Orientation finale de la Tondeuse.
     */
    public char getOrientation() {
        return orientation;
    }
}
