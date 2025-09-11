package com.reader.adventure.story.model.condition;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.reader.adventure.player.model.Player;
import com.reader.adventure.story.model.condition.visitor.IConditionVisitor;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = ConditionGold.class, name = "Gold"),
        @JsonSubTypes.Type(value = ConditionAgility.class, name = "AG")
})
public interface ICondition {

    public String getComparator();

    public boolean evaluate(IConditionVisitor visitor, Player player);
}
