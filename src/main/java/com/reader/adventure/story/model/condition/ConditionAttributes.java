package com.reader.adventure.story.model.condition;

import com.reader.adventure.adventurer.model.Adventurer;
import com.reader.adventure.game.AttributeKey;
import com.reader.adventure.game.ComparatorKey;
import com.reader.adventure.story.model.condition.visitor.IConditionVisitor;

public record ConditionAttributes(AttributeKey attribute,
                                  ComparatorKey comparator) implements ICondition {

    public boolean evaluate(IConditionVisitor visitor, Adventurer adventurer) {
        return visitor.evaluate(this, adventurer);
    }

    public String toString() {
        return comparator.getSymbol() + " " + attribute.getName();
    }

    public String inverseToString() {
        return comparator.getInverseSymbol() + " " + attribute.getName();
    }
}
