package com.reader.adventure.ui.gameHandler;

import com.reader.adventure.game.GameBook;
import com.reader.adventure.story.read.dao.IStoryDao;
import com.reader.adventure.story.read.model.choice.visitor.ChoiceVisitor;
import com.reader.adventure.ui.GameLauncherUI;
import com.reader.adventure.ui.GameOption;
import com.reader.adventure.ui.player.FileLoader;
import com.reader.adventure.ui.player.story.AUIPlayer;
import com.reader.adventure.ui.player.story.UIPlayerJFrame;
import com.reader.adventure.ui.player.story.choice.AChoicePanel;
import com.reader.adventure.ui.player.story.choice.ChoiceManualPanel;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("manual")
public class ManualGameModeHandler implements IGameModeHandler {
    private final ChoiceVisitor choiceVisitor;
    private final IStoryDao storyDao;
    private final GameBook gameBook;

    public ManualGameModeHandler(GameBook gameBook, ChoiceVisitor choiceVisitor, IStoryDao storyDao) {
        this.choiceVisitor = choiceVisitor;
        this.storyDao = storyDao;
        this.gameBook = gameBook;
    }

    public void handle(GameOption gameOption, GameLauncherUI launcherUI) throws Exception {
        storyDao.loadNodes(FileLoader.loadFile(gameOption.storyFile(), "/nodes.json"));
        AChoicePanel choicesPanel = new ChoiceManualPanel(choiceVisitor);
        AUIPlayer playerUI = new UIPlayerJFrame(gameBook, choicesPanel);
        playerUI.startGame(gameBook.getFirstNodeId());
    }
}

