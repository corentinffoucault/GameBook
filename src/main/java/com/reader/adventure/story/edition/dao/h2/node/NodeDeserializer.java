package com.reader.adventure.story.edition.dao.h2.node;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class NodeDeserializer extends JsonDeserializer<Map<String, NodeH2>> {

    @Override
    public Map<String, NodeH2> deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        JsonNode arrayNode = parser.getCodec().readTree(parser);
        Map<String, NodeH2> nodes = new HashMap<>();

        for (JsonNode node : arrayNode) {
            NodeH2 obj = parser.getCodec().treeToValue(node, NodeH2.class);
            nodes.put(obj.getName(), obj);
        }
        return nodes;
    }
}
