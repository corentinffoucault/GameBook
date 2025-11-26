package com.reader.adventure.story.edition.model.condition;


import com.reader.adventure.game.ComparatorKey;

public interface ICondition {
    public void setComparator(ComparatorKey comparator);
    public ComparatorKey getComparator();

}
