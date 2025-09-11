package com.reader.adventure.ui.player;

import com.reader.adventure.game.GameBook;

public abstract class AUIPlayer {

    protected GameBook gameBook;

    public AUIPlayer(GameBook gameBook) {
        this.gameBook = gameBook;
    }

    public void startGame(String startingNode) {
        createUI();
        showNode(startingNode); // TODO: Update model to get Starting info
    }

    abstract void createUI();
    abstract void showNode(String startingNode);
}
