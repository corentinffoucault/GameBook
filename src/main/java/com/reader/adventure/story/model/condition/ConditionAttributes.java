package com.reader.adventure.story.model.condition;

import com.reader.adventure.player.model.Player;
import com.reader.adventure.story.model.condition.visitor.IConditionVisitor;

public class ConditionAttributes implements ICondition {
    private String comparator;
    private String type;

    public String getComparator() {
        return comparator;
    }

    public void setComparator(String comparator) {
        this.comparator = comparator;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean evaluate(IConditionVisitor visitor, Player player) {
        return visitor.evaluate(this, player);
    }
}
