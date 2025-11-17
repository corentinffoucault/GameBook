package com.reader.adventure.ui.player.story.choice;

import com.reader.adventure.story.read.model.choice.SelectedChoice;

public interface IChoiceHandler {
    void onChoiceSelected(String name, SelectedChoice result);
}
