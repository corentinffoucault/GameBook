package com.reader.adventure.story.read.dao.Jackson.choice;

import com.reader.adventure.story.read.dao.Jackson.condition.ConditionMapper;
import com.reader.adventure.story.read.model.choice.ChoiceConditional;
import com.reader.adventure.story.read.model.choice.ChoiceDirect;

import com.reader.adventure.story.read.model.choice.IChoice;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {ConditionMapper.class})
public interface ChoiceMapper {
    ChoiceMapper INSTANCE = Mappers.getMapper(ChoiceMapper.class);

    ChoiceDirect choiceDirectToModel(ChoiceDirectJackson choice);
    ChoiceConditional choiceConditionalToModel(ChoiceConditionalJackson choice);

    default IChoice map(IChoiceJackson choice) {
        if (choice instanceof ChoiceDirectJackson) {
            return choiceDirectToModel((ChoiceDirectJackson) choice);
        } else if (choice instanceof ChoiceConditionalJackson) {
            return choiceConditionalToModel((ChoiceConditionalJackson) choice);
        }
        throw new IllegalArgumentException("Type non supporté: " + choice.getClass());
    }
}