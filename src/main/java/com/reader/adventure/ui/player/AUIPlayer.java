package com.reader.adventure.ui.player;

import com.reader.adventure.player.dao.IPlayerDao;
import com.reader.adventure.story.dao.IStoryDao;
import com.reader.adventure.story.model.choice.visitor.ApplyChoiceVisitor;

public abstract class AUIPlayer {

    protected IStoryDao storyDao;
    protected IPlayerDao playerDao;
    protected ApplyChoiceVisitor choiceVisitor;

    public AUIPlayer(IStoryDao storyDao, IPlayerDao playerDao, ApplyChoiceVisitor choiceVisitor) {
        this.storyDao = storyDao;
        this.playerDao = playerDao;
        this.choiceVisitor = choiceVisitor;
    }

    public void startGame(String startingNode) {
        createUI();
        showNode(startingNode); // TODO: Update model to get Starting info
    }

    abstract void createUI();
    abstract void showNode(String startingNode);
}
