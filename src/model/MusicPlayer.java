package model;

import java.io.File;
import java.util.HashMap;
import java.util.Random;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class MusicPlayer {
    private static final HashMap<String, Clip> soundClips = new HashMap<>();
    private static final Random rand = new Random();

    /**
     * Initialise les sons utilisés dans le jeu.
     */

    public static void initSound() {
        // à ajouter : son crapaud, son pluie
        String path = "resources/Son/son_nuit.wav";
        // Vérifier que le fichier de son existe
        File soundPath = new File(path);
        if (!soundPath.exists()) {
            System.out.println("Le fichier de son n'existe pas : " + soundPath.getAbsolutePath());
        } else {
            loadSound("son_nuit", "resources/Son/son_nuit.wav");
        }

        path = "resources/Son/son_jour.wav";
        soundPath = new File(path);
        if (!soundPath.exists()) {
            System.out.println("Le fichier de son n'existe pas : " + soundPath.getAbsolutePath());
        } else {
            loadSound("son_jour", path);
        }

        path = "resources/Son/music.wav";
        soundPath = new File(path);
        if (!soundPath.exists()) {
            System.out.println("Le fichier de son n'existe pas : " + soundPath.getAbsolutePath());
        } else {
            loadSound("music", path);
        }

        path = "resources/Son/music_menu.wav";
        soundPath = new File(path);
        if (!soundPath.exists()) {
            System.out.println("Le fichier de son n'existe pas : " + soundPath.getAbsolutePath());
        } else {
            loadSound("music_menu", path);
        }
    } 

    private static void loadSound(String key, String path) {
        try {
            File soundPath = new File(path);
            System.out.println("Tentative de chargement du son depuis : " + soundPath.getAbsolutePath());
            if (soundPath.exists()) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(soundPath);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInput);
                soundClips.put(key, clip);
                System.out.println("Son chargé pour la clé: " + key);
            } else {
                System.out.println("Fichier non trouvé pour le chemin: " + path);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void playSound(String key) {
        Clip clip = soundClips.get(key);
        if (clip != null) {
            System.out.println("Lecture du son: " + key);
            clip.setFramePosition(0);
            clip.start();
        } else {
            System.out.println("Clip non trouvé pour la clé: " + key);
        }
    }

    // Méthodes pour le mode nuit et le mode jour
    public static void playNightSound() {
        playSound("son_nuit");
    }
    
    public static void playDaySound() {
        playSound("son_jour");
    }

     /**
     * Méthode qui arrête le son actuellement en lecture.
     * Vous pouvez l'appeler avant de lancer un nouveau son.
     */
    public static void stop(String key) {
            Clip clipToStop = soundClips.get(key);
            if (clipToStop != null && clipToStop.isRunning()) {
                System.out.println("Arrêt du son: " + key);
                clipToStop.stop();
                clipToStop.setFramePosition(0);
            } else {
                System.out.println("Aucun son en cours de lecture pour la clé: " + key);
            }
    }

    public static void stopNightSound() {
        stop("son_nuit");
    }

    public static void stopDaySound() {
        stop("son_jour");
    }

    public static void playMusic() {
        playSound("music");

    }

    public static void playMusicMenu() {
        playSound("music_menu");
    }

    public static void stopMusicMenu() {
        stop("music_menu");
    }
    
    public static void stopAllSounds() {
        for (String key : soundClips.keySet()) {
            stop(key);
        }
    }


    // TEST
    public static void main(String[] args) {
        initSound();
        playNightSound();
        //playDaySound();
    }
}
