package com.reader.adventure.story.model.choice;

import com.reader.adventure.player.model.Player;
import com.reader.adventure.story.model.choice.visitor.IChoiceVisitor;

public record ChoiceDirect(String name,
                           String text,
                           String next) implements IChoice {

    @Override
    public SelectedChoice applyChoice(IChoiceVisitor visitor, Player player) {
        return visitor.applyChoice(this, player);
    }
}
