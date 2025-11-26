package com.reader.adventure.story.read.dao.Jackson.node;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class NodeDeserializer extends JsonDeserializer<Map<String, NodeJackson>> {

    @Override
    public Map<String, NodeJackson> deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        JsonNode arrayNode = parser.getCodec().readTree(parser);
        Map<String, NodeJackson> nodes = new HashMap<>();

        for (JsonNode node : arrayNode) {
            NodeJackson obj = parser.getCodec().treeToValue(node, NodeJackson.class);
            nodes.put(obj.name(), obj);
        }
        return nodes;
    }
}
