package com.reader.adventure.story.read.model.node;

import com.reader.adventure.story.read.model.choice.IChoice;

import java.util.List;

public interface INode {

    public String getId();
    public void setId(String id);

    public String getTitle();
    public void setTitle(String title);

    public String getText();
    public void setText(String text);

    public List<IChoice> getChoice();
    public void setChoice(List<IChoice> choice);
}