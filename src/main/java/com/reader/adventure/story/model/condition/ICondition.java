package com.reader.adventure.story.model.condition;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

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

    public Integer getValue();

    public String getComparator();

    // TODO: add player parameter
    public boolean evaluate();
}
