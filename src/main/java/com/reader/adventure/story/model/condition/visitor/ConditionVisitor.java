package com.reader.adventure.story.model.condition.visitor;

import com.reader.adventure.game.dice.ADice;
import com.reader.adventure.player.model.Player;
import com.reader.adventure.story.model.condition.Comparator;
import com.reader.adventure.story.model.condition.ConditionAgility;
import com.reader.adventure.story.model.condition.ConditionGold;

public class ConditionVisitor implements IConditionVisitor {
    private final ADice dice;

    public ConditionVisitor(ADice dice) {
        this.dice = dice;
    }

    @Override
    public boolean evaluate(ConditionAgility condition, Player player) {
        int randomValue = dice.roll();
        return Comparator.compare(randomValue, player.getAgility(), condition.getComparator());
    }

    @Override
    public boolean evaluate(ConditionGold condition, Player player) {
        return Comparator.compare(condition.getValue(), player.getGold(), condition.getComparator());
    }
}