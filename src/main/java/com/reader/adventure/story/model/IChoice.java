package com.reader.adventure.story.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = ChoiceDirect.class, name = "direct"),
        @JsonSubTypes.Type(value = ChoiceConditional.class, name = "conditional")
})
public interface IChoice {

    public String getName();
    public void setName(String name);

    public String getText();
    public void setText(String text);

    public String getNext();
    public void setNext(String next);

    public String ApplyChoice();
}