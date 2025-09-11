package com.reader.adventure.story.model.choice.visitor;

import com.reader.adventure.player.model.Player;
import com.reader.adventure.story.model.choice.ChoiceConditional;
import com.reader.adventure.story.model.choice.ChoiceDirect;
import com.reader.adventure.story.model.choice.SelectedChoice;

public interface IChoiceVisitor {
    SelectedChoice visit(ChoiceDirect choice, Player player);
    SelectedChoice visit(ChoiceConditional choice, Player player);
}