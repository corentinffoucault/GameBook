package com.reader.adventure.ui.player.story;

import com.reader.adventure.game.GameBook;
import com.reader.adventure.ui.player.adventurer.AdventurerSheet;
import com.reader.adventure.ui.player.story.choice.AChoicePanel;

public abstract class AUIPlayer {

    protected GameBook gameBook;
    protected AdventurerSheet adventurerSheet;
    protected AChoicePanel choicesPanel;

    public AUIPlayer(GameBook gameBook, AdventurerSheet adventurerSheet, AChoicePanel choicesPanel) {
        this.adventurerSheet = adventurerSheet;
        this.gameBook = gameBook;
        this.choicesPanel = choicesPanel;
    }

    public void startGame(String startingNode) {
        createUI();
        adventurerSheet.createUi();
        showNode(startingNode); // TODO: Update model to get Starting info
    }

    abstract void createUI();
    abstract void showNode(String startingNode);
}
