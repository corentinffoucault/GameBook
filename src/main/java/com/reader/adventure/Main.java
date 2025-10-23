package com.reader.adventure;

import com.reader.adventure.game.GameTypeKey;
import com.reader.adventure.game.dice.Dice20;
import com.reader.adventure.adventurer.dao.IAdventurerDao;
import com.reader.adventure.adventurer.dao.jackson.AdventurerJsonDaoJackson;
import com.reader.adventure.story.dao.IStoryDao;
import com.reader.adventure.story.dao.Jackson.StoryJsonDaoJackson;
import com.reader.adventure.story.model.choice.visitor.ChoiceVisitor;
import com.reader.adventure.story.model.condition.visitor.ConditionVisitor;
import com.reader.adventure.game.GameBook;
import com.reader.adventure.ui.GameLauncherUI;
import com.reader.adventure.ui.export.ExportUI;
import com.reader.adventure.ui.player.story.AUIPlayer;
import com.reader.adventure.ui.player.adventurer.AdventurerForm;
import com.reader.adventure.ui.player.adventurer.AdventurerSheet;
import com.reader.adventure.ui.player.FileLoader;
import com.reader.adventure.ui.player.story.UIPlayerJFrame;
import com.reader.adventure.ui.player.story.choice.AChoicePanel;
import com.reader.adventure.ui.player.story.choice.ChoiceManualPanel;
import com.reader.adventure.ui.player.story.choice.ChoiceWithAutoDicePanel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Main().start();
        });
    }

    private void start() {
        try {
            GameLauncherUI gameLauncherUI = new GameLauncherUI();
            IStoryDao storyDao = new StoryJsonDaoJackson();
            GameBook gameBook = new GameBook(storyDao);

            Dice20 dice = new Dice20(ThreadLocalRandom.current());
            ConditionVisitor conditionVisitor = new ConditionVisitor(dice);
            ChoiceVisitor choiceVisitor = new ChoiceVisitor(conditionVisitor);

            IAdventurerDao adventurerDao = new AdventurerJsonDaoJackson();

            gameLauncherUI.setGameOptionHandler((gameOption) -> {
                AChoicePanel choicesPanel;
                storyDao.loadNodes(FileLoader.loadFile(gameOption.storyFile(), "/nodes.json"));

                if (gameOption.GameType() == GameTypeKey.AUTO) {
                    adventurerDao.loadAdventurer(FileLoader.loadFile(gameOption.adventurerFile(), "/Adventurer.json"));

                    choicesPanel = new ChoiceWithAutoDicePanel(choiceVisitor, adventurerDao);
                    AUIPlayer playerUI = new UIPlayerJFrame(gameBook, choicesPanel);
                    AdventurerSheet adventurerSheet = new AdventurerSheet(adventurerDao);

                    AdventurerForm adventurerForm = new AdventurerForm(adventurerDao);
                    adventurerForm.setAdventurerHandler(() -> {
                        adventurerSheet.createUi();
                        adventurerSheet.setVisible(true);
                        playerUI.startGame(gameBook.getFirstNodeId());
                    });
                    adventurerForm.setVisible(true);

                } else if (gameOption.GameType() == GameTypeKey.MANUAL) {
                    choicesPanel = new ChoiceManualPanel(choiceVisitor);
                    AUIPlayer playerUI = new UIPlayerJFrame(gameBook, choicesPanel);
                    playerUI.startGame(gameBook.getFirstNodeId());
                } else if (gameOption.GameType() == GameTypeKey.EXPORT) {
                    ExportUI exportUI = new ExportUI(storyDao);
                    exportUI.setExporterHandler(Main::closeGame);
                    exportUI.run(gameLauncherUI);
                }

            });
            gameLauncherUI.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erreur : " + e.getMessage());
        }
    }

    private static void closeGame() {
        int result = JOptionPane.showConfirmDialog(
                null,
                "Voulez-vous vraiment quitter l'application ?",
                "Confirmation de fermeture",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
        );

        if (result == JOptionPane.YES_OPTION) {
            System.out.println("Fermeture de l'application...");
            System.exit(0);
        } else {
            System.out.println("Fermeture annulée par l'utilisateur.");
        }
    }
}