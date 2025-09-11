package com.reader.adventure.game;

import com.reader.adventure.player.dao.IPlayerDao;
import com.reader.adventure.story.dao.IStoryDao;
import com.reader.adventure.story.model.Node;
import com.reader.adventure.story.model.choice.IChoice;
import com.reader.adventure.story.model.choice.SelectedChoice;
import com.reader.adventure.story.model.choice.visitor.ApplyChoiceVisitor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GameBook {

    public IStoryDao storyDao;
    public IPlayerDao playerDao;
    public ApplyChoiceVisitor applyChoiceVisitor;
    private static final Logger logger = LogManager.getLogger(GameBook.class);

    public GameBook(IStoryDao storyDao, IPlayerDao playerDao, ApplyChoiceVisitor applyChoiceVisitor) {
        this.storyDao = storyDao;
        this.playerDao = playerDao;
        this.applyChoiceVisitor = applyChoiceVisitor;
    }

    public SelectedChoice applyChoice(IChoice choice) {
        return choice.applyChoice(applyChoiceVisitor, playerDao.getPlayer());
    }

    public Node getNodeById(String id) {
        Node node = storyDao.getNodeById(id);
        if (node == null) {
            String errorMessage = String.format("Node with id %s not found", id);
            logger.error(errorMessage);
            throw new RuntimeException(errorMessage);
        }
        return node;
    }
}
