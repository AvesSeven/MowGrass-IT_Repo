package org.main;

import org.obj.Pelouse;
import org.obj.Tondeuse;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

/**
 * Classe MowGrass : permet de gérer l'utilisation de tondeuses automatique sur une pelouse.
 * Elle implémente donc un objet Pelouse et des objets Tondeuse.
 *
 * @author Amandine Carlier
 * @version 1.0
 */

public class MowGrass extends IOException{
    // Loffer pour récupérer les éventuels problèmes liés aux mouvements de la tondeuse.
    public Logger log = Logger.getLogger("org.main.MowGrass");

    /**
     * Constructeur de la classe MowGrass qui permet de lancer le programme.
     */
    public MowGrass() throws IOException {

        // Gestion du fichier pour le log :
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
        // Fin de la gestion du fichier pour le log.

        int numLigneEntete = 0;
        int numLigneCorps;
        int cptTondeuses = 0;
        int res;
        boolean loop = true;
        boolean newTondeuse = true;
        String ligne;
        String[] item = null;
        List<String>elementFichier = new ArrayList<>();
        Tondeuse tondeuse;
        BufferedReader in = null;
        File fichier;

        // Gestion du fichier en entrée :
        try {
            fichier = new File("src/org/ressources/instructions.txt");
            in = new BufferedReader(new FileReader(fichier));
        } catch (FileNotFoundException e) {
            System.out.println("Erreur d'ouverture");
        }

        while((ligne = in.readLine()) != null)
        {
            if(ligne.length() > 0) {
                elementFichier.add(ligne);
            }
        }

        in.close();
        // Fin de la gestion du fichier d'entrée.

        // Gestion de la liste :
        // Récupérer les coordonnées du cin en haut à droite pour la Pelouse.
        while((numLigneEntete < elementFichier.size()) && (loop))
        {
            item = elementFichier.get(numLigneEntete).split(" ");
            loop = false;

            numLigneEntete += 1;
        }

        Pelouse pelouse = new Pelouse(Integer.parseInt(item[0]), Integer.parseInt(item[1]));

        numLigneCorps = numLigneEntete;

        // Premier grand parcours de la liste pour ajouter toutes les tondeuses.
        while (numLigneCorps < elementFichier.size()) {
            if (newTondeuse) {
                item = elementFichier.get(numLigneCorps).split(" ");
                tondeuse = new Tondeuse(Integer.parseInt(item[0]), Integer.parseInt(item[1]), Character.toUpperCase(item[2].charAt(0)));

                if (pelouse.ajouterTondeuse(tondeuse) == 0) {
                    log.info("La tondeuse numéro " + tondeuse.getId() + " n'a pas pu être ajouté sur la pelouse !");
                }
            }
            newTondeuse = !newTondeuse;

            numLigneCorps += 1;
        }

        newTondeuse = true;
        numLigneCorps = numLigneEntete;

        // Deuxième parcours de la liste pour faire les instructions des tondeuses de manière séquentielle.
        while (numLigneCorps < elementFichier.size()) {
            if (!newTondeuse) {
                for(int i = 0; i < elementFichier.get(numLigneCorps).length(); i++)
                {
                    res = pelouse.instructionTondeuse(elementFichier.get(numLigneCorps).charAt(i), cptTondeuses);

                    if(res != 1)
                    {
                        this.appelLogger(res, pelouse.getTondeuses().get(cptTondeuses));
                    }
                }
                cptTondeuses += 1;
                pelouse.afficherPelouse();
            }
            newTondeuse = !newTondeuse;

            numLigneCorps += 1;
        }
        // Fin de la gestion de la liste.

        // Ecriture du fichier de résultats.
        this.ecrireResultat(pelouse.getTondeuses());
    }

    /**
     * Méthode qui permet d'écrire dans fichierInfo.log en cas de problème lors de l'exécution d'une autre méthode.
     * @param res : En fonction de la valeur de res, nous saurons le problème rencontré.
     * @param tondeuse : Tondeuse qui a rencontré le problème.
     */
    public void appelLogger(int res, Tondeuse tondeuse) {
        switch (res) {
            case -2:
                log.info("problème avec le nom d'une instruction de la tondeuse " + tondeuse.getId());
                break;

            case -1:
                log.info("La tondeuse " + tondeuse.getId() + " ne peut pas avancer");
                break;

            case 0:
                log.info("Problème avec l'orientation de la tondeuse " + tondeuse.getId());
                break;

            default:
                log.info("Problème lors de l'appel d'une méthode");
        }
    }

    /**
     * Méthode qui permet d'écrire les positions finales et l'orientation des tondeuses à la fin du programme.
     * @param tondeuses : Liste de Tondeuse, elle va permettre de récupérer les informations voulues.
     * @throws  : Gère l'éventuelle Exception.
     */
    public void ecrireResultat(List<Tondeuse> tondeuses) throws IOException {

        FileWriter fileWriter = new FileWriter("src/org/ressources/resultats.txt");
        PrintWriter printWriter = new PrintWriter(fileWriter);

        for (Tondeuse tond : tondeuses) {
            printWriter.println("\n" + tond.getPosX() + " " + tond.getPosY() + " " + tond.getOrientation());
        }

        printWriter.close();
    }

    /**
     * main qui permet de lancer le programme principal.
     * @param args arguments lors de la compilation du programme.
     * @throws IOException : Gère l'éventuelle Exception.
     */
    public static void main(String[] args) throws IOException {
        new MowGrass();
    }
}
