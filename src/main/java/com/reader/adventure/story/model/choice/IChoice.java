package com.reader.adventure.story.model.choice;

import com.reader.adventure.adventurer.model.Adventurer;
import com.reader.adventure.story.model.choice.visitor.IChoiceVisitor;

public sealed interface IChoice permits ChoiceDirect, ChoiceConditional {
    String name();
    String text();
    String next();

    public SelectedChoice applyChoice(IChoiceVisitor visitor, Adventurer adventurer);
}