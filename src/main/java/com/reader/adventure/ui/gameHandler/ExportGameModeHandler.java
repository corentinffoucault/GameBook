package com.reader.adventure.ui.gameHandler;

import com.reader.adventure.story.read.dao.IStoryDao;
import com.reader.adventure.ui.GameLauncherUI;
import com.reader.adventure.ui.GameOption;
import com.reader.adventure.ui.export.ExportUI;
import com.reader.adventure.ui.export.handler.ExportHandlerFactory;
import com.reader.adventure.ui.player.FileLoader;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.swing.*;

@Component
@Qualifier("export")
public class ExportGameModeHandler implements IGameModeHandler {
    private final IStoryDao storyDao;
    private final ExportHandlerFactory factory;

    public ExportGameModeHandler(IStoryDao storyDao, ExportHandlerFactory factory) {
        this.storyDao = storyDao;
        this.factory = factory;
    }

    public void handle(GameOption gameOption, GameLauncherUI gameLauncherUI) throws Exception {
        storyDao.loadNodes(FileLoader.loadFile(gameOption.storyFile(), "/nodes.json"));

        ExportUI exportUI = new ExportUI();
        exportUI.setExporterHandler(exportOption -> {
            try {
                factory.getHandler(exportOption.extension()).print(exportOption.fileToSave());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erreur : " + e.getMessage());
            }
        });
        exportUI.run(gameLauncherUI);
    }
}

