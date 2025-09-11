package com.reader.adventure.game;

import com.reader.adventure.player.dao.IPlayerDao;
import com.reader.adventure.story.dao.IStoryDao;
import com.reader.adventure.story.model.Node;
import com.reader.adventure.story.model.choice.visitor.ChoiceVisitor;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

import org.mockito.Mockito;
import static org.mockito.Mockito.*;

class GameBookTests {

    private static IStoryDao mockedStoryDao;
    private static IPlayerDao mockedPlayerDao;
    private static ChoiceVisitor mockedChoiceVisitor;
    private static GameBook gameBook;

    @BeforeAll
    static void initAll() {
        mockedStoryDao = Mockito.mock(IStoryDao.class);
        mockedPlayerDao = Mockito.mock(IPlayerDao.class);
        mockedChoiceVisitor = Mockito.mock(ChoiceVisitor.class);

        gameBook = new GameBook(mockedStoryDao, mockedPlayerDao, mockedChoiceVisitor);
    }

    @BeforeEach
    void init() {
        Mockito.reset(mockedStoryDao, mockedPlayerDao, mockedChoiceVisitor);
    }

    @Test
    void getNodeById_existingId_returnsNode() {
        Node expectedNode = new Node();
        expectedNode.setId("1");
        expectedNode.setText("A test node");
        when(mockedStoryDao.getNodeById("1")).thenReturn(expectedNode);

        Node result = gameBook.getNodeById("1");

        assertNotNull(result);
        assertEquals("1", result.getId());
        assertEquals("A test node", result.getText());
        verify(mockedStoryDao).getNodeById("1");
    }

    @Test
    void getNodeById_invalidId_Throw() {
        when(mockedStoryDao.getNodeById("999")).thenReturn(null);

        Exception exception = assertThrows(RuntimeException.class, () -> gameBook.getNodeById("999"));

        assertEquals(exception.getMessage(), "Node with id 999 not found");
        verify(mockedStoryDao).getNodeById("999");
    }
}
