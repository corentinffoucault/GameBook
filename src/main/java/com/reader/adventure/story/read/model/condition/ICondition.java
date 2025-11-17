package com.reader.adventure.story.read.model.condition;

import com.reader.adventure.adventurer.model.Adventurer;
import com.reader.adventure.story.read.model.condition.visitor.IConditionVisitor;

public sealed interface ICondition permits ConditionAttributes, ConditionGold {
    public boolean evaluate(IConditionVisitor visitor, Adventurer adventurer);
    public String toString();
    public String inverseToString();
}
