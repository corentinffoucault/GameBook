package com.reader.adventure.ui.player.story.choice;

import com.reader.adventure.game.GameBook;
import com.reader.adventure.story.model.choice.IChoice;
import com.reader.adventure.story.model.choice.SelectedChoice;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ChoiceWithAutoDicePanel extends AChoicePanel {

    public ChoiceWithAutoDicePanel(GameBook gameBook) {
        super(gameBook);
    }

    protected JButton getButton(IChoice c) {
        JButton b = new JButton(c.name());
        b.setAlignmentX(Component.CENTER_ALIGNMENT);
        b.setMaximumSize(new Dimension(Integer.MAX_VALUE, 36));

        b.addActionListener((ActionEvent e) -> {
            SelectedChoice selectedChoice = gameBook.applyChoice(c);
            if (choiceHandler != null) {
                choiceHandler.onChoiceSelected(c, selectedChoice);
            }
        });
        return b;
    }
}
