package com.reader.adventure.story.dao.Jackson.choice;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.reader.adventure.story.model.choice.IChoice;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = ChoiceDirectJackson.class, name = "direct"),
        @JsonSubTypes.Type(value = ChoiceConditionalJackson.class, name = "conditional")
})
public sealed interface IChoiceJackson extends IChoice permits ChoiceDirectJackson, ChoiceConditionalJackson {}
