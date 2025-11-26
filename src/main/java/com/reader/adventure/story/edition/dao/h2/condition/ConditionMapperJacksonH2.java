package com.reader.adventure.story.edition.dao.h2.condition;

import com.reader.adventure.story.read.dao.Jackson.condition.ConditionAttributesJackson;
import com.reader.adventure.story.read.dao.Jackson.condition.ConditionGoldJackson;
import com.reader.adventure.story.read.dao.Jackson.condition.IConditionJackson;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public interface ConditionMapperJacksonH2 {
    ConditionMapperJacksonH2 INSTANCE = Mappers.getMapper(ConditionMapperJacksonH2.class);

    ConditionGoldH2 conditionGoldToModel(ConditionGoldJackson condition);
    ConditionAttributesH2 conditionAttributesToModel(ConditionAttributesJackson condition);

    default AConditionH2 map(IConditionJackson condition) {
        if (condition instanceof ConditionAttributesJackson) {
            return conditionAttributesToModel((ConditionAttributesJackson) condition);
        } else if (condition instanceof ConditionGoldJackson) {
            return conditionGoldToModel((ConditionGoldJackson) condition);
        }
        throw new IllegalArgumentException("Type non supporté: " + condition.getClass());
    }
}