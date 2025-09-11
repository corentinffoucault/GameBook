package com.reader.adventure.story.model.condition;

import com.reader.adventure.player.model.Player;
import com.reader.adventure.story.model.condition.visitor.IConditionVisitor;

public class ConditionGold implements ICondition {
    private Integer value;
    private String comparator;

    public Integer getValue() {
        return value;
    }

    public String getComparator() {
        return comparator;
    }

    public boolean evaluate(IConditionVisitor visitor, Player player) {
        return visitor.evaluate(this, player);
    }
}
