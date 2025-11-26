package com.reader.adventure.story.edition.model.condition;

import com.reader.adventure.game.AttributeKey;
import com.reader.adventure.game.ComparatorKey;

public class ConditionAttributes implements ICondition {

    AttributeKey attribute;
    ComparatorKey comparator;


    public AttributeKey getAttribute() {
        return attribute;
    }

    public void setAttribute(AttributeKey attribute) {
        this.attribute = attribute;
    }

    public ComparatorKey getComparator() {
        return comparator;
    }

    public void setComparator(ComparatorKey comparator) {
        this.comparator = comparator;
    }
}
