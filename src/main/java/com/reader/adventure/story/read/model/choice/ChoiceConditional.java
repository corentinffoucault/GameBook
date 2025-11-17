package com.reader.adventure.story.read.model.choice;

import com.reader.adventure.adventurer.model.Adventurer;
import com.reader.adventure.story.read.model.choice.visitor.IChoiceVisitor;
import com.reader.adventure.story.read.model.condition.ICondition;

import java.util.List;

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

    @Override
    public List<DirectionChoice> getAllDirection() {
        String successName = this.name + ": " + this.condition.toString();
        String FailName = this.name + ": " + this.condition.inverseToString();
        return List.of(
                new DirectionChoice(successName, success, next),
                new DirectionChoice(FailName, fail, nextFail)
                );
    }
}
