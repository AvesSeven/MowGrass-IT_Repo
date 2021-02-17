package org.obj;

public class Tondeuse {
    // protected : y avoir accès facilement dans le package (donc dans Pelouse)
    protected int posX;
    protected int posY;
    protected char orientation;
    protected int id;
    private static int cpt;

    /*
    * Constructors
    * */
    public Tondeuse() {
        this.posX = 0;
        this.posY = 0;
        this.orientation = 'N';
        cpt++;
        this.id = cpt;
    }

    public Tondeuse(int posX, int posY, char orientation) {
        this.posX = posX;
        this.posY = posY;
        this.orientation = Character.toUpperCase(orientation);
        cpt++;
        this.id = cpt;
    }

    /*
    * Methods
    * */
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

    /*
    * Getters - Setters
    * Pour récupérer des informations dans le main
    * */
    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public char getOrientation() {
        return orientation;
    }

    public void setOrientation(char orientation) {
        this.orientation = orientation;
    }

    public int getId() {
        return id;
    }
}
