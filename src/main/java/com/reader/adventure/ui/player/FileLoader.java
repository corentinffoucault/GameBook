package com.reader.adventure.ui.player;

import javax.swing.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileLoader {

    private static final Logger logger = LoggerFactory.getLogger(FileLoader.class);

    public static Reader loadFile(String filePath, String alternativeResource) {
            try {
                if (filePath.isEmpty()) {
                    InputStream in = FileLoader.class.getResourceAsStream(alternativeResource);
                    if (in == null) {
                        throw new RuntimeException(alternativeResource + " introuvable dans resources !");
                    }
                    return new InputStreamReader(in, StandardCharsets.UTF_8);
                }
                return new FileReader(filePath, java.nio.charset.StandardCharsets.UTF_8);
            } catch (Exception e) {
                logger.error("Erreur lors du chargement du fichier", e);
                throw new RuntimeException("Erreur lors du chargement du fichier", e);
            }
    }
}
