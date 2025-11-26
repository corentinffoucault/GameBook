package com.reader.adventure.story.edition.dao.h2.story;

import com.reader.adventure.story.edition.dao.h2.node.NodeH2;
import com.reader.adventure.story.edition.dao.h2.node.NodeMapperJacksonH2;
import com.reader.adventure.story.read.dao.Jackson.node.NodeJackson;
import com.reader.adventure.story.read.dao.Jackson.story.StoryJackson;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Map;

@Mapper(uses = {NodeMapperJacksonH2.class})
public interface StoryMapperJacksonToH2 {
    StoryMapperJacksonToH2 INSTANCE = Mappers.getMapper(StoryMapperJacksonToH2.class);

    @Mapping(source = "nodes", target = "nodes", qualifiedByName = "mapNodes")
    StoryH2 sourceToTarget(StoryJackson story);

    @AfterMapping
    default void linkChildren(@MappingTarget StoryH2 story) {
        if (story.getNodes() == null) return;
        story.getNodes().forEach(node -> node.setStory(story));
    }

    @Named("mapNodes")
    default List<NodeH2> mapNodes(Map<String, NodeJackson> nodes) {
        if (nodes == null) return null;
        return nodes.values()
                .stream()
                .map(NodeMapperJacksonH2.INSTANCE::sourceToTarget)
                .toList();
    }
}