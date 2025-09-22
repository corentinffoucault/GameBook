package com.reader.adventure.player.dao;

import com.reader.adventure.player.model.Player;

public interface IPlayerDao {
    public Player getPlayer();

    void saveAdventurer(Player player);

    void exportToJson(String path);
}
