package com.reader.adventure.story.model.choice;

import com.reader.adventure.story.model.condition.ICondition;

public interface IChoiceConditional<T extends ICondition> extends IChoice {
    String nextFail();
    String success();
    String fail();
    T condition();
}
