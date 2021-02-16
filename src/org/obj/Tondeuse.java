package org.obj;

public class Tondeuse {
    // protected : y avoir accès facilement dans le package
    protected int posX;
    protected int posY;
    protected char orientation;
    protected String nom;
    private static int cpt;

    /*
    * Constructors
    * */
    public Tondeuse() {
        this.posX = 0;
        this.posY = 0;
        this.orientation = 'N';
        cpt++;
        this.nom = "Tond_" + cpt;
    }

    public Tondeuse(int posX, int posY, char orientation) {
        this.posX = posX;
        this.posY = posY;
        this.orientation = orientation;
        cpt++;
        this.nom = "Tond_" + cpt;
    }

    /*
    * Methods
    * */


    /*
    * Getters - Setters
    * Pour récupérer des informations dans le main
    * */
    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public char getOrientation() {
        return orientation;
    }

    public void setOrientation(char orientation) {
        this.orientation = orientation;
    }

    public String getNom() {
        return nom;
    }
}
