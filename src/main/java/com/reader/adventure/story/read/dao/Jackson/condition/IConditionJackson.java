package com.reader.adventure.story.read.dao.Jackson.condition;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = ConditionGoldJackson.class, name = "Gold"),
        @JsonSubTypes.Type(value = ConditionAttributesJackson.class, name = "attribute")
})

public sealed interface IConditionJackson permits ConditionGoldJackson, ConditionAttributesJackson {}
