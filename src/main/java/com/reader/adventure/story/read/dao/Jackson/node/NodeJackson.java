package com.reader.adventure.story.read.dao.Jackson.node;

import com.reader.adventure.story.read.dao.Jackson.choice.IChoiceJackson;

import java.util.List;

public record NodeJackson(String id,
                          String title,
                          String text,
                          List<IChoiceJackson> choice)  {}