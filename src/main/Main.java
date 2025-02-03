package main;

import javax.swing.*;

import model.Position;
import view.Affichage;
import control.ReactionClic;
import control.Redessine;
import control.Descendre;

public class Main {
    public static void main(String[] args) {

        // Création de la fenêtre
        JFrame maFenetre = new JFrame("Exercice 1");
        maFenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Création d'une instance de Position
        Position maPosition = new Position();

        // Création de l'affichage (on lui passe la position)
        Affichage affichage = new Affichage(maPosition);

        // Création du ReactionClic (on lui passe la même position)
        ReactionClic reaction = new ReactionClic(maPosition);

        // On enregistre le MouseListener sur le composant affichage
        affichage.addMouseListener(reaction);

        // Ajout du composant affichage à la fenêtre
        maFenetre.add(affichage);

        // Ajustement de la taille de la fenêtre
        maFenetre.pack();

        // Rendre la fenêtre visible
        maFenetre.setVisible(true);

        // Création et lancement du thread de redessin
        Redessine redessine = new Redessine(affichage);
        redessine.start();

        // Lancement du thread descendre
        Descendre descendre = new Descendre(maPosition);
        descendre.start();
    }
}
