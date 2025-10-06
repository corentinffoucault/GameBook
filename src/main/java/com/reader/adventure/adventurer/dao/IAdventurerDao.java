package com.reader.adventure.adventurer.dao;

import com.reader.adventure.adventurer.model.Adventurer;

import java.io.Reader;

public interface IAdventurerDao {
    Adventurer getAdventurer();
    void saveAdventurer(Adventurer adventurer);
    void exportToJson(String path);
    void loadAdventurer(Reader reader);
}
