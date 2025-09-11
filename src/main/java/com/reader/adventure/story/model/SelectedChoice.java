package com.reader.adventure.story.model;

public class SelectedChoice {
        private String text;
        private String nextNode;

    public SelectedChoice(String text, String nextNode) {
        this.text = text;
        this.nextNode = nextNode;
    }

    public String getText() {
        return text;
    }

    public String getNextNode() {
        return nextNode;
    }
}
