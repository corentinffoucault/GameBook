package com.reader.adventure.story.model.choice.visitor;

import com.reader.adventure.player.model.Player;
import com.reader.adventure.story.model.choice.ChoiceConditional;
import com.reader.adventure.story.model.choice.ChoiceDirect;
import com.reader.adventure.story.model.choice.SelectedChoice;
import com.reader.adventure.story.model.condition.visitor.IConditionVisitor;

public class ApplyChoiceVisitor implements IChoiceVisitor {
    private final IConditionVisitor conditionVisitor;

    public ApplyChoiceVisitor(IConditionVisitor conditionVisitor) {
        this.conditionVisitor = conditionVisitor;
    }

    @Override
    public SelectedChoice visit(ChoiceDirect choice, Player player) {
        return new SelectedChoice(choice.getText(), choice.getNext());
    }

    @Override
    public SelectedChoice visit(ChoiceConditional choice, Player player) {
        StringBuilder sb = new StringBuilder(choice.getText()).append('\n');
        if (choice.getCondition().evaluate(conditionVisitor, player)) {
            sb.append(choice.getSuccess());
            return new SelectedChoice(sb.toString(), choice.getNext());
        } else {
            sb.append(choice.getFail());
            return new SelectedChoice(sb.toString(), choice.getNextFail());
        }
    }
}