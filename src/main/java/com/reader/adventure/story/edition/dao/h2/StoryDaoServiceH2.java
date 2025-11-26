package com.reader.adventure.story.edition.dao.h2;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.reader.adventure.story.edition.dao.IStoryDao;
import com.reader.adventure.story.edition.dao.h2.node.NodeH2;
import com.reader.adventure.story.edition.dao.h2.node.NodeMapper;
import com.reader.adventure.story.edition.dao.h2.story.StoryH2;
import com.reader.adventure.story.edition.dao.h2.story.StoryMapper;
import com.reader.adventure.story.edition.dao.h2.story.StoryMapperJacksonToH2;
import com.reader.adventure.story.edition.model.node.INode;
import com.reader.adventure.story.edition.model.story.Story;
import com.reader.adventure.story.read.dao.Jackson.story.StoryJackson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Reader;

@Repository
public class StoryDaoServiceH2 implements IStoryDao {

    private static final Logger logger = LogManager.getLogger(StoryDaoServiceH2.class);

    private final StoryDaoH2 dao;

    @Autowired
    public StoryDaoServiceH2(StoryDaoH2 dao) {
        this.dao = dao;
    }

    private Long storyId;

    @Transactional
    public void save(StoryH2 story) {
        dao.save(story);
    }

    @Transactional(readOnly = true)
    public StoryH2 find(int id) {
        return dao.find(id);
    }

    @Transactional
    public INode getNodeById(String id) {
        NodeH2 node = dao.getNodeById(id);
        return NodeMapper.INSTANCE.nodeH2toNode(node);
    }

    @Transactional(readOnly = true)
    public Story getStory() {
        StoryH2 story = dao.find(storyId);
        return StoryMapper.INSTANCE.sourceToTarget(story);
    }

    public String getFirstNodeId() {
        return dao.find(storyId).getFirstNode();
    }

    @Transactional
    public void loadNodes(Reader reader) throws RuntimeException {
        try {
            ObjectMapper mapper = new ObjectMapper();
            StoryJackson storyJson = mapper.readValue(reader, new TypeReference<>() {});

            StoryH2 story = StoryMapperJacksonToH2.INSTANCE.sourceToTarget(storyJson);
            save(story);
            storyId = story.getId();

            System.out.println("Tables générées et données insérées avec succès !");
        } catch (Exception e) {
            logger.error("Erreur lors du chargement du fichier aventure", e);
            throw new RuntimeException("Erreur lors du chargement du fichier aventure", e);
        }
    }
}
