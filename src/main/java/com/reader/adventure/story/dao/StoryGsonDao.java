package com.reader.adventure.story.dao;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.reader.adventure.story.model.Node;

import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StoryGsonDao implements IStoryDao {

    private Map<String, Node> nodes;

    public StoryGsonDao() throws Exception {
        this.loadNodes();
    }

    public Node getNodeById(String id) {
        return nodes.get(id);
    }

    private void loadNodes() throws Exception {
        Gson gson = new Gson();
        Type listType = new TypeToken<List<Node>>(){}.getType();
        InputStreamReader reader = new InputStreamReader(
                getClass().getResourceAsStream("/nodes.json"), "UTF-8");
        List<Node> list = gson.fromJson(reader, listType);
        nodes = list.stream().collect(Collectors.toMap(Node::getId, n -> n));
    }
}
