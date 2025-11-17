package com.reader.adventure.story.read.model.story;

import com.reader.adventure.story.read.model.node.INode;

import java.util.Map;

public record Story(String author,
                    String firstNode,
                    Map<String, INode> nodes) implements IStory {}