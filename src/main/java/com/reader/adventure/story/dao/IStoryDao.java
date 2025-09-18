package com.reader.adventure.story.dao;

import com.reader.adventure.story.model.node.INode;

public interface IStoryDao {
    public INode getNodeById(String id);
}
