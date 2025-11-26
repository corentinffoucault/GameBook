package com.reader.adventure.story.edition.model.condition;

import com.reader.adventure.game.ComparatorKey;

public class ConditionGold implements ICondition {
    Integer value;
    ComparatorKey comparator;


    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public ComparatorKey getComparator() {
        return comparator;
    }

    public void setComparator(ComparatorKey comparator) {
        this.comparator = comparator;
    }
}
