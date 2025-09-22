package com.reader.adventure.adventurer.dao.jackson;

import com.reader.adventure.adventurer.model.Adventurer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AdventurerMapper {
    AdventurerMapper INSTANCE = Mappers.getMapper(AdventurerMapper.class);

    Adventurer sourceToTarget(AdventurerJackson node);
    AdventurerJackson targetToSource(Adventurer node);
}