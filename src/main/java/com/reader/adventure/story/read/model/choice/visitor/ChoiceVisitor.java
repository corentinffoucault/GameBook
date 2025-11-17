package com.reader.adventure.story.read.model.choice.visitor;

import com.reader.adventure.adventurer.model.Adventurer;
import com.reader.adventure.story.read.model.choice.ChoiceConditional;
import com.reader.adventure.story.read.model.choice.ChoiceDirect;
import com.reader.adventure.story.read.model.choice.SelectedChoice;
import com.reader.adventure.story.read.model.condition.visitor.IConditionVisitor;

public class ChoiceVisitor implements IChoiceVisitor {
    private final IConditionVisitor conditionVisitor;

    public ChoiceVisitor(IConditionVisitor conditionVisitor) {
        this.conditionVisitor = conditionVisitor;
    }

    @Override
    public SelectedChoice applyChoice(ChoiceDirect choice, Adventurer adventurer) {
        return new SelectedChoice(choice.text(), choice.next());
    }

    @Override
    public SelectedChoice applyChoice(ChoiceConditional choice, Adventurer adventurer) {
        StringBuilder sb = new StringBuilder(choice.text()).append('\n');
        if (choice.condition().evaluate(conditionVisitor, adventurer)) {
            sb.append(choice.success());
            return new SelectedChoice(sb.toString(), choice.next());
        } else {
            sb.append(choice.fail());
            return new SelectedChoice(sb.toString(), choice.nextFail());
        }
    }
}