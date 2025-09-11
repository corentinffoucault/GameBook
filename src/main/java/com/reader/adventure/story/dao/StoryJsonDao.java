package com.reader.adventure.story.dao;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.reader.adventure.story.model.Node;

import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StoryJsonDao implements IStoryDao {

    private Map<String, Node> nodesById;

    public StoryJsonDao() throws Exception {
        this.loadNodes();
    }

    public Node getNodeById(String id) {
        return nodesById.get(id);
    }

    private void loadNodes() throws Exception {

        ObjectMapper mapper = new ObjectMapper();

        try (InputStream in = StoryJsonDao.class.getResourceAsStream("/nodes.json")) {
            if (in == null) {
                throw new RuntimeException("nodes.json introuvable !");
            }
            List<Node> nodes = mapper.readValue(in, new TypeReference<List<Node>>() {});
            nodesById = nodes.stream().collect(Collectors.toMap(Node::getId, n -> n));
        }
    }
}
