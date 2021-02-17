package org.main;

import org.obj.Pelouse;
import org.obj.Tondeuse;

import java.io.*;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

public class MowGrass extends IOException{

    public Logger log = Logger.getLogger("org.main.MowGrass");

    public MowGrass() throws IOException {

        // Partie fichier pour le log
        FileHandler fh = null;
        try{
            fh = new FileHandler("src/org/ressources/fichierInfo.log");
        } catch(SecurityException | IOException e){
            e.printStackTrace();
        }
        if(fh !=null){
            log.addHandler(fh);
        }
        else{
            log.warning("Problème avec le fichier...");
        }

        // Traitement du fichier en entrée
        int x = 0;
        int y = 0;
        String ligne;
        String[] item;
        BufferedReader in = null;

        try {
            File fichier = new File("src/org/ressources/instructions.txt");
            in = new BufferedReader(new FileReader(fichier));
        } catch (FileNotFoundException e) {
            System.out.println("Erreur d'ouverture");
        }

        /*
         * Lecture de la 1iere ligne <=> coordonnées haut à droite de la pelouse
         * */
        while ((ligne = in.readLine()) != null) {
            if (ligne.length() > 0) {
                item = ligne.split(" ");
                x = Integer.parseInt(item[0]);
                y = Integer.parseInt(item[1]);
                break;
            }
        }
        Pelouse pelouse = new Pelouse(x, y);

        /*
         * Boucle principale
         * */
        // Pour savoir si on lit une ligne pour les coordonnées de la tondeuse, ou si c'est les instructions :
        int cptTondeuses = -1;
        // Dans le cas où il y a un problème avec la méthode avancer
        int res = -3;
        boolean ligneInstructions = false;
        Tondeuse tondeuse = null;

        while ((ligne = in.readLine()) != null) {
            if (ligne.length() > 0) {
                // On verifie quel type de ligne a été lue
                if (!ligneInstructions) { //On créée une tondeuse
                    item = ligne.split(" ");
                    tondeuse = new Tondeuse(Integer.parseInt(item[0]), Integer.parseInt(item[1]), Character.toUpperCase(item[2].charAt(0)));
                    cptTondeuses++;

                    if (pelouse.ajouterTondeuse(tondeuse) == 0) {
                        log.warning("La tondeuse numéro " + tondeuse.getId() + " n'a pas pu être ajouté sur la pelouse !");
                    }
                } else {
                    // On éxecute les instructions
                    for (int i = 0; i < ligne.length(); i++) {
                        res = pelouse.instructionTondeuse(ligne.charAt(i), cptTondeuses);

                        if (res != 1) {
                            this.appelLogger(res, tondeuse);
                        }
                    }
                    // Une fois que la tondeuse à finie on affiche la pelouse, et on passe à la suivante
                    pelouse.afficherPelouse();
                }
                ligneInstructions = !ligneInstructions;
            }
        }

        in.close();

        ecrireResultat(pelouse.getTondeuses());
    }

    public void appelLogger(int res, Tondeuse tondeuse) {
        switch (res) {
            case -2:
                log.warning("problème avec le nom d'une instruction de la tondeuse " + tondeuse.getId());
                break;

            case -1:
                log.warning("La tondeuse " + tondeuse.getId() + " ne peut pas avancer");
                break;

            case 0:
                log.warning("Problème avec l'orientation de la tondeuse " + tondeuse.getId());
                break;

            default:
                log.warning("Problème lors de l'appel d'une méthode");
        }
    }

    public void ecrireResultat(List<Tondeuse> tondeuses) throws IOException {

        FileWriter fileWriter = new FileWriter("src/org/ressources/resultats.txt");
        PrintWriter printWriter = new PrintWriter(fileWriter);

        for (Tondeuse tond : tondeuses) {
            printWriter.println("\n" + tond.getPosX() + " " + tond.getPosY() + " " + tond.getOrientation());
        }

        printWriter.close();
    }
    public static void main(String[] args) throws IOException {
        new MowGrass();
    }
}
