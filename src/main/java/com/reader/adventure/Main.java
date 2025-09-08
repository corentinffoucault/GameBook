package com.reader.adventure;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    private JFrame frame;
    private JLabel titleLabel;
    private JTextArea textArea;
    private JPanel choicesPanel;
    private Map<String, Node> nodes;
    private Node current;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                new Main().start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void start() throws Exception {
        loadNodes();
        createUI();
        showNode("Noeud 1"); // noeud initial — tu peux changer
    }

    private void loadNodes() throws Exception {
        Gson gson = new Gson();
        Type listType = new TypeToken<List<Node>>(){}.getType();
        InputStreamReader reader = new InputStreamReader(
                getClass().getResourceAsStream("/nodes.json"), "UTF-8");
        List<Node> list = gson.fromJson(reader, listType);
        nodes = list.stream().collect(Collectors.toMap(Node::getId, n -> n));
    }

    private void createUI() {
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

    private void showNode(String id) {
        Node node = nodes.get(id);
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
            for (Choice c : current.getChoice()) {
                JButton b = new JButton(c.getName());
                b.setAlignmentX(Component.CENTER_ALIGNMENT);
                b.setMaximumSize(new Dimension(Integer.MAX_VALUE, 36));
                b.addActionListener((ActionEvent e) -> {
                    // optionnel: montrer le texte du choix avant transition (ici on l'affiche dans un popup)
                    if (c.getText() != null && !c.getText().isBlank()) {
                        // on peut afficher un bref aperçu (ou écrire dans textArea)
                        JOptionPane.showMessageDialog(frame, c.getText(), c.getName(), JOptionPane.PLAIN_MESSAGE);
                    }
                    // naviguer vers le prochain noeud
                    showNode(c.getNext());
                });
                choicesPanel.add(Box.createRigidArea(new Dimension(0,6)));
                choicesPanel.add(b);
            }
        }

        choicesPanel.revalidate();
        choicesPanel.repaint();
    }
}