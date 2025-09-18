package com.reader.adventure.story.dao.Jackson.choice;

public record ChoiceDirectJackson(String name,
                                  String text,
                                  String next) implements IChoiceJackson {

}