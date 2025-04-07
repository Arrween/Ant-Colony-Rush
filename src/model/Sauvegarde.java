package model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Sauvegarde {

    private String cheminFichier;

    public Sauvegarde(String cheminFichier) {
        this.cheminFichier = cheminFichier;
    }

    // Méthode pour sauvegarder une liste de chaînes de caractères dans un fichier
    public void sauvegarder(List<String> contenu) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(cheminFichier))) {
            for (String ligne : contenu) {
                writer.write(ligne);
                writer.newLine();
            }
            System.out.println("Données sauvegardées dans " + cheminFichier);
        } catch (IOException e) {
            System.err.println("Erreur lors de la sauvegarde : " + e.getMessage());
        }
    }

    // Méthode pour charger les données depuis un fichier
    public List<String> charger() {
        List<String> contenu = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(cheminFichier))) {
            String ligne;
            while ((ligne = reader.readLine()) != null) {
                contenu.add(ligne);
            }
            System.out.println("Données chargées depuis " + cheminFichier);
        } catch (IOException e) {
            System.err.println("Erreur lors du chargement : " + e.getMessage());
        }
        return contenu;
    }
}