package com.reader.adventure.story.dao.Jackson.choice;

import com.reader.adventure.story.dao.Jackson.condition.IConditionJackson;

public record ChoiceConditionalJackson(String name,
                                       String text,
                                       String next,
                                       String nextFail,
                                       String success,
                                       String fail,
                                       IConditionJackson condition) implements IChoiceJackson {

}