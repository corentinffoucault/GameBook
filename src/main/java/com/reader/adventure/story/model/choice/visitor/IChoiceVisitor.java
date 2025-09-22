package com.reader.adventure.story.model.choice.visitor;

import com.reader.adventure.adventurer.model.Adventurer;
import com.reader.adventure.story.model.choice.ChoiceConditional;
import com.reader.adventure.story.model.choice.ChoiceDirect;
import com.reader.adventure.story.model.choice.SelectedChoice;

public interface IChoiceVisitor {
    SelectedChoice applyChoice(ChoiceDirect choice, Adventurer adventurer);
    SelectedChoice applyChoice(ChoiceConditional choice, Adventurer adventurer);
}