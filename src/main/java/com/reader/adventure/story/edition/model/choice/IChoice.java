package com.reader.adventure.story.edition.model.choice;

public interface IChoice {
    public String getName();
    public void setName(String name);

    public String getText();
    public void setText(String text);

    public String getNext();
    public void setNext(String next);
}