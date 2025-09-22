package com.reader.adventure.ui.player.story.choice;

import com.reader.adventure.game.GameBook;
import com.reader.adventure.story.model.choice.IChoice;
import com.reader.adventure.story.model.node.INode;

import javax.swing.*;
import java.awt.*;

public abstract class AChoicePanel extends JPanel {

    protected GameBook gameBook;

    public AChoicePanel(GameBook gameBook) {
        this.gameBook = gameBook;
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
                JButton b = getButton(c);
                this.add(Box.createRigidArea(new Dimension(0,6)));
                this.add(b);
            }
        }

        this.revalidate();
        this.repaint();
    }

    protected abstract JButton getButton(IChoice c);
}
