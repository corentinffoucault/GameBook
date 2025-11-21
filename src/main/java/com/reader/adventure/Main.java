package com.reader.adventure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;

import javax.swing.*;

@SpringBootApplication
public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        System.setProperty("java.awt.headless", "false"); // Force Swing
        SpringApplication.run(Main.class, args);
    }

    public static void closeGame() {
        int result = JOptionPane.showConfirmDialog(
                null,
                "Voulez-vous vraiment quitter l'application ?",
                "Confirmation de fermeture",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
        );

        if (result == JOptionPane.YES_OPTION) {
            System.out.println("Fermeture de l'application...");
            System.exit(0);
        } else {
            System.out.println("Fermeture annulée par l'utilisateur.");
        }
    }
}