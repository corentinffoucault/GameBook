package com.reader.adventure.game;

import com.reader.adventure.story.dao.IStoryDao;
import com.reader.adventure.story.model.node.INode;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GameBook {

    public IStoryDao storyDao;
    private static final Logger logger = LogManager.getLogger(GameBook.class);

    public GameBook(IStoryDao storyDao) {
        this.storyDao = storyDao;
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

    public String getFirstNodeId() {
        return storyDao.getFirstNodeId();
    }
}
