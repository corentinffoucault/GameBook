package com.reader.adventure.player.dao;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.reader.adventure.player.model.Player;

import java.io.InputStream;

public class PlayerJsonDao implements IPlayerDao {

    private Player player;

    public PlayerJsonDao() throws Exception {
        this.loadPlayer();
    }

    public Player getPlayer() {
        return player;
    }

    private void loadPlayer() throws Exception {

        ObjectMapper mapper = new ObjectMapper();

        try (InputStream in = PlayerJsonDao.class.getResourceAsStream("/player.json")) {
            if (in == null) {
                throw new RuntimeException("player.json introuvable !");
            }
            player = mapper.readValue(in, new TypeReference<Player>() {});
        }
    }
}
