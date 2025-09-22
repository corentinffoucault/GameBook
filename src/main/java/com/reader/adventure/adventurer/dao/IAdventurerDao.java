package com.reader.adventure.adventurer.dao;

import com.reader.adventure.adventurer.model.Adventurer;

public interface IAdventurerDao {
    public Adventurer getAdventurer();

    void saveAdventurer(Adventurer adventurer);

    void exportToJson(String path);
}
