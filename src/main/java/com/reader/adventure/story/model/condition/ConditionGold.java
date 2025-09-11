package com.reader.adventure.story.model.condition;

public class ConditionGold implements ICondition {
    private Integer value;
    private String comparator;

    public Integer getValue() {
        return value;
    }

    public String getComparator() {
        return comparator;
    }

    public boolean evaluate() {
        // TODO: replace with a valid dice roller and move the condition check in specific class
        double randomValue =Math.random()*20;
        System.out.println(randomValue);
        return randomValue < value;
    }
}
