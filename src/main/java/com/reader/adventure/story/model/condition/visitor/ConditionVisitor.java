package com.reader.adventure.story.model.condition.visitor;

import com.reader.adventure.game.dice.Dice20;
import com.reader.adventure.player.model.Player;
import com.reader.adventure.story.model.condition.Comparator;
import com.reader.adventure.story.model.condition.ConditionAgility;
import com.reader.adventure.story.model.condition.ConditionGold;

public class ConditionVisitor implements IConditionVisitor {
    private final Dice20 dice;

    public ConditionVisitor(Dice20 dice) {
        this.dice = dice;
    }

    @Override
    public boolean evaluate(ConditionAgility condition, Player player) {
        int randomValue = dice.roll();
        return Comparator.compare(randomValue, condition.getComparator(), player.getAgility());
    }

    @Override
    public boolean evaluate(ConditionGold condition, Player player) {
        return Comparator.compare(player.getGold(), condition.getComparator(), condition.getValue());
    }
}