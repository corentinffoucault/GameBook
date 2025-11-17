package com.reader.adventure.story.read.dao.Jackson.choice;

import com.reader.adventure.story.read.dao.Jackson.condition.IConditionJackson;

public record ChoiceConditionalJackson(String name,
                                       String text,
                                       String next,
                                       String nextFail,
                                       String success,
                                       String fail,
                                       IConditionJackson condition) implements IChoiceJackson {

}