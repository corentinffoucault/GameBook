package com.reader.adventure.story.model.choice;

import com.reader.adventure.player.model.Player;
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
    public SelectedChoice applyChoice(IChoiceVisitor visitor, Player player) {
        return visitor.applyChoice(this, player);
    }
}
