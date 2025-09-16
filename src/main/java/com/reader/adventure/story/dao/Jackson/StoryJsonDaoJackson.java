package com.reader.adventure.story.dao.Jackson;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.reader.adventure.story.dao.IStoryDao;
import com.reader.adventure.story.dao.Jackson.node.NodeJackson;
import com.reader.adventure.story.dao.Jackson.choice.IChoiceJackson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.Reader;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StoryJsonDaoJackson implements IStoryDao<IChoiceJackson> {

    private static final Logger logger = LogManager.getLogger(StoryJsonDaoJackson.class);

    private Map<String, NodeJackson> nodesById;

    public StoryJsonDaoJackson(Reader reader) throws Exception {
        this.loadNodes(reader);
    }

    public NodeJackson getNodeById(String id) {
        return nodesById.get(id);
    }

    private void loadNodes(Reader reader) throws Exception {
        try {
            ObjectMapper mapper = new ObjectMapper();
            List<NodeJackson> nodes = mapper.readValue(reader, new TypeReference<List<NodeJackson>>() {});
            nodesById = nodes.stream().collect(Collectors.toMap(NodeJackson::getId, n -> n));
        } catch (Exception e) {
            logger.error("Erreur lors du chargement du fichier", e);
            throw new RuntimeException("Erreur lors du chargement du fichier", e);
        }
    }
}
