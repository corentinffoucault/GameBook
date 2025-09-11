package com.reader.adventure.story.model.choice;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.reader.adventure.player.model.Player;
import com.reader.adventure.story.model.choice.visitor.IChoiceVisitor;

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

    public SelectedChoice applyChoice(IChoiceVisitor visitor, Player player);
}