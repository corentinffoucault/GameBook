package com.reader.adventure.story.dao.Jackson.condition;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.reader.adventure.game.AttributeKey;
import com.reader.adventure.story.dao.Jackson.AttributeDeserializerJackson;

public record ConditionAttributesJackson(@JsonDeserialize(using = AttributeDeserializerJackson.class)
                                         AttributeKey attribute,
                                         String comparator) implements IConditionJackson {


}