package com.reader.adventure.adventurer.dao.jackson;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.reader.adventure.adventurer.dao.IAdventurerDao;
import com.reader.adventure.adventurer.model.Adventurer;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;

@Repository("adventurerJsonDaoJackson")
@Scope("singleton")
public class AdventurerJsonDaoJackson implements IAdventurerDao {

    private static final Logger logger = LoggerFactory.getLogger(AdventurerJsonDaoJackson.class);

    private AdventurerJackson adventurer;

    private final ObjectMapper mapper = new ObjectMapper();

    public AdventurerJsonDaoJackson() {
    }

    public Adventurer getAdventurer() {
        return AdventurerMapper.INSTANCE.sourceToTarget(adventurer);
    }

    public void saveAdventurer(Adventurer adventurer) {
        this.adventurer = AdventurerMapper.INSTANCE.targetToSource(adventurer);
    }

    public void loadAdventurer(Reader reader) {
        try {
            adventurer = mapper.readValue(reader, new TypeReference<AdventurerJackson>() {});
        } catch (Exception e) {
            logger.error("Erreur lors du chargement du fichier joueur", e);
            throw new RuntimeException("Erreur lors du chargement du fichier joueur", e);
        }
    }

    public void exportToJson(String path) {
        try(FileWriter fileWriter = new FileWriter(path, StandardCharsets.UTF_8)) {
            mapper.writeValue(fileWriter, adventurer);
        } catch (IOException e) {
            logger.error("Erreur lors de l'export de l'aventurier", e);
            throw new RuntimeException("Erreur lors de l'export de l'aventurier", e);
        }
    }
}
