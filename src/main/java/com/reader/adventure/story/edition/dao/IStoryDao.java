package com.reader.adventure.story.edition.dao;

import com.reader.adventure.story.edition.model.node.INode;
import com.reader.adventure.story.edition.model.story.Story;

import java.io.Reader;

public interface IStoryDao {
    public INode getNodeById(String id);
    public Story getStory();
    public void loadNodes(Reader reader) throws Exception;
    public String getFirstNodeId();
}
