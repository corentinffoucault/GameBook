package com.reader.adventure.ui.player.story.choice;

import com.reader.adventure.adventurer.dao.IAdventurerDao;
import com.reader.adventure.story.read.model.choice.IChoice;
import com.reader.adventure.story.read.model.choice.SelectedChoice;
import com.reader.adventure.story.read.model.choice.visitor.ChoiceVisitor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ChoiceWithAutoDicePanel extends AChoicePanel {

    private IAdventurerDao adventurerDao;

    public ChoiceWithAutoDicePanel(ChoiceVisitor choiceVisitor, IAdventurerDao adventurerDao) {
        super(choiceVisitor);
        this.adventurerDao = adventurerDao;
    }

    protected void addChoice(IChoice c) {
        JButton b = new JButton(c.name());
        b.setAlignmentX(Component.CENTER_ALIGNMENT);
        b.setMaximumSize(new Dimension(Integer.MAX_VALUE, 36));

        b.addActionListener((ActionEvent e) -> {
            SelectedChoice selectedChoice = c.applyChoice(choiceVisitor, adventurerDao.getAdventurer());
            if (choiceHandler != null) {
                choiceHandler.onChoiceSelected(c.name(), selectedChoice);
            }
        });
        addButton(b);
    }
}
