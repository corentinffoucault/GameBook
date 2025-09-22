package com.reader.adventure.story.model.choice;

import com.reader.adventure.adventurer.model.Adventurer;
import com.reader.adventure.story.model.choice.visitor.IChoiceVisitor;
import com.reader.adventure.story.model.condition.ICondition;

public record ChoiceConditional(String name,
                                String text,
                                String next,
                                String nextFail,
                                String success,
                                String fail,
                                ICondition condition) implements IChoice {

    @Override
    public SelectedChoice applyChoice(IChoiceVisitor visitor, Adventurer adventurer) {
        return visitor.applyChoice(this, adventurer);
    }
}
