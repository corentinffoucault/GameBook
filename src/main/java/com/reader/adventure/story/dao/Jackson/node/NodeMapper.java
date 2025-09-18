package com.reader.adventure.story.dao.Jackson.node;

import com.reader.adventure.story.dao.Jackson.choice.ChoiceMapper;
import com.reader.adventure.story.model.node.Node;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {ChoiceMapper.class})
public interface NodeMapper {
    NodeMapper INSTANCE = Mappers.getMapper(NodeMapper.class);

    Node sourceToTarget(NodeJackson node);
}