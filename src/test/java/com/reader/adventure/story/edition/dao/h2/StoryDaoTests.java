package com.reader.adventure.story.edition.dao.h2;

import com.reader.adventure.story.edition.model.story.IStory;
import com.reader.adventure.ui.player.FileLoader;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        assertEquals("Arrivé", storyDao.getNodeById("Noeud 1").getTitle());
    }
}
