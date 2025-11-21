package com.reader.adventure.ui.gameHandler;

import com.reader.adventure.ui.GameLauncherUI;
import com.reader.adventure.ui.GameOption;

public interface IGameModeHandler {
    void handle(GameOption gameOption, GameLauncherUI launcherUI) throws Exception;
}
