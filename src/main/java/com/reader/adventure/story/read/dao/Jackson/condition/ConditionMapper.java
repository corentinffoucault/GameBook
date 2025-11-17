package com.reader.adventure.story.read.dao.Jackson.condition;

import com.reader.adventure.story.read.model.condition.ConditionAttributes;
import com.reader.adventure.story.read.model.condition.ConditionGold;

import com.reader.adventure.story.read.model.condition.ICondition;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ConditionMapper {
    ConditionMapper INSTANCE = Mappers.getMapper(ConditionMapper.class);

    ConditionGold conditionGoldToModel(ConditionGoldJackson condition);
    ConditionAttributes conditionAttributesToModel(ConditionAttributesJackson condition);

    default ICondition map(IConditionJackson condition) {
        if (condition instanceof ConditionAttributesJackson) {
            return conditionAttributesToModel((ConditionAttributesJackson) condition);
        } else if (condition instanceof ConditionGoldJackson) {
            return conditionGoldToModel((ConditionGoldJackson) condition);
        }
        throw new IllegalArgumentException("Type non supporté: " + condition.getClass());
    }
}