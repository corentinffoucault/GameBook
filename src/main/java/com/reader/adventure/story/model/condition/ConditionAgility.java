package com.reader.adventure.story.model.condition;

import com.reader.adventure.player.model.Player;

import java.util.Random;

public class ConditionAgility implements ICondition {
    private String comparator;

    public String getComparator() {
        return comparator;
    }

    public boolean evaluate(Player player) {
        // TODO: replace with a valid dice roller and move the condition check in specific class
        int randomValue = new Random().nextInt(20)+1;
        return Comparator.compare(randomValue, player.getAgility(), comparator);
    }
}
