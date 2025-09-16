package com.reader.adventure.story.model.condition;

import com.reader.adventure.player.model.Player;
import com.reader.adventure.story.model.condition.visitor.IConditionVisitor;

public interface IConditionGold extends ICondition {
    Integer value();
    String comparator();

    public boolean evaluate(IConditionVisitor visitor, Player player);
}
