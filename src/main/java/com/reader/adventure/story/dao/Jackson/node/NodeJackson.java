package com.reader.adventure.story.dao.Jackson.node;

import com.reader.adventure.story.dao.Jackson.choice.IChoiceJackson;
import com.reader.adventure.story.model.node.INode;

import java.util.List;

public class NodeJackson implements INode<IChoiceJackson> {
    private String id;
    private String title;
    private String text;
    private List<IChoiceJackson> choice;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getText() { return text; }
    public void setText(String text) { this.text = text; }

    public List<IChoiceJackson> getChoice() { return choice; }
    public void setChoice(List<IChoiceJackson> choice) { this.choice = choice; }
}