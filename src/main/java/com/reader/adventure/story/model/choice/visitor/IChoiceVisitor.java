package com.reader.adventure.story.model.choice.visitor;

import com.reader.adventure.player.model.Player;
import com.reader.adventure.story.model.choice.IChoiceConditional;
import com.reader.adventure.story.model.choice.IChoiceDirect;
import com.reader.adventure.story.model.choice.SelectedChoice;

public interface IChoiceVisitor {
    SelectedChoice applyChoice(IChoiceDirect choice, Player player);
    SelectedChoice applyChoice(IChoiceConditional choice, Player player);
}