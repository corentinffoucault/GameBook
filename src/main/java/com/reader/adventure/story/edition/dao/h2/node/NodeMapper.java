package com.reader.adventure.story.edition.dao.h2.node;

import com.reader.adventure.story.edition.dao.h2.choice.ChoiceMapper;
import com.reader.adventure.story.edition.model.node.INode;
import com.reader.adventure.story.edition.model.node.Node;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.Map;

@Mapper(uses = {ChoiceMapper.class})
public interface NodeMapper {
    NodeMapper INSTANCE = Mappers.getMapper(NodeMapper.class);

    Node nodeH2toNode(NodeH2 node);
    Map<String, INode> sourceToTarget(Map<String, NodeH2> nodes);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "story", ignore = true)
    default INode map(NodeH2 node) {
        return nodeH2toNode(node);
    }
}