package com.reader.adventure.story.dao.Jackson.condition;

import com.reader.adventure.player.model.Player;
import com.reader.adventure.story.model.condition.IConditionGold;
import com.reader.adventure.story.model.condition.visitor.IConditionVisitor;

public record ConditionGoldJackson(Integer value,
                                   String comparator) implements IConditionJackson, IConditionGold {
    public boolean evaluate(IConditionVisitor visitor, Player player) {
        return visitor.evaluate(this, player);
    }
}
