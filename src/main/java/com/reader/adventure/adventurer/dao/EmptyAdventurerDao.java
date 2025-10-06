package com.reader.adventure.adventurer.dao;

import com.reader.adventure.adventurer.model.Adventurer;

import java.io.Reader;

public class EmptyAdventurerDao implements IAdventurerDao {

    @Override
    public Adventurer getAdventurer() {
        return new  Adventurer();
    }

    @Override
    public void saveAdventurer(Adventurer adventurer) {

    }

    @Override
    public void exportToJson(String path) {

    }

    @Override
    public void loadAdventurer(Reader reader) {

    }
}
