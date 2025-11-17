package com.reader.adventure.story.read.dao;

import com.reader.adventure.story.read.model.node.INode;
import com.reader.adventure.story.read.model.story.Story;

import java.io.Reader;

public interface IStoryDao {
    public INode getNodeById(String id);
    public Story getStory();
    public void loadNodes(Reader reader) throws Exception;
    public String getFirstNodeId();
}
