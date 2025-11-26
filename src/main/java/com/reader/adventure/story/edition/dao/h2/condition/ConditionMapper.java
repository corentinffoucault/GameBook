package com.reader.adventure.story.edition.dao.h2.condition;

import com.reader.adventure.story.edition.model.condition.ConditionAttributes;
import com.reader.adventure.story.edition.model.condition.ConditionGold;
import com.reader.adventure.story.edition.model.condition.ICondition;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ConditionMapper {
    ConditionMapper INSTANCE = Mappers.getMapper(ConditionMapper.class);

    ConditionGold conditionGoldToModel(ConditionGoldH2 condition);
    ConditionAttributes conditionAttributesToModel(ConditionAttributesH2 condition);

    default ICondition map(AConditionH2 condition) {
        if (condition instanceof ConditionAttributesH2) {
            return conditionAttributesToModel((ConditionAttributesH2) condition);
        } else if (condition instanceof ConditionGoldH2) {
            return conditionGoldToModel((ConditionGoldH2) condition);
        }
        throw new IllegalArgumentException("Type non supporté: " + condition.getClass());
    }
}