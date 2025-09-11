package com.reader.adventure.story.model.condition.visitor;

import com.reader.adventure.player.model.Player;
import com.reader.adventure.story.model.condition.ConditionAgility;
import com.reader.adventure.story.model.condition.ConditionGold;

public interface IConditionVisitor {
    boolean evaluate(ConditionAgility condition, Player player);
    boolean evaluate(ConditionGold condition, Player player);
}