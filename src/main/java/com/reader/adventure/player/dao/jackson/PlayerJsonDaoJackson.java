package com.reader.adventure.player.dao.jackson;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.reader.adventure.player.dao.IPlayerDao;
import com.reader.adventure.player.model.Player;
import com.reader.adventure.story.dao.Jackson.StoryJsonDaoJackson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

public class PlayerJsonDaoJackson implements IPlayerDao {
    private static final Logger logger = LogManager.getLogger(PlayerJsonDaoJackson.class);

    private AdventurerJackson adventurer;

    public PlayerJsonDaoJackson() throws Exception {
        this.loadPlayer();
    }

    public Player getPlayer() {
        return AdventurerMapper.INSTANCE.sourceToTarget(adventurer);
    }

    public void saveAdventurer(Player player) {
        this.adventurer = AdventurerMapper.INSTANCE.targetToSource(player);
    }

    private void loadPlayer() throws Exception {

        ObjectMapper mapper = new ObjectMapper();

        try (InputStream in = PlayerJsonDaoJackson.class.getResourceAsStream("/player.json")) {
            if (in == null) {
                throw new RuntimeException("player.json introuvable !");
            }
            adventurer = mapper.readValue(in, new TypeReference<AdventurerJackson>() {});
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
