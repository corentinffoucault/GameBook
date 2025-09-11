package com.reader.adventure.story.model.condition;

import com.reader.adventure.player.model.Player;

public class ConditionGold implements ICondition {
    private Integer value;
    private String comparator;

    public Integer getValue() {
        return value;
    }

    public String getComparator() {
        return comparator;
    }

    public boolean evaluate(Player player) {
        return Comparator.compare(value, player.getGold(), comparator);
    }
}
