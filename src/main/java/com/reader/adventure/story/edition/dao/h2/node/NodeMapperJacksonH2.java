package com.reader.adventure.story.edition.dao.h2.node;

import com.reader.adventure.story.edition.dao.h2.choice.ChoiceMapperJacksonH2;
import com.reader.adventure.story.read.dao.Jackson.node.NodeJackson;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.Map;

@Mapper(uses = {ChoiceMapperJacksonH2.class})
public interface NodeMapperJacksonH2 {
    NodeMapperJacksonH2 INSTANCE = Mappers.getMapper(NodeMapperJacksonH2.class);

    @Mapping(target = "story", ignore = true)
    NodeH2 sourceToTarget(NodeJackson node);
    Map<String, NodeH2> sourceToTarget(Map<String, NodeJackson> nodes);
}