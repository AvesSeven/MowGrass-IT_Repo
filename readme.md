# Projet MowGrass
***
Développement d'un programme permettant la gestion d'une tondeuse automatique sur une surface rectangulaire.

## Avant le lancement du programme
1. Créez un fichier "instructions.txt", celui-ci doit être formé de la façon suivant :
    * un premier chiffre pour la position en x en haut à droite de la pelouse;
    * un espace;
    * un deuxième chiffre pour la position en y en haut à droite de la pelouse;
    * un retour à la ligne;
    * la position en x de la première tondeuse;
    * un espace;
    * la position en y de la première tondeuse;
    * un espace;
    * l'orientation de la première tondeuse;
    * un retour à la ligne;
    * une liste de caractères (A : avancer, D : droite, G : gauche) pour la liste des instructions;
    * s'il y a d'autres tondeuses, il suffira de reprendre les huits dernières étapes.
 
2. Ensuite vous devez aller dans le dossier src, puis dans org, et enfin dans ressources pour pouvoir y placer le fichier ci-dessus.  
    Un fichier initial contenant des instructions se trouve déjà dans le dossier ressources.  

3. Ouvrir le projet via l'IDE Intellij.

4. Ensuite, allez dans le dossier src, puis org, enfin main, puis ouvrez la classe MowGrass.

5. Une fois dans cette classe, vous trouverez la méthode main et n'aurez plus qu'à lancer le programme.

## Pendant le programme
* Pour chaque tondeuse, un affichage console sera fait à la fin de leur liste d'instructions.

* En cas de problème, un message sera affiché dans la console et sera sauvegardé dans un fichier prévu à cet effet.

## Après le programme
* Un fichier "resultats.txt" sera créé, il comportera les positions finales des tondeuses, et se composera de la façon suivante :
    * un retour à la ligne;
    * un chiffre (position en x de la tondeuse);
    * un espace;
    * un autre chiffre (position en y de la tondeuse);
    * un autre espace;
    * une lettre (orienatation de la tondeuse).

* Un fichier "fichierIngo.log" sera créé dans le dossier ressources et il repartoriera les éventuels problèmes rencontrés. 