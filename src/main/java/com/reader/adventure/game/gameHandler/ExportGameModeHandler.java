package com.reader.adventure.game.gameHandler;

import com.reader.adventure.Main;
import com.reader.adventure.story.read.dao.IStoryDao;
import com.reader.adventure.ui.GameLauncherUI;
import com.reader.adventure.ui.GameOption;
import com.reader.adventure.ui.export.ExportUI;
import com.reader.adventure.ui.player.FileLoader;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("export")
public class ExportGameModeHandler implements IGameModeHandler {
    private final IStoryDao storyDao;

    public ExportGameModeHandler(IStoryDao storyDao) {
        this.storyDao = storyDao;
    }

    public void handle(GameOption gameOption, GameLauncherUI gameLauncherUI) throws Exception {
        storyDao.loadNodes(FileLoader.loadFile(gameOption.storyFile(), "/nodes.json"));
        ExportUI exportUI = new ExportUI(storyDao);
        exportUI.setExporterHandler(Main::closeGame);
        exportUI.run(gameLauncherUI);
    }
}

