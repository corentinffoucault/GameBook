package com.reader.adventure.ui.player.story.choice;

import com.reader.adventure.story.model.choice.IChoice;
import com.reader.adventure.story.model.choice.SelectedChoice;

public interface IChoiceHandler {
    void onChoiceSelected(IChoice choice, SelectedChoice result);
}
