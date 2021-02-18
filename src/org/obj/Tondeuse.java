package org.obj;

/**
 * Classe Tondeuse : permet d'initialiser les attributs d'une tondeuse
 * et de gérer une partie des fonctionnalitées de celle-ci.
 *
 * @author Amandine Carlier
 * @version 1.0
 */

public class Tondeuse {
    // Attributs protected :
    // Cela va me permettre d'y avoir accès facilement dans le package (donc dans Pelouse).
    protected int posX;
    protected int posY;
    protected char orientation;
    protected int id;
    private static int cpt;

    /**
     * Constructeur vide qui va permettre d'initialiser un objet Tondeuse
     * avec des valeurs par défaut.
     */
    public Tondeuse() {
        this.posX = 0;
        this.posY = 0;
        this.orientation = 'N';
        cpt++;
        this.id = cpt;
    }

    /**
     * Constructeur qui va permettre d'initialiser les attributs d'une Tondeuse.
     * @param posX : Position initiale en x de la Tondeuse.
     * @param posY : Position initiale en y de la Tondeuse.
     * @param orientation : orientation initiale de la Tondeuse.
     */
    public Tondeuse(int posX, int posY, char orientation) {
        this.posX = posX;
        this.posY = posY;
        this.orientation = Character.toUpperCase(orientation);
        cpt++;
        this.id = cpt;
    }

    /**
     * Méthode qui va permettre de faire avancer la Tondeuse dans une direction selon son orientation donnée.
     * Méthode protected car je souhaite que son accès soit limité à son package.
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
     * Getteur qui va permettre de récupérer la position finale en x de la Tondeuse (main).
     * @return posX : Position finale en x de la Tondeuse.
     */
    public int getPosX() {
        return posX;
    }

    /**
     * Getteur qui va permettre de récupérer la position finale en y de la Tondeuse (main).
     * @return posY : Position finale en y de la Tondeuse.
     */
    public int getPosY() {
        return posY;
    }

    /**
     * Getteur qui va permettre de récupérer l'orientation finale de la Tondeuse (main).
     * @return orientation : Orientation finale de la Tondeuse.
     */
    public char getOrientation() {
        return orientation;
    }

    /**
     * Getteur qui permet de récupérer l'identifiant de la Tondeuse en cas de problème (log du main).
     * @return id : Identifiant unique de la Tondeuse.
     */
    public int getId() {
        return id;
    }
}
