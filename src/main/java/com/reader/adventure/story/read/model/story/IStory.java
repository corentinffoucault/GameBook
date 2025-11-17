package com.reader.adventure.story.read.model.story;

import com.reader.adventure.story.read.model.node.INode;

import java.util.Map;

public sealed interface IStory permits Story {
    String author();
    String firstNode();
    Map<String, INode> nodes();
}