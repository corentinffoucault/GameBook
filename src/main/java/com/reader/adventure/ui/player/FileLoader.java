package com.reader.adventure.ui.player;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.io.*;

public class FileLoader {

    private static final Logger logger = LogManager.getLogger(FileLoader.class);

    public static Reader loadFile() {
            String[] options = {"Fichier interne (resources)", "Fichier externe (disque)"};
            int choice = JOptionPane.showOptionDialog(null,
                    "Voulez-vous charger l'aventure depuis les resources ou un fichier externe ?",
                    "Choix du fichier",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]);
            try {
                if (choice == 0) {
                    InputStream in = FileLoader.class.getResourceAsStream("/nodes.json");
                    if (in == null) {
                        throw new RuntimeException("nodes.json introuvable dans resources !");
                    }
                    return new InputStreamReader(in, "UTF-8");
                }
                JFileChooser chooser = new JFileChooser(new File("."));
                chooser.setDialogTitle("Choisissez un fichier nodes.json");
                int result = chooser.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    return new FileReader(chooser.getSelectedFile(), java.nio.charset.StandardCharsets.UTF_8);
                }
            } catch (Exception e) {
                logger.error("Erreur lors du chargement du fichier", e);
                throw new RuntimeException("Erreur lors du chargement du fichier", e);
            }
        return null; // erreur
    }
}
