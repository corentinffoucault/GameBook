package com.reader.adventure.ui.player;

import com.reader.adventure.story.model.node.INode;
import com.reader.adventure.story.model.choice.IChoice;
import com.reader.adventure.story.model.choice.SelectedChoice;
import com.reader.adventure.game.GameBook;
import com.reader.adventure.ui.player.Adventurer.AdventurerSheet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class UIPlayerJFrame extends AUIPlayer {

    private JFrame frame;
    private JLabel titleLabel;
    private JTextArea textArea;
    private JPanel choicesPanel;
    private INode current;

    public UIPlayerJFrame(GameBook gameBook, AdventurerSheet adventurerSheet) {
        super(gameBook, adventurerSheet);
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

        choicesPanel = new JPanel();
        choicesPanel.setLayout(new BoxLayout(choicesPanel, BoxLayout.Y_AXIS));
        JScrollPane cscroll = new JScrollPane(choicesPanel);
        cscroll.setPreferredSize(new Dimension(700, 140));
        frame.add(cscroll, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    protected void showNode(String id) {
        try {
            INode node = gameBook.getNodeById(id);
            current = node;
            titleLabel.setText(node.getTitle());
            textArea.setText(node.getText());
            refreshChoices();
        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(frame, e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private void refreshChoices() {
        choicesPanel.removeAll();

        if (current.getChoice() == null || current.getChoice().isEmpty()) {
            JLabel done = new JLabel("Aucun choix disponible — fin possible.");
            done.setBorder(BorderFactory.createEmptyBorder(8,8,8,8));
            choicesPanel.add(done);
        } else {
            for (IChoice c : current.getChoice()) {
                JButton b = getButton(c);
                choicesPanel.add(Box.createRigidArea(new Dimension(0,6)));
                choicesPanel.add(b);
            }
        }

        choicesPanel.revalidate();
        choicesPanel.repaint();
    }

    private JButton getButton(IChoice c) {
        JButton b = new JButton(c.name());
        b.setAlignmentX(Component.CENTER_ALIGNMENT);
        b.setMaximumSize(new Dimension(Integer.MAX_VALUE, 36));
        b.addActionListener((ActionEvent e) -> {
            SelectedChoice selectedChoice = gameBook.applyChoice(c);
            JOptionPane.showMessageDialog(frame, selectedChoice.text(), c.name(), JOptionPane.PLAIN_MESSAGE);
            showNode(selectedChoice.nextNode());
        });
        return b;
    }
}
