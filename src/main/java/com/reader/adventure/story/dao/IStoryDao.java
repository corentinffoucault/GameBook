package com.reader.adventure.story.dao;

import com.reader.adventure.story.model.node.INode;
import com.reader.adventure.story.model.choice.IChoice;

public interface IStoryDao<T extends IChoice> {
    public INode<T> getNodeById(String id);
}
