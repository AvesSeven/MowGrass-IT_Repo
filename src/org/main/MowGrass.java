package org.main;

import org.log.LogUtil;
import org.obj.Pelouse;
import org.obj.Tondeuse;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe MowGrass : permet de gérer l'utilisation de tondeuses automatiques sur une pelouse.
 * Elle implémente donc un objet Pelouse et des objets Tondeuse.
 *
 * @author Amandine Carlier
 * @version 1.0
 */

public class MowGrass extends IOException {
    /**
     * Constructeur de la classe MowGrass qui permet de lancer le programme.
     * @throws IOException : Gère les éventuelles Exception lors de la lecture ou de l'écriture d'un fichier.
     */
    public MowGrass() throws IOException {

        // Pour gérer les Logs.
        LogUtil.configLogger();

        int numLigneEntete = 0;
        int numLigneCorps;
        int cptTondeuses = 0;
        boolean loop = true;
        boolean newTondeuse = true;
        String ligne;
        String[] item = new String[0];
        List<String>elementFichier = new ArrayList<>();
        List<Integer>elementSupp = new ArrayList<>();
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
        // Récupérer les coordonnées du coin en haut à droite de la Pelouse.
        while((numLigneEntete < elementFichier.size()) && (loop))
        {
            item = elementFichier.get(numLigneEntete).split(" ");
            loop = false;

            numLigneEntete += 1;
        }

        Pelouse pelouse = new Pelouse(Integer.parseInt(item[0]), Integer.parseInt(item[1]));

        numLigneCorps = numLigneEntete;

        // Premier grand parcours de la liste pour ajouter toutes les tondeuses sur la pelouse.
        while (numLigneCorps < elementFichier.size()) {
            if (newTondeuse) {
                item = elementFichier.get(numLigneCorps).split(" ");
                tondeuse = new Tondeuse(Integer.parseInt(item[0]), Integer.parseInt(item[1]), Character.toUpperCase(item[2].charAt(0)));

                if(!pelouse.ajouterTondeuse(tondeuse)){
                    elementSupp.add(numLigneCorps);
                    elementSupp.add(numLigneCorps + 1);
                }
            }
            newTondeuse = !newTondeuse;

            numLigneCorps += 1;
        }

        // Pour supprimer les tondeuses, ainsi que leurs intructions si elles n'ont pas été ajoutées.
        for(int i = elementSupp.size() - 1; i >= 0; i--) {
            elementFichier.remove(Integer.parseInt(elementSupp.get(i).toString()));
        }

        newTondeuse = true;
        numLigneCorps = numLigneEntete;

        // Deuxième parcours de la liste pour faire les instructions des tondeuses de manière séquentielle.
        while (numLigneCorps < elementFichier.size()) {
            if (!newTondeuse) {
                for(int i = 0; i < elementFichier.get(numLigneCorps).length(); i++)
                {
                    pelouse.instructionTondeuse(elementFichier.get(numLigneCorps).charAt(i), cptTondeuses);
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
     * Méthode qui permet d'écrire les positions finales et l'orientation des tondeuses à la fin du programme.
     * @param tondeuses : Liste de Tondeuse, elle va permettre de récupérer les informations voulues.
     * @throws IOException : Gère les éventuelles Exception lors de la lecture ou de l'écriture d'un fichier.
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
     * @param args : Arguments lors de la compilation du programme.
     * @throws IOException : Gère les éventuelles Exception lors de la lecture ou de l'écriture d'un fichier.
     */
    public static void main(String[] args) throws IOException {
        new MowGrass();
    }
}
