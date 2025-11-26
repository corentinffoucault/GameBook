package com.reader.adventure.story.edition.model.node;

import com.reader.adventure.story.edition.model.choice.IChoice;

import java.util.List;

public class Node implements INode {
    private String id;
    private String title;
    private String text;
    private List<IChoice> choice;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getText() { return text; }
    public void setText(String text) { this.text = text; }

    public List<IChoice> getChoice() { return choice; }
    public void setChoice(List<IChoice> choice) { this.choice = choice; }
}