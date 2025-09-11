package com.reader.adventure.story.model;

import com.reader.adventure.story.model.condition.ICondition;

public class ChoiceConditional implements IChoice {
    private String name;
    private String text;
    private String next;
    private String success;
    private String fail;
    private ICondition condition;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getText() { return text; }
    public void setText(String text) { this.text = text; }

    public String getNext() { return next; }
    public void setNext(String next) { this.next = next; }

    public String getSuccess() { return success; }
    public void setSuccess(String success) { this.success = success; }

    public String getFail() { return fail; }
    public void setFail(String fail) { this.fail = fail; }

    public ICondition getCondition() { return condition; }
    public void setCondition(ICondition condition) { this.condition = condition; }


    @Override
    public String ApplyChoice() {
        StringBuilder stringBuilder = new StringBuilder(this.getText());
        stringBuilder.append('\n');
        if(this.condition.evaluate()) {
            stringBuilder.append(this.getSuccess());
        } else {
            stringBuilder.append(this.getFail());
        }
        return stringBuilder.toString();
    }
}