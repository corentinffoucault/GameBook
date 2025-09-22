package com.reader.adventure.ui.player;

import com.reader.adventure.game.GameBook;
import com.reader.adventure.ui.player.Adventurer.AdventurerSheet;

public abstract class AUIPlayer {

    protected GameBook gameBook;
    protected AdventurerSheet adventurerSheet;

    public AUIPlayer(GameBook gameBook, AdventurerSheet adventurerSheet) {
        this.adventurerSheet = adventurerSheet;
        this.gameBook = gameBook;
    }

    public void startGame(String startingNode) {
        createUI();
        adventurerSheet.createUi();
        showNode(startingNode); // TODO: Update model to get Starting info
    }

    abstract void createUI();
    abstract void showNode(String startingNode);
}
