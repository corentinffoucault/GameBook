package com.reader.adventure.story.edition.model.choice;

public class ChoiceDirect implements IChoice {
    String name;
    String text;
    String next;


    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }

    public String getNext() {
        return next;
    }
    public void setNext(String next) {
        this.next = next;
    }
}
