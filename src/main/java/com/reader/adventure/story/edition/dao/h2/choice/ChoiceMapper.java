package com.reader.adventure.story.edition.dao.h2.choice;

import com.reader.adventure.story.edition.dao.h2.condition.ConditionMapper;
import com.reader.adventure.story.edition.model.choice.ChoiceConditional;
import com.reader.adventure.story.edition.model.choice.ChoiceDirect;
import com.reader.adventure.story.edition.model.choice.IChoice;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {ConditionMapper.class})
public interface ChoiceMapper {
    ChoiceMapper INSTANCE = Mappers.getMapper(ChoiceMapper.class);

    ChoiceDirect choiceDirectToModel(ChoiceDirectH2 choice);
    ChoiceConditional choiceConditionalToModel(ChoiceConditionalH2 choice);

    default IChoice map(AChoiceH2 choice) {
        if (choice instanceof ChoiceDirectH2) {
            return choiceDirectToModel((ChoiceDirectH2) choice);
        } else if (choice instanceof ChoiceConditionalH2) {
            return choiceConditionalToModel((ChoiceConditionalH2) choice);
        }
        throw new IllegalArgumentException("Type non supporté: " + choice.getClass());
    }
}