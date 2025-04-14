package model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Sauvegarde {

    private String cheminFichier;

    public Sauvegarde(String cheminFichier) {
        this.cheminFichier = cheminFichier;
        File fichier = new File(cheminFichier);
        try {
            if (!fichier.exists()) {
                fichier.createNewFile(); // Créer le fichier s'il n'existe pas
            }
        } catch (IOException e) {
            System.err.println("Erreur lors de la création du fichier : " + e.getMessage());
        }
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

    // Méthode pour sauvegarder le meilleur score
    public void sauvegarderMeilleurScore(int score) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(cheminFichier))) {
            writer.write(String.valueOf(score)); // Écrire uniquement le score
            System.out.println("Meilleur score sauvegardé : " + score);
        } catch (IOException e) {
            System.err.println("Erreur lors de la sauvegarde du meilleur score : " + e.getMessage());
        }
    }

    // Méthode pour charger le meilleur score
    public int chargerMeilleurScore() {
        try (BufferedReader reader = new BufferedReader(new FileReader(cheminFichier))) {
            String ligne = reader.readLine();
            if (ligne != null && !ligne.isEmpty()) {
                return Integer.parseInt(ligne.trim()); // Convertir la ligne en entier
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Erreur lors du chargement du meilleur score : " + e.getMessage());
        }
        return 0; // Retourne 0 si aucun score valide n'est trouvé
    }

}