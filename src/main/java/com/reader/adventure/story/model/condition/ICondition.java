package com.reader.adventure.story.model.condition;

import com.reader.adventure.player.model.Player;
import com.reader.adventure.story.model.condition.visitor.IConditionVisitor;

public sealed interface ICondition permits ConditionAttributes, ConditionGold {
    public boolean evaluate(IConditionVisitor visitor, Player player);
}
