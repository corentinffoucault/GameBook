package com.reader.adventure.story.read.model.story;

import com.reader.adventure.story.read.model.node.INode;

import java.util.Map;

public class Story implements IStory {

    private String author;
    private String firstNode;
    private Map<String, INode> nodes;


    public String getAuthor() {  return author; }
    public void setAuthor(String author) { this.author = author; }

    public String getFirstNode() { return firstNode; }
    public void setFirstNode(String firstNode) { this.firstNode = firstNode; }

    public Map<String, INode> getNodes() { return nodes; }
    public void setNodes(Map<String, INode> nodes) { this.nodes = nodes; }
}