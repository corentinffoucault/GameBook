package com.reader.adventure.story.dao;

import com.reader.adventure.story.model.node.INode;

import java.io.Reader;
import java.util.Map;

public interface IStoryDao {
    public INode getNodeById(String id);
    public Map<String, INode> getStory();
    public void loadNodes(Reader reader) throws Exception;
}
