package com.reader.adventure;

import com.reader.adventure.game.dice.Dice20;
import com.reader.adventure.player.dao.IPlayerDao;
import com.reader.adventure.player.dao.jackson.PlayerJsonDaoJackson;
import com.reader.adventure.story.dao.IStoryDao;
import com.reader.adventure.story.dao.Jackson.StoryJsonDaoJackson;
import com.reader.adventure.story.model.choice.visitor.ChoiceVisitor;
import com.reader.adventure.story.model.condition.visitor.ConditionVisitor;
import com.reader.adventure.game.GameBook;
import com.reader.adventure.ui.player.AUIPlayer;
import com.reader.adventure.ui.player.Adventurer.AdventurerForm;
import com.reader.adventure.ui.player.Adventurer.AdventurerSheet;
import com.reader.adventure.ui.player.FileLoader;
import com.reader.adventure.ui.player.UIPlayerJFrame;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                new Main().start();
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
        });
    }

    private void start() throws Exception {
        try {
            Dice20 dice = new Dice20(ThreadLocalRandom.current());
            IStoryDao storyDao = new StoryJsonDaoJackson(FileLoader.loadFile());
            IPlayerDao playerDao = new PlayerJsonDaoJackson();
            ConditionVisitor conditionVisitor = new ConditionVisitor(dice);
            ChoiceVisitor choiceVisitor = new ChoiceVisitor(conditionVisitor);
            GameBook gameBook = new GameBook(storyDao, playerDao, choiceVisitor);

            AdventurerSheet adventurerSheet = new AdventurerSheet(playerDao);
            AUIPlayer playerUI = new UIPlayerJFrame(gameBook, adventurerSheet);
            AdventurerForm adventurerForm = new AdventurerForm(playerUI, playerDao);
            adventurerForm.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erreur : " + e.getMessage());
        }
    }
}