package com.reader.adventure.ui.player;

import com.reader.adventure.player.dao.IPlayerDao;
import com.reader.adventure.story.dao.IStoryDao;

public abstract class AUIPlayer {

    protected IStoryDao storyDao;
    protected IPlayerDao playerDao;

    public AUIPlayer(IStoryDao storyDao, IPlayerDao playerDao) {
        this.storyDao = storyDao;
        this.playerDao = playerDao;
    }

    public void startGame(String startingNode) {
        createUI();
        showNode(startingNode); // TODO: Update model to get Starting info
    }

    abstract void createUI();
    abstract void showNode(String startingNode);
}
