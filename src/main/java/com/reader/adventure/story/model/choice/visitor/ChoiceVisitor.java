package com.reader.adventure.story.model.choice.visitor;

import com.reader.adventure.player.model.Player;
import com.reader.adventure.story.model.choice.IChoiceConditional;
import com.reader.adventure.story.model.choice.IChoiceDirect;
import com.reader.adventure.story.model.choice.SelectedChoice;
import com.reader.adventure.story.model.condition.visitor.IConditionVisitor;

public class ChoiceVisitor implements IChoiceVisitor {
    private final IConditionVisitor conditionVisitor;

    public ChoiceVisitor(IConditionVisitor conditionVisitor) {
        this.conditionVisitor = conditionVisitor;
    }

    @Override
    public SelectedChoice applyChoice(IChoiceDirect choice, Player player) {
        return new SelectedChoice(choice.text(), choice.next());
    }

    @Override
    public SelectedChoice applyChoice(IChoiceConditional choice, Player player) {
        StringBuilder sb = new StringBuilder(choice.text()).append('\n');
        if (choice.condition().evaluate(conditionVisitor, player)) {
            sb.append(choice.success());
            return new SelectedChoice(sb.toString(), choice.next());
        } else {
            sb.append(choice.fail());
            return new SelectedChoice(sb.toString(), choice.nextFail());
        }
    }
}