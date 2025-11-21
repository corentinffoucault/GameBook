package com.reader.adventure.story.read.dao.Jackson;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.reader.adventure.story.read.dao.IStoryDao;
import com.reader.adventure.story.read.dao.Jackson.node.NodeMapper;
import com.reader.adventure.story.read.dao.Jackson.story.StoryJackson;
import com.reader.adventure.story.read.dao.Jackson.story.StoryMapper;
import com.reader.adventure.story.read.model.node.INode;
import com.reader.adventure.story.read.model.story.Story;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Reader;

@Repository
@Scope("singleton")
public class StoryJsonDaoJackson implements IStoryDao {

    private static final Logger logger = LoggerFactory.getLogger(StoryJsonDaoJackson.class);

    private StoryJackson story;

    public INode getNodeById(String id) {
        return NodeMapper.INSTANCE.sourceToTarget(story.nodes().get(id));
    }

    public Story getStory() {
        return StoryMapper.INSTANCE.sourceToTarget(story);
    }

    public String getFirstNodeId() {
        return story.firstNode();
    }

    public void loadNodes(Reader reader) throws RuntimeException {
        try {
            ObjectMapper mapper = new ObjectMapper();
            story = mapper.readValue(reader, new TypeReference<>() {});
        } catch (Exception e) {
            logger.error("Erreur lors du chargement du fichier aventure", e);
            throw new RuntimeException("Erreur lors du chargement du fichier aventure", e);
        }
    }
}
