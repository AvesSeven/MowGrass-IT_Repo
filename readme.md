# Projet MowGrass
***
Développement d'un programme permettant la gestion de tondeuses automatiques sur une surface rectangulaire.

## Avant le lancement du programme
1. Créez un fichier "instructions.txt", celui-ci doit être formé de la façon suivant :
    * un premier chiffre qui correspond à la position en x du point en haut à droite de la pelouse;
    * un espace;
    * un deuxième chiffre qui correspond à la position en y du point en haut à droite de la pelouse;
    * un retour à la ligne;
    * la position en x de la tondeuse;
    * un espace;
    * la position en y de la tondeuse;
    * un espace;
    * l'orientation de la tondeuse;
    * un retour à la ligne;
    * une liste de caractères ('A' : avancer, 'D' : droite, 'G' : gauche) pour la liste des instructions que la tondeuse doit effectuer;
    * s'il y a d'autres tondeuses, il suffira de reprendre les huits dernières étapes.
 
2. Ensuite vous devrez aller dans le dossier src, puis dans org, et enfin dans ressources pour pouvoir y placer le fichier ci-dessus.  
    Un fichier initial contenant des instructions se trouve déjà dans le dossier ressources.  

3. Ouvrir le projet via l'IDE Intellij.

4. Ensuite, allez dans le dossier src, puis org, enfin main, puis ouvrez la classe MowGrass.

5. Une fois dans cette classe, vous trouverez la méthode main et n'aurez plus qu'à lancer le programme.

## Pendant le programme
Pour chaque tondeuse, un affichage console sera fait à la fin de leur liste d'instructions, ceci de manière séquentielle.

## Après le programme
* Un fichier "resultats.txt" sera créé, il comportera les positions finales des tondeuses, et se composera de la façon suivante :
    * un retour à la ligne;
    * un chiffre (position finale en x de la tondeuse);
    * un espace;
    * un autre chiffre (position finale en y de la tondeuse);
    * un autre espace;
    * une lettre (orientation finale de la tondeuse).

* Un fichier "Erreurs.log" sera créé dans le dossier log (qui se trouve lui-même dans le dossier org déjà rencontré auparavant) et il repartoriera les éventuels problèmes rencontrés.

***