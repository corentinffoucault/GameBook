package com.reader.adventure.ui;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class GameLauncherUI extends JFrame {
    private JTextField playerFileField;
    private JTextField storyFileField;
    private JRadioButton manualChoiceRadio;
    private JRadioButton autoDiceRadio;
    private IGameOptionHandler gameOptionHandler;

    public GameLauncherUI() {
        setTitle("Configuration du jeu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(550, 220);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JPanel panel = new JPanel(new GridLayout(3, 3, 5, 5));

        panel.add(new JLabel("Fichier joueur :"));
        playerFileField = new JTextField();
        panel.add(playerFileField);
        JButton browsePlayerBtn = new JButton("Parcourir...");
        browsePlayerBtn.addActionListener(e -> chooseFile(playerFileField));
        panel.add(browsePlayerBtn);

        panel.add(new JLabel("Fichier histoire :"));
        storyFileField = new JTextField();
        panel.add(storyFileField);
        JButton browseStoryBtn = new JButton("Parcourir...");
        browseStoryBtn.addActionListener(e -> chooseFile(storyFileField));
        panel.add(browseStoryBtn);

        panel.add(new JLabel("Mode de choix :"));

        JPanel choicePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        manualChoiceRadio = new JRadioButton("Manuel", true);
        autoDiceRadio = new JRadioButton("Auto-dé");
        ButtonGroup group = new ButtonGroup();
        group.add(manualChoiceRadio);
        group.add(autoDiceRadio);
        choicePanel.add(manualChoiceRadio);
        choicePanel.add(autoDiceRadio);

        panel.add(choicePanel);
        panel.add(new JLabel(""));

        add(panel, BorderLayout.CENTER);

        JButton startButton = new JButton("Démarrer l'aventure");
        startButton.addActionListener(e -> {
            try {
                startGame();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Erreur : " + ex.getMessage());
                throw new RuntimeException(ex);
            }
        });
        add(startButton, BorderLayout.SOUTH);
    }

    private void chooseFile(JTextField targetField) {
        JFileChooser chooser = new JFileChooser();
        int result = chooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            targetField.setText(file.getAbsolutePath());
        }
    }

    private void startGame() throws Exception {
        String playerFile = playerFileField.getText().trim();
        String storyFile = storyFileField.getText().trim();

        if (gameOptionHandler != null) {
            GameOption gameOption = new GameOption(playerFile, storyFile, autoDiceRadio.isSelected());
            gameOptionHandler.onOptionSelected(gameOption);
        }
    }

    public void setGameOptionHandler(IGameOptionHandler gameOptionHandler) {
        this.gameOptionHandler = gameOptionHandler;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GameLauncherUI().setVisible(true));
    }
}
