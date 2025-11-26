package com.reader.adventure.story.edition.dao.h2.choice;

import com.reader.adventure.story.edition.dao.h2.condition.ConditionMapperJacksonH2;
import com.reader.adventure.story.read.dao.Jackson.choice.ChoiceConditionalJackson;
import com.reader.adventure.story.read.dao.Jackson.choice.ChoiceDirectJackson;
import com.reader.adventure.story.read.dao.Jackson.choice.IChoiceJackson;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = {ConditionMapperJacksonH2.class})
public interface ChoiceMapperJacksonH2 {
    ChoiceMapperJacksonH2 INSTANCE = Mappers.getMapper(ChoiceMapperJacksonH2.class);

    ChoiceDirectH2 choiceDirectToModel(ChoiceDirectJackson choice);
    ChoiceConditionalH2 choiceConditionalToModel(ChoiceConditionalJackson choice);

    default AChoiceH2 map(IChoiceJackson choice) {
        if (choice instanceof ChoiceDirectJackson) {
            return choiceDirectToModel((ChoiceDirectJackson) choice);
        } else if (choice instanceof ChoiceConditionalJackson) {
            return choiceConditionalToModel((ChoiceConditionalJackson) choice);
        }
        throw new IllegalArgumentException("Type non supporté: " + choice.getClass());
    }

    List<AChoiceH2> mapFromJackson(List<IChoiceJackson> choices);
}