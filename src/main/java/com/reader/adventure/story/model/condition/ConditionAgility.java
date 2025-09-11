package com.reader.adventure.story.model.condition;

import com.reader.adventure.player.model.Player;
import com.reader.adventure.story.model.condition.visitor.IConditionVisitor;

public class ConditionAgility implements ICondition {
    private String comparator;

    public String getComparator() {
        return comparator;
    }

    public boolean evaluate(IConditionVisitor visitor, Player player) {
        return visitor.visit(this, player);
    }
}
