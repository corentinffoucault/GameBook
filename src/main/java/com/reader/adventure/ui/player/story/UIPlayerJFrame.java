package com.reader.adventure.ui.player.story;

import com.reader.adventure.story.model.node.INode;
import com.reader.adventure.game.GameBook;
import com.reader.adventure.ui.player.adventurer.AdventurerSheet;
import com.reader.adventure.ui.player.story.choice.AChoicePanel;
import com.reader.adventure.ui.player.story.choice.ChoiceWithAutoDicePanel;

import javax.swing.*;
import java.awt.*;

public class UIPlayerJFrame extends AUIPlayer {

    private JFrame frame;
    private JLabel titleLabel;
    private JTextArea textArea;

    public UIPlayerJFrame(GameBook gameBook, AdventurerSheet adventurerSheet, AChoicePanel choicesPanel) {
        super(gameBook, adventurerSheet, choicesPanel);
    }

    protected void createUI() {
        frame = new JFrame("Aventure (exemple)");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout(10,10));
        frame.setSize(700, 450);
        frame.setLocationRelativeTo(null);

        titleLabel = new JLabel("", SwingConstants.CENTER);
        titleLabel.setFont(titleLabel.getFont().deriveFont(20f));
        frame.add(titleLabel, BorderLayout.NORTH);

        textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);
        textArea.setFont(textArea.getFont().deriveFont(14f));
        JScrollPane scroll = new JScrollPane(textArea);
        frame.add(scroll, BorderLayout.CENTER);

        choicesPanel.setLayout(new BoxLayout(choicesPanel, BoxLayout.Y_AXIS));
        JScrollPane cscroll = new JScrollPane(choicesPanel);
        cscroll.setPreferredSize(new Dimension(700, 140));
        frame.add(cscroll, BorderLayout.SOUTH);

        choicesPanel.setChoiceHandler((choice, result) -> {
            JOptionPane.showMessageDialog(frame, result.text(), choice.name(), JOptionPane.PLAIN_MESSAGE);
            showNode(result.nextNode());
        });

        frame.setVisible(true);
    }

    protected void showNode(String id) {
        try {
            INode node = gameBook.getNodeById(id);
            titleLabel.setText(node.getTitle());
            textArea.setText(node.getText());
            choicesPanel.refreshChoices(node);
        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(frame, e.getMessage());
            throw new RuntimeException(e);
        }
    }

}
