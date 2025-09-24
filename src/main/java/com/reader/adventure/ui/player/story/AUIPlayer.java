package com.reader.adventure.ui.player.story;

import com.reader.adventure.game.GameBook;
import com.reader.adventure.ui.player.story.choice.AChoicePanel;

public abstract class AUIPlayer {

    protected GameBook gameBook;
    protected AChoicePanel choicesPanel;

    public AUIPlayer(GameBook gameBook, AChoicePanel choicesPanel) {
        this.gameBook = gameBook;
        this.choicesPanel = choicesPanel;
    }

    public void startGame(String startingNode) {
        createUI();
        showNode(startingNode); // TODO: Update model to get Starting info
    }

    abstract void createUI();
    abstract void showNode(String startingNode);
}
