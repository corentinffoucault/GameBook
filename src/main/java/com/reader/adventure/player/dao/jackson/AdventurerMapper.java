package com.reader.adventure.player.dao.jackson;

import com.reader.adventure.player.model.Player;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AdventurerMapper {
    AdventurerMapper INSTANCE = Mappers.getMapper(AdventurerMapper.class);

    Player sourceToTarget(AdventurerJackson node);
    AdventurerJackson targetToSource(Player node);
}