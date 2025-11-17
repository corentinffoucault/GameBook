package com.reader.adventure.story.read.dao.Jackson.node;

import com.reader.adventure.story.read.dao.Jackson.choice.ChoiceMapper;
import com.reader.adventure.story.read.model.node.INode;
import com.reader.adventure.story.read.model.node.Node;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Map;

@Mapper(uses = {ChoiceMapper.class})
public interface NodeMapper {
    NodeMapper INSTANCE = Mappers.getMapper(NodeMapper.class);

    Node sourceToTarget(NodeJackson node);
    Map<String, INode> sourceToTarget(Map<String, NodeJackson> nodes);
}