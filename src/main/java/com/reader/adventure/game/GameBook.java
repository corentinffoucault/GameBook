package com.reader.adventure.game;

import com.reader.adventure.adventurer.dao.IAdventurerDao;
import com.reader.adventure.story.dao.IStoryDao;
import com.reader.adventure.story.model.node.INode;
import com.reader.adventure.story.model.choice.IChoice;
import com.reader.adventure.story.model.choice.SelectedChoice;
import com.reader.adventure.story.model.choice.visitor.ChoiceVisitor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GameBook {

    public IStoryDao storyDao;
    public IAdventurerDao adventurerDao;
    public ChoiceVisitor choiceVisitor;
    private static final Logger logger = LogManager.getLogger(GameBook.class);

    public GameBook(IStoryDao storyDao, IAdventurerDao adventurerDao, ChoiceVisitor choiceVisitor) {
        this.storyDao = storyDao;
        this.adventurerDao = adventurerDao;
        this.choiceVisitor = choiceVisitor;
    }

    public SelectedChoice applyChoice(IChoice choice) {
        return choice.applyChoice(choiceVisitor, adventurerDao.getAdventurer());
    }

    public INode getNodeById(String id) {
        INode node = storyDao.getNodeById(id);
        if (node == null) {
            String errorMessage = String.format("Node with id %s not found", id);
            logger.error(errorMessage);
            throw new RuntimeException(errorMessage);
        }
        return node;
    }
}
