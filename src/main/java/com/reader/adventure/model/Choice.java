package com.reader.adventure;

public class Choice {
    private String name;
    private String text;
    private String next;

    // getters & setters (Gson en a besoin)
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getText() { return text; }
    public void setText(String text) { this.text = text; }

    public String getNext() { return next; }
    public void setNext(String next) { this.next = next; }
}