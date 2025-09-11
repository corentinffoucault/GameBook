package com.reader.adventure.story.model.condition.visitor;

import com.reader.adventure.player.model.Player;
import com.reader.adventure.story.model.condition.Comparator;
import com.reader.adventure.story.model.condition.ConditionAgility;
import com.reader.adventure.story.model.condition.ConditionGold;

import java.util.Random;

public class ConditionVisitor implements IConditionVisitor {

    @Override
    public boolean evaluate(ConditionAgility condition, Player player) {
        // TODO: replace with a valid dice roller and move the condition check in specific class
        int randomValue = new Random().nextInt(20)+1;
        return Comparator.compare(randomValue, player.getAgility(), condition.getComparator());
    }

    @Override
    public boolean evaluate(ConditionGold condition, Player player) {
        return Comparator.compare(condition.getValue(), player.getGold(), condition.getComparator());
    }
}