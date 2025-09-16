package com.reader.adventure.story.dao.Jackson.choice;

import com.reader.adventure.player.model.Player;
import com.reader.adventure.story.model.choice.IChoiceDirect;
import com.reader.adventure.story.model.choice.SelectedChoice;
import com.reader.adventure.story.model.choice.visitor.IChoiceVisitor;

public record ChoiceDirectJackson(String name,
                                  String text,
                                  String next) implements IChoiceJackson, IChoiceDirect {

    @Override
    public SelectedChoice applyChoice(IChoiceVisitor visitor, Player player) {
        return visitor.applyChoice(this, player);
    }
}