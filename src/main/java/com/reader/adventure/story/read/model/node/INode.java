package com.reader.adventure.story.read.model.node;

import com.reader.adventure.story.read.model.choice.IChoice;

import java.util.List;

public sealed interface INode permits Node {
    String id();
    String title();
    String text();
    List<IChoice> choice();
}