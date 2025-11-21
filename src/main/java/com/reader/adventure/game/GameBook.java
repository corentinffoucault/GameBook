package com.reader.adventure.game;

import com.reader.adventure.story.read.dao.IStoryDao;
import com.reader.adventure.story.read.model.node.INode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class GameBook {

    public IStoryDao storyDao;
    private static final Logger logger = LoggerFactory.getLogger(GameBook.class);

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
