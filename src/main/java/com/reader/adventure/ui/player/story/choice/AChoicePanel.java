package com.reader.adventure.ui.player.story.choice;

import com.reader.adventure.story.read.model.choice.IChoice;
import com.reader.adventure.story.read.model.choice.visitor.ChoiceVisitor;
import com.reader.adventure.story.read.model.node.INode;

import javax.swing.*;
import java.awt.*;

public abstract class AChoicePanel extends JPanel {
    public ChoiceVisitor choiceVisitor;

    public AChoicePanel(ChoiceVisitor choiceVisitor) {
        this.choiceVisitor = choiceVisitor;
    }

    protected IChoiceHandler choiceHandler; // callback externe

    public void setChoiceHandler(IChoiceHandler handler) {
        this.choiceHandler = handler;
    }

    public void refreshChoices(INode current) {
        this.removeAll();

        if (current.getChoice() == null || current.getChoice().isEmpty()) {
            JLabel done = new JLabel("Aucun choix disponible — fin possible.");
            done.setBorder(BorderFactory.createEmptyBorder(8,8,8,8));
            this.add(done);
        } else {
            for (IChoice c : current.getChoice()) {
                addChoice(c);
            }
        }

        this.revalidate();
        this.repaint();
    }

    protected void addButton(JButton b) {
        this.add(Box.createRigidArea(new Dimension(0,6)));
        this.add(b);
    }

    protected abstract void addChoice(IChoice c);
}
