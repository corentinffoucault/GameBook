package com.reader.adventure.story.model.condition.visitor;

import com.reader.adventure.game.dice.Dice20;
import com.reader.adventure.player.PlayerAttributes;
import com.reader.adventure.player.model.Player;
import com.reader.adventure.story.model.condition.Comparator;
import com.reader.adventure.story.dao.Jackson.condition.ConditionAttributesJackson;
import com.reader.adventure.story.dao.Jackson.condition.ConditionGoldJackson;

public class ConditionVisitor implements IConditionVisitor {

    private final Dice20 dice;

    public ConditionVisitor(Dice20 dice) {
        this.dice = dice;
    }

    @Override
    public boolean evaluate(ConditionAttributesJackson condition, Player player) {
        int randomValue = dice.roll();
        int carac = PlayerAttributes.getValue(player, condition.attribute());
        return Comparator.compare(randomValue, condition.comparator(), carac);
    }

    @Override
    public boolean evaluate(ConditionGoldJackson condition, Player player) {
        return Comparator.compare(player.getGold(), condition.comparator(), condition.value());
    }
}