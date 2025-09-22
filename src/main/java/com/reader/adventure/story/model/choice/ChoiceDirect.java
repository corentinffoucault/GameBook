package com.reader.adventure.story.model.choice;

import com.reader.adventure.adventurer.model.Adventurer;
import com.reader.adventure.story.model.choice.visitor.IChoiceVisitor;

public record ChoiceDirect(String name,
                           String text,
                           String next) implements IChoice {

    @Override
    public SelectedChoice applyChoice(IChoiceVisitor visitor, Adventurer adventurer) {
        return visitor.applyChoice(this, adventurer);
    }
}
