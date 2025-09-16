package com.reader.adventure.story.model.condition.visitor;

import com.reader.adventure.player.model.Player;
import com.reader.adventure.story.dao.Jackson.condition.ConditionAttributesJackson;
import com.reader.adventure.story.dao.Jackson.condition.ConditionGoldJackson;

public interface IConditionVisitor {
    boolean evaluate(ConditionAttributesJackson condition, Player player);
    boolean evaluate(ConditionGoldJackson condition, Player player);
}