package com.reader.adventure.story.read.model.node;

import com.reader.adventure.story.read.model.choice.IChoice;

import java.util.List;

public record Node(String name,
                   String title,
                   String text,
                   List<IChoice> choice) implements INode {
}