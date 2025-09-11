package com.reader.adventure.ui.player;

import com.reader.adventure.player.dao.IPlayerDao;
import com.reader.adventure.story.dao.IStoryDao;
import com.reader.adventure.story.model.choice.IChoice;
import com.reader.adventure.story.model.Node;
import com.reader.adventure.story.model.choice.SelectedChoice;
import com.reader.adventure.story.model.choice.visitor.ApplyChoiceVisitor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class UIPlayerJFrame extends AUIPlayer {

    private JFrame frame;
    private JLabel titleLabel;
    private JTextArea textArea;
    private JPanel choicesPanel;
    private Node current;

    public UIPlayerJFrame(IStoryDao storyDao, IPlayerDao playerDao, ApplyChoiceVisitor choiceVisitor) {
        super(storyDao, playerDao, choiceVisitor);
    }

    public void startGame(String startingNode) {
        createUI();
        showNode(startingNode); // TODO: Update model to get Starting info
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
        Node node = storyDao.getNodeById(id);
        if (node == null) {
            JOptionPane.showMessageDialog(frame, "Noeud introuvable: " + id);
            return;
        }
        current = node;
        titleLabel.setText(node.getTitle());
        textArea.setText(node.getText());
        refreshChoices();
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
        JButton b = new JButton(c.getName());
        b.setAlignmentX(Component.CENTER_ALIGNMENT);
        b.setMaximumSize(new Dimension(Integer.MAX_VALUE, 36));
        b.addActionListener((ActionEvent e) -> {
            SelectedChoice selectedChoice = c.applyChoice(choiceVisitor, playerDao.getPlayer());
            JOptionPane.showMessageDialog(frame, selectedChoice.getText(), c.getName(), JOptionPane.PLAIN_MESSAGE);
            showNode(selectedChoice.getNextNode());
        });
        return b;
    }
}
