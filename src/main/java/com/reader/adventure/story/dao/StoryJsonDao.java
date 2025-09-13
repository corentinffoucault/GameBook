package com.reader.adventure.story.dao;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.reader.adventure.story.model.Node;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.Reader;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StoryJsonDao implements IStoryDao {

    private static final Logger logger = LogManager.getLogger(StoryJsonDao.class);

    private Map<String, Node> nodesById;

    public StoryJsonDao(Reader reader) throws Exception {
        this.loadNodes(reader);
    }

    public Node getNodeById(String id) {
        return nodesById.get(id);
    }

    private void loadNodes(Reader reader) throws Exception {
        try {
            ObjectMapper mapper = new ObjectMapper();
            List<Node> nodes = mapper.readValue(reader, new TypeReference<List<Node>>() {
            });
            nodesById = nodes.stream().collect(Collectors.toMap(Node::getId, n -> n));
        } catch (Exception e) {
            logger.error("Erreur lors du chargement du fichier", e);
            throw new RuntimeException("Erreur lors du chargement du fichier", e);
        }
    }
}
