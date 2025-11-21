package com.reader.adventure.adventurer.dao;

import com.reader.adventure.adventurer.model.Adventurer;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.io.Reader;

@Repository
@Scope("singleton")
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
