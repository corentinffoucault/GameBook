package com.reader.adventure.adventurer.dao.jackson;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.reader.adventure.adventurer.dao.IAdventurerDao;
import com.reader.adventure.adventurer.model.Adventurer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;

public class AdventurerJsonDaoJackson implements IAdventurerDao {
    private static final Logger logger = LogManager.getLogger(AdventurerJsonDaoJackson.class);

    private AdventurerJackson adventurer;

    public AdventurerJsonDaoJackson(Reader reader) throws Exception {
        this.loadAdventurer(reader);
    }

    public Adventurer getAdventurer() {
        return AdventurerMapper.INSTANCE.sourceToTarget(adventurer);
    }

    public void saveAdventurer(Adventurer adventurer) {
        this.adventurer = AdventurerMapper.INSTANCE.targetToSource(adventurer);
    }

    private void loadAdventurer(Reader reader) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            adventurer = mapper.readValue(reader, new TypeReference<AdventurerJackson>() {});
        } catch (Exception e) {
            logger.error("Erreur lors du chargement du fichier joueur", e);
            throw new RuntimeException("Erreur lors du chargement du fichier joueur", e);
        }
    }

    public void exportToJson(String path) {
        try {
            FileWriter fileWriter = new FileWriter(path, java.nio.charset.StandardCharsets.UTF_8);
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(fileWriter, adventurer);
        } catch (IOException e) {
            logger.error("Erreur lors de l'export de l'aventurier", e);
            throw new RuntimeException("Erreur lors de l'export de l'aventurier", e);
        }
    }
}
