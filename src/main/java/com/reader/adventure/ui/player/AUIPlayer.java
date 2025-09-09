package com.reader.adventure.ui.player;

import com.reader.adventure.story.dao.IStoryDao;

public abstract class AUIPlayer {

    protected IStoryDao storyDao;

    public AUIPlayer(IStoryDao storyDao) {
        this.storyDao = storyDao;
    }

    public void startGame(String startingNode) {
        createUI();
        showNode(startingNode); // TODO: Update model to get Starting info
    }

    abstract void createUI();
    abstract void showNode(String startingNode);
}
