package com.reader.adventure;

import com.reader.adventure.game.GameTypeKey;
import com.reader.adventure.game.dice.Dice20;
import com.reader.adventure.adventurer.dao.IAdventurerDao;
import com.reader.adventure.adventurer.dao.jackson.AdventurerJsonDaoJackson;
import com.reader.adventure.story.dao.IStoryDao;
import com.reader.adventure.story.dao.Jackson.StoryJsonDaoJackson;
import com.reader.adventure.story.export.odt.ExporterOdt;
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
            Dice20 dice = new Dice20(ThreadLocalRandom.current());

            GameLauncherUI gameLauncherUI = new GameLauncherUI();

            gameLauncherUI.setGameOptionHandler((gameOption) -> {

                IAdventurerDao adventurerDao;
                AChoicePanel choicesPanel;

                IStoryDao storyDao = new StoryJsonDaoJackson(FileLoader.loadFile(gameOption.storyFile(), "/nodes.json"));
                GameBook gameBook = new GameBook(storyDao);

                ConditionVisitor conditionVisitor = new ConditionVisitor(dice);
                ChoiceVisitor choiceVisitor = new ChoiceVisitor(conditionVisitor);

                if (gameOption.GameType() == GameTypeKey.AUTO) {
                    adventurerDao = new AdventurerJsonDaoJackson(FileLoader.loadFile(gameOption.adventurerFile(), "/Adventurer.json"));
                    choicesPanel = new ChoiceWithAutoDicePanel(choiceVisitor, adventurerDao);
                    AdventurerSheet adventurerSheet = new AdventurerSheet(adventurerDao);

                    AUIPlayer playerUI = new UIPlayerJFrame(gameBook, choicesPanel);
                    AdventurerForm adventurerForm = new AdventurerForm();
                    adventurerForm.setAdventurerHandler((adventurer) -> {
                        adventurerDao.saveAdventurer(adventurer);
                        adventurerSheet.createUi();
                        adventurerSheet.setVisible(true);
                        playerUI.startGame("Noeud 1");
                    });
                    adventurerForm.setVisible(true);

                } else if (gameOption.GameType() == GameTypeKey.MANUAL) {
                    choicesPanel = new ChoiceManualPanel(choiceVisitor);
                    AUIPlayer playerUI = new UIPlayerJFrame(gameBook, choicesPanel);
                    playerUI.startGame("Noeud 1");
                } else if (gameOption.GameType() == GameTypeKey.EXPORT) {
                    ExportUI exportUI = new ExportUI(storyDao);
                    exportUI.setExporterHandler(() -> {
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
                    });
                    exportUI.run(gameLauncherUI);
                }

            });
            gameLauncherUI.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erreur : " + e.getMessage());
        }
    }
}