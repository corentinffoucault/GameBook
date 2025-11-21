package com.reader.adventure.ui.gameHandler;

import com.reader.adventure.adventurer.dao.IAdventurerDao;
import com.reader.adventure.game.GameBook;
import com.reader.adventure.story.read.dao.IStoryDao;
import com.reader.adventure.story.read.model.choice.visitor.ChoiceVisitor;
import com.reader.adventure.ui.GameLauncherUI;
import com.reader.adventure.ui.GameOption;
import com.reader.adventure.ui.player.FileLoader;
import com.reader.adventure.ui.player.adventurer.AdventurerForm;
import com.reader.adventure.ui.player.adventurer.AdventurerSheet;
import com.reader.adventure.ui.player.story.AUIPlayer;
import com.reader.adventure.ui.player.story.UIPlayerJFrame;
import com.reader.adventure.ui.player.story.choice.AChoicePanel;
import com.reader.adventure.ui.player.story.choice.ChoiceWithAutoDicePanel;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("auto")
public class AutoGameModeHandler implements IGameModeHandler {
    private final IAdventurerDao adventurerDao;
    private final ChoiceVisitor choiceVisitor;
    private final IStoryDao storyDao;
    private final GameBook gameBook;

    public AutoGameModeHandler(GameBook gameBook,
                               @Qualifier("adventurerJsonDaoJackson")IAdventurerDao adventurerDao,
                               ChoiceVisitor choiceVisitor,
                               IStoryDao storyDao) {
        this.adventurerDao = adventurerDao;
        this.choiceVisitor = choiceVisitor;
        this.storyDao = storyDao;
        this.gameBook = gameBook;
    }

    public void handle(GameOption gameOption, GameLauncherUI launcherUI) throws Exception {
        storyDao.loadNodes(FileLoader.loadFile(gameOption.storyFile(), "/nodes.json"));
        adventurerDao.loadAdventurer(FileLoader.loadFile(gameOption.adventurerFile(), "/Adventurer.json"));

        AChoicePanel choicesPanel = new ChoiceWithAutoDicePanel(choiceVisitor, adventurerDao);
        AUIPlayer playerUI = new UIPlayerJFrame(gameBook, choicesPanel);
        AdventurerSheet adventurerSheet = new AdventurerSheet(adventurerDao);

        AdventurerForm adventurerForm = new AdventurerForm(adventurerDao);
        adventurerForm.setAdventurerHandler(() -> {
            adventurerSheet.createUi();
            adventurerSheet.setVisible(true);
            playerUI.startGame(gameBook.getFirstNodeId());
        });
        adventurerForm.setVisible(true);
    }
}

