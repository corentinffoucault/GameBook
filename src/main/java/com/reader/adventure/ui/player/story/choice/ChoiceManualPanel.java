package com.reader.adventure.ui.player.story.choice;

import com.reader.adventure.story.model.choice.DirectionChoice;
import com.reader.adventure.story.model.choice.IChoice;
import com.reader.adventure.story.model.choice.SelectedChoice;
import com.reader.adventure.story.model.choice.visitor.ChoiceVisitor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class ChoiceManualPanel extends AChoicePanel {

    public ChoiceManualPanel(ChoiceVisitor choiceVisitor) {
        super(choiceVisitor);
    }

    protected void addChoice(IChoice c) {
        List<DirectionChoice> directions = c.getAllDirection();
        for (DirectionChoice direction : directions) {
            JButton b = new JButton(direction.name());
            b.setAlignmentX(Component.CENTER_ALIGNMENT);
            b.setMaximumSize(new Dimension(Integer.MAX_VALUE, 36));

            b.addActionListener((ActionEvent e) -> {
                SelectedChoice selectedChoice = new SelectedChoice(direction.text(), direction.nextNode());
                if (choiceHandler != null) {
                    choiceHandler.onChoiceSelected(direction.name(), selectedChoice);
                }
            });
            addButton(b);
        }
    }
}
