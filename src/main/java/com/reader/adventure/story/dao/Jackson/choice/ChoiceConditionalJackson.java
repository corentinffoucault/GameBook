package com.reader.adventure.story.dao.Jackson.choice;

import com.reader.adventure.player.model.Player;
import com.reader.adventure.story.dao.Jackson.condition.IConditionJackson;
import com.reader.adventure.story.model.choice.IChoiceConditional;
import com.reader.adventure.story.model.choice.SelectedChoice;
import com.reader.adventure.story.model.choice.visitor.IChoiceVisitor;

public record ChoiceConditionalJackson(String name,
                                       String text,
                                       String next,
                                       String nextFail,
                                       String success,
                                       String fail,
                                       IConditionJackson condition) implements IChoiceJackson, IChoiceConditional<IConditionJackson> {
    @Override
    public SelectedChoice applyChoice(IChoiceVisitor visitor, Player player) {
        return visitor.applyChoice(this, player);
    }
}