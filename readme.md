# Le Pong, Mais Pas Pareil

## Présentation

Ce jeu de raquettes, programmé en Java 17 avec JavaFX, est configuré avec Gradle. Ce jeu est basé sur le jeu [Pong](https://fr.wikipedia.org/wiki/Pong), un classique des salles d'arcades dans les années 1970.

Le principe est simple : un terrain, deux raquettes et une balle. Le jeu se joue à deux (ou moins), chaque joueur pouvant déplacer sa raquette sur un axe haut/bas et ayant pour but de ne pas laisser passer la balle derrière sa raquette (ce qui correspond à un échec). La balle se déplace à vitesse constante entre ses rebonds et rebondit sur les parois (limites haute et basse de la fenêtre) et sur les raquettes.

Ici, beaucoup de modifications ont été faite, pour créer une expérience très différente tout en gardant le principe fondamental.
Presques toutes sont activables ou désactivables dans les paramètres, accessibles au démarrage du jeu.
Des exemples de ces modifications sont: des briques, que la balle casse en rebondissant dessus, ou bien du vent, qui affecte la trajectoire de la balle.

## Jouer

Le jeu requiert java 17 pour être compilé et exécuté.

La raquette de gauche est contrôlée par les touches A et Q, alors que celle de droite est contrôlée par ↑ et ↓.
Pour mettre le jeu en pause (ou le remettre en action quand il est en pause), appuyez sur P.

Les paramètres sont configurables au lancement de l'application, et ne sont pas sauvegardés entre des exécutions successives.

Pour que le jeu compte les points, il faut activer une des options `Scoreboard`.

Si l'option `Bonus-Malus` est activée, d'autres options apparaitront (comprenant ` (Bonus-malus)` après leur nom).
Si aucune de ces options n'est activée, bonus malus ne servira à rien.

1. Télécharger le projet en le clonant via git: `git clone https://gaufre.informatique.univ-paris-diderot.fr/mi-zer/pong.git`
(si sur les machines de la halle aux farines, il y a un problème de certificat. Il faut alors dire à git de ne pas le vérifier, en utilisant l'option `-c http.sslVerify=false`).
2. Exécuter avec `./gradlew run`, dans le répertoire du projet
(si sur les machines de la halle aux farines, il y a un problème d'environnement. Il faut donc effectuer `source SCRIPT/envsetup` avant d'exécuter gradlew).
3. Jouer !

[Membres du groupe.](https://gaufre.informatique.univ-paris-diderot.fr/mi-zer/pong/blob/master/membres.md)
