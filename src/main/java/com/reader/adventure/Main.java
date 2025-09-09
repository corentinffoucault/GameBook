package com.reader.adventure;

import com.reader.adventure.story.dao.IStoryDao;
import com.reader.adventure.story.dao.StoryGsonDao;
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
        IStoryDao storyDao = new StoryGsonDao();
        AUIPlayer playerUI = new UIPlayerJFrame(storyDao);
        playerUI.startGame("Noeud 1");
    }
}