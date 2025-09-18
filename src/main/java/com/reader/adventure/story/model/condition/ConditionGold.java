package com.reader.adventure.story.model.condition;

import com.reader.adventure.game.ComparatorKey;
import com.reader.adventure.player.model.Player;
import com.reader.adventure.story.model.condition.visitor.IConditionVisitor;

public record ConditionGold(Integer value,
                            ComparatorKey comparator) implements ICondition {

    public boolean evaluate(IConditionVisitor visitor, Player player) {
        return visitor.evaluate(this, player);
    }
}
