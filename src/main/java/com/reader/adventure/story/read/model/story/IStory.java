package com.reader.adventure.story.read.model.story;

import com.reader.adventure.story.read.model.node.INode;

import java.util.Map;

public interface IStory {

    public String getAuthor();
    public void setAuthor(String author);

    public String getFirstNode();
    public void setFirstNode(String firstNode);

    public Map<String, INode> getNodes();
    public void setNodes(Map<String, INode> nodes);

}