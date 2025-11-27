package com.reader.adventure.story.edition.dao.h2;

import com.reader.adventure.game.AttributeKey;
import com.reader.adventure.game.ComparatorKey;
import com.reader.adventure.story.edition.model.choice.ChoiceConditional;
import com.reader.adventure.story.edition.model.condition.ConditionAttributes;
import com.reader.adventure.story.edition.model.story.IStory;
import com.reader.adventure.ui.player.FileLoader;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class StoryDaoTests {

    @Autowired
    StoryDaoServiceH2 storyDao;

    @Test
    void shouldLoadJsonToH2() {

        storyDao.loadNodes(FileLoader.loadFile("", "/nodes.json"));
        IStory story = storyDao.getStory();

        assertEquals("Author", story.getAuthor());
        assertEquals("Noeud 1", storyDao.getFirstNodeId());
        assertEquals("Arrivé", storyDao.getNodeById(storyDao.getFirstNodeId()).getTitle());
        assertEquals(4, storyDao.getNodeById("Noeud 1").getChoice().size());
        assertEquals("Vous avez trop envie de vous mettre au chaud à la taverne et manger une bonne soupe chaude avec un morceau de boudin. Vous dirigez donc vos pas vers le nord en empruntant ***", storyDao.getNodeById("Noeud 1").getChoice().get(0).getText());
        assertEquals("Noeud 1.1", storyDao.getNodeById("Noeud 1").getChoice().get(0).getNext());

        assertInstanceOf(ChoiceConditional.class, storyDao.getNodeById("Noeud 1").getChoice().get(2));
        ChoiceConditional choice = (ChoiceConditional) storyDao.getNodeById("Noeud 1").getChoice().get(2);
        assertInstanceOf(ConditionAttributes.class, choice.getCondition());

        ConditionAttributes condition = ((ConditionAttributes) choice.getCondition());
        assertEquals(ComparatorKey.LT, condition.getComparator());
        assertEquals(AttributeKey.AG,condition.getAttribute());

    }
}
