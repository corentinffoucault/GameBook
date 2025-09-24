package com.reader.adventure.ui.player.adventurer;

import com.reader.adventure.adventurer.model.Adventurer;

public interface IAdventurerHandler {
    void onAdventurerCompleted(Adventurer adventurer) throws Exception;
}
