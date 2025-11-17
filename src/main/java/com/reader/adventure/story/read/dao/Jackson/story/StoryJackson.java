package com.reader.adventure.story.read.dao.Jackson.story;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.reader.adventure.story.read.dao.Jackson.node.NodeJackson;
import com.reader.adventure.story.read.dao.Jackson.node.NodeDeserializer;

import java.util.Map;

public record StoryJackson(String author,
                           String firstNode,
                           @JsonDeserialize(using = NodeDeserializer.class)
                           Map<String, NodeJackson> nodes) {

}