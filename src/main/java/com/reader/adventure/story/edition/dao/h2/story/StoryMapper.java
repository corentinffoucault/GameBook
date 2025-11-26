package com.reader.adventure.story.edition.dao.h2.story;

import com.fasterxml.jackson.databind.JsonNode;
import com.reader.adventure.story.edition.dao.h2.node.NodeH2;
import com.reader.adventure.story.edition.dao.h2.node.NodeMapper;
import com.reader.adventure.story.edition.dao.h2.node.NodeMapperJacksonH2;
import com.reader.adventure.story.edition.model.node.INode;
import com.reader.adventure.story.edition.model.node.Node;
import com.reader.adventure.story.edition.model.story.Story;
import com.reader.adventure.story.read.dao.Jackson.node.NodeJackson;
import com.reader.adventure.story.read.dao.Jackson.story.StoryJackson;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper(uses = {NodeMapper.class})
public interface StoryMapper {
    StoryMapper INSTANCE = Mappers.getMapper(StoryMapper.class);

    @Mapping(source = "nodes", target = "nodes", qualifiedByName = "mapNodes")
    Story sourceToTarget(StoryH2 story);

    @Named("mapNodes")
    default Map<String, INode> mapNodes(List<NodeH2> nodes) {
        if (nodes == null) return null;

        List<INode> nodesList = nodes.stream()
                                .map(NodeMapper.INSTANCE::map)
                                .toList();

        Map<String, INode> nodesMap = new HashMap<>();

        for (INode node : nodesList) {
            nodesMap.put(node.getId(), node);
        }
        return nodesMap;
    }
}