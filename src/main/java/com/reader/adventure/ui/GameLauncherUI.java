package com.reader.adventure.ui;

import com.reader.adventure.game.GameTypeKey;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class GameLauncherUI extends JFrame {
    private final JTextField playerFileField;
    private final JTextField storyFileField;
    private final JRadioButton manualChoiceRadio;
    private final JRadioButton autoDiceRadio;
    private final JRadioButton exportRadio;
    private IGameOptionHandler gameOptionHandler;
    private GameTypeKey gameTypeKey;

    public GameLauncherUI() {
        setTitle("Configuration du jeu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(550, 260);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel playerPanel = new JPanel();
        playerPanel.setLayout(new BoxLayout(playerPanel, BoxLayout.X_AXIS));
        playerPanel.add(new JLabel("Fichier joueur : "));
        playerFileField = new JTextField();
        Dimension fieldSize = new Dimension(300, playerFileField.getPreferredSize().height);
        playerFileField.setPreferredSize(fieldSize);
        playerFileField.setMaximumSize(fieldSize);
        playerPanel.add(playerFileField);
        JButton browsePlayerBtn = new JButton("Parcourir...");
        browsePlayerBtn.addActionListener(e -> chooseFile(playerFileField));
        playerPanel.add(Box.createHorizontalStrut(5));
        playerPanel.add(browsePlayerBtn);

        JPanel storyPanel = new JPanel();
        storyPanel.setLayout(new BoxLayout(storyPanel, BoxLayout.X_AXIS));
        storyPanel.add(new JLabel("Fichier histoire : "));
        storyFileField = new JTextField();
        storyFileField.setPreferredSize(fieldSize);
        storyFileField.setMaximumSize(fieldSize);
        storyPanel.add(storyFileField);
        JButton browseStoryBtn = new JButton("Parcourir...");
        browseStoryBtn.addActionListener(e -> chooseFile(storyFileField));
        storyPanel.add(Box.createHorizontalStrut(5));
        storyPanel.add(browseStoryBtn);

        JPanel choicePanel = new JPanel();
        choicePanel.setLayout(new BoxLayout(choicePanel, BoxLayout.X_AXIS));
        choicePanel.add(new JLabel("Mode de choix : "));

        manualChoiceRadio = new JRadioButton("Manuel");
        autoDiceRadio = new JRadioButton("Auto-dé", true);
        gameTypeKey = GameTypeKey.AUTO;
        exportRadio = new JRadioButton("Export");

        autoDiceRadio.addChangeListener(e -> {
            playerPanel.setVisible(autoDiceRadio.isSelected());
            playerPanel.revalidate();
            playerPanel.repaint();
        });
        autoDiceRadio.addActionListener(e -> {
            gameTypeKey = GameTypeKey.AUTO;
        });
        manualChoiceRadio.addActionListener(e -> {
            gameTypeKey = GameTypeKey.MANUAL;
        });
        exportRadio.addActionListener(e -> {
            gameTypeKey = GameTypeKey.EXPORT;
        });

        ButtonGroup group = new ButtonGroup();
        group.add(manualChoiceRadio);
        group.add(autoDiceRadio);
        group.add(exportRadio);
        choicePanel.add(Box.createHorizontalStrut(5));
        choicePanel.add(manualChoiceRadio);
        choicePanel.add(Box.createHorizontalStrut(10));
        choicePanel.add(autoDiceRadio);
        choicePanel.add(Box.createHorizontalStrut(15));
        choicePanel.add(exportRadio);

        mainPanel.add(playerPanel);
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(storyPanel);
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(choicePanel);

        JButton startButton = new JButton("Go");
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        startButton.addActionListener(e -> {
            try {
                startGame();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erreur : " + ex.getMessage());
                throw new RuntimeException(ex);
            }
        });

        getContentPane().add(mainPanel, BorderLayout.CENTER);
        getContentPane().add(startButton, BorderLayout.SOUTH);
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
            GameOption gameOption = new GameOption(playerFile, storyFile, gameTypeKey);
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
