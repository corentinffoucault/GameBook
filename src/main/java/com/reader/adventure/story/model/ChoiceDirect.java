package com.reader.adventure.story.model;

import com.reader.adventure.player.model.Player;

public class ChoiceDirect implements IChoice {
    private String name;
    private String text;
    private String next;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getText() { return text; }
    public void setText(String text) { this.text = text; }

    public String getNext() { return next; }
    public void setNext(String next) { this.next = next; }

    @Override
    public SelectedChoice ApplyChoice(Player player) {
        return new SelectedChoice(text, next);
    }
}