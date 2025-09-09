package com.reader.adventure.story.model;

import java.util.List;

public class Node {
    private String id;
    private String title;
    private String text;
    private List<Choice> choice;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getText() { return text; }
    public void setText(String text) { this.text = text; }

    public List<Choice> getChoice() { return choice; }
    public void setChoice(List<Choice> choice) { this.choice = choice; }
}