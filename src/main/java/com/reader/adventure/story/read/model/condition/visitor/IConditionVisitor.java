package com.reader.adventure.story.read.model.condition.visitor;

import com.reader.adventure.adventurer.model.Adventurer;
import com.reader.adventure.story.read.model.condition.ConditionAttributes;
import com.reader.adventure.story.read.model.condition.ConditionGold;

public interface IConditionVisitor {
    boolean evaluate(ConditionAttributes condition, Adventurer adventurer);
    boolean evaluate(ConditionGold condition, Adventurer adventurer);
}