package model;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

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

    // Méthode pour sauvegarder les scores par difficulté
    public void sauvegarderScores(Map<Integer, Integer> scores) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(cheminFichier))) {
            for (Map.Entry<Integer, Integer> entry : scores.entrySet()) {
                writer.write(entry.getKey() + ": " + entry.getValue());
                writer.newLine();
            }
            System.out.println("Scores sauvegardés dans " + cheminFichier);
        } catch (IOException e) {
            System.err.println("Erreur lors de la sauvegarde des scores : " + e.getMessage());
        }
    }

    // Méthode pour charger les scores par difficulté
    public Map<Integer, Integer> chargerScores() {
        Map<Integer, Integer> scores = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(cheminFichier))) {
            String ligne;
            while ((ligne = reader.readLine()) != null) {
                String[] parts = ligne.split(": ");
                if (parts.length == 2) {
                    int difficulte = Integer.parseInt(parts[0].trim());
                    int score = Integer.parseInt(parts[1].trim());
                    scores.put(difficulte, score);
                }
            }
            System.out.println("Scores chargés depuis " + cheminFichier);
        } catch (IOException | NumberFormatException e) {
            System.err.println("Erreur lors du chargement des scores : " + e.getMessage());
        }
        return scores;
    }

    // Méthode pour sauvegarder un score spécifique à une difficulté
    public void sauvegarderScorePourDifficulte(int difficulte, int score) {
        Map<Integer, Integer> scores = chargerScores();
        scores.put(difficulte, score);
        sauvegarderScores(scores);
    }

    // Méthode pour charger un score spécifique à une difficulté
    public int chargerScorePourDifficulte(int difficulte) {
        Map<Integer, Integer> scores = chargerScores();
        return scores.getOrDefault(difficulte, 0); // Retourne 0 si aucun score n'est trouvé
    }
}