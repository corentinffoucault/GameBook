package com.reader.adventure.story.model.condition;

import com.reader.adventure.game.AttributeKey;
import com.reader.adventure.player.model.Player;
import com.reader.adventure.story.model.condition.visitor.IConditionVisitor;

public interface IConditionAttributes extends ICondition {
    String comparator();
    AttributeKey attribute();

    public boolean evaluate(IConditionVisitor visitor, Player player);
}
