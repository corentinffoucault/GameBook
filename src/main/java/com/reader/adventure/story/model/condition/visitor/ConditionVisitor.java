package com.reader.adventure.story.model.condition.visitor;

import com.reader.adventure.adventurer.model.Adventurer;
import com.reader.adventure.game.dice.Dice20;
import com.reader.adventure.adventurer.AdventurerAttributes;
import com.reader.adventure.story.model.condition.Comparator;
import com.reader.adventure.story.model.condition.ConditionAttributes;
import com.reader.adventure.story.model.condition.ConditionGold;

public class ConditionVisitor implements IConditionVisitor {

    private final Dice20 dice;

    public ConditionVisitor(Dice20 dice) {
        this.dice = dice;
    }

    @Override
    public boolean evaluate(ConditionAttributes condition, Adventurer adventurer) {
        int randomValue = dice.roll();
        int carac = AdventurerAttributes.getValue(adventurer, condition.attribute());
        return Comparator.compare(randomValue, condition.comparator(), carac);
    }

    @Override
    public boolean evaluate(ConditionGold condition, Adventurer adventurer) {
        return Comparator.compare(adventurer.getGold(), condition.comparator(), condition.value());
    }
}