package com.reader.adventure.story.model.condition;

import com.reader.adventure.adventurer.model.Adventurer;
import com.reader.adventure.game.ComparatorKey;
import com.reader.adventure.story.model.condition.visitor.IConditionVisitor;

public record ConditionGold(Integer value,
                            ComparatorKey comparator) implements ICondition {

    private static String NAME = "or";

    public boolean evaluate(IConditionVisitor visitor, Adventurer adventurer) {
        return visitor.evaluate(this, adventurer);
    }

    public String toString() {
        return NAME + " " + comparator.getSymbol() + " " + value;
    }

    public String inverseToString() {
        return NAME + " " + comparator.getInverseSymbol() + " " + value;
    }
}
