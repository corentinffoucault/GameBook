package com.reader.adventure.story.model.node;

import com.reader.adventure.story.model.choice.IChoice;

import java.util.List;

public interface INode<T extends IChoice> {

    public String getId();
    public void setId(String id);

    public String getTitle();
    public void setTitle(String title);

    public String getText();
    public void setText(String text);

    public List<T> getChoice();
    public void setChoice(List<T> choice);
}