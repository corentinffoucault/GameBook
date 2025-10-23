package com.reader.adventure.story.dao.Jackson.story;

import com.reader.adventure.story.dao.Jackson.node.NodeMapper;
import com.reader.adventure.story.model.story.Story;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {NodeMapper.class})
public interface StoryMapper {
    StoryMapper INSTANCE = Mappers.getMapper(StoryMapper.class);

    Story sourceToTarget(StoryJackson story);
}