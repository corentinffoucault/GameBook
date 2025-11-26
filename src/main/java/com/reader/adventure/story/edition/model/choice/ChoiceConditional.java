package com.reader.adventure.story.edition.model.choice;

import com.reader.adventure.story.edition.model.condition.ICondition;

public class ChoiceConditional implements IChoice {

    String name;
    String text;
    String next;
    String nextFail;
    String success;
    String fail;
    ICondition condition;


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

    public String getNextFail() {
        return nextFail;
    }
    public void setNextFail(String nextFail) {
        this.nextFail = nextFail;
    }

    public String getSuccess() {
        return success;
    }
    public void setSuccess(String success) {
        this.success = success;
    }

    public String getFail() {
        return fail;
    }
    public void setFail(String fail) {
        this.fail = fail;
    }

    public ICondition getCondition() {
        return condition;
    }
    public void setCondition(ICondition condition) {
        this.condition = condition;
    }
}
