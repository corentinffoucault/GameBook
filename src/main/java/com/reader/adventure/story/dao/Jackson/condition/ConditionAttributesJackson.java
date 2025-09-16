package com.reader.adventure.story.dao.Jackson.condition;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.reader.adventure.game.AttributeKey;
import com.reader.adventure.player.model.Player;
import com.reader.adventure.story.dao.Jackson.AttributeDeserializerJackson;
import com.reader.adventure.story.model.condition.IConditionAttributes;
import com.reader.adventure.story.model.condition.visitor.IConditionVisitor;

public record ConditionAttributesJackson(@JsonDeserialize(using = AttributeDeserializerJackson.class)
                                         AttributeKey attribute,
                                         String comparator) implements IConditionJackson, IConditionAttributes {

    public boolean evaluate(IConditionVisitor visitor, Player player) {
        return visitor.evaluate(this, player);
    }
}