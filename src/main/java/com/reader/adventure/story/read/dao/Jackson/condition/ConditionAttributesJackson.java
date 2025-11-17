package com.reader.adventure.story.read.dao.Jackson.condition;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.reader.adventure.game.AttributeKey;
import com.reader.adventure.game.ComparatorKey;
import com.reader.adventure.story.read.dao.Jackson.AttributeDeserializerJackson;
import com.reader.adventure.story.read.dao.Jackson.ComparatorDeserializerJackson;

public record ConditionAttributesJackson(@JsonDeserialize(using = AttributeDeserializerJackson.class)
                                         AttributeKey attribute,
                                         @JsonDeserialize(using = ComparatorDeserializerJackson.class)
                                         ComparatorKey comparator) implements IConditionJackson {


}