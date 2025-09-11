package com.reader.adventure;

import com.reader.adventure.player.dao.IPlayerDao;
import com.reader.adventure.player.dao.PlayerJsonDao;
import com.reader.adventure.story.dao.IStoryDao;
import com.reader.adventure.story.dao.StoryJsonDao;
import com.reader.adventure.story.model.choice.visitor.ChoiceVisitor;
import com.reader.adventure.story.model.condition.visitor.ConditionVisitor;
import com.reader.adventure.game.GameBook;
import com.reader.adventure.ui.player.AUIPlayer;
import com.reader.adventure.ui.player.UIPlayerJFrame;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;

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
        IStoryDao storyDao = new StoryJsonDao();
        IPlayerDao playerDao = new PlayerJsonDao();
        ConditionVisitor conditionVisitor = new ConditionVisitor();
        ChoiceVisitor choiceVisitor = new ChoiceVisitor(conditionVisitor);
        GameBook gameBook = new GameBook(storyDao, playerDao, choiceVisitor);
        AUIPlayer playerUI = new UIPlayerJFrame(gameBook);
        playerUI.startGame("Noeud 1");
    }
}