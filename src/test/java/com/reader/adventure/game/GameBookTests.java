package com.reader.adventure.game;

import com.reader.adventure.story.read.dao.IStoryDao;
import com.reader.adventure.story.read.model.node.INode;
import com.reader.adventure.story.read.model.node.Node;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

import org.mockito.Mockito;
import static org.mockito.Mockito.*;

class GameBookTests {

    private static IStoryDao mockedStoryDao;
    private static GameBook gameBook;

    @BeforeAll
    static void initAll() {
        mockedStoryDao = Mockito.mock(IStoryDao.class);

        gameBook = new GameBook(mockedStoryDao);
    }

    @BeforeEach
    void init() {
        Mockito.reset(mockedStoryDao);
    }

    @Test
    void getNodeById_existingId_returnsNode() {
        Node expectedNode = new Node();
        expectedNode.setId("1");
        expectedNode.setText("A test node");
        when(mockedStoryDao.getNodeById("1")).thenReturn(expectedNode);

        INode result = gameBook.getNodeById("1");

        assertNotNull(result);
        assertEquals("1", result.getId());
        assertEquals("A test node", result.getText());
        verify(mockedStoryDao).getNodeById("1");
    }

    @Test
    void getNodeById_invalidId_Throw() {
        when(mockedStoryDao.getNodeById("999")).thenReturn(null);

        Exception exception = assertThrows(RuntimeException.class, () -> gameBook.getNodeById("999"));

        assertEquals("Node with id 999 not found", exception.getMessage());
        verify(mockedStoryDao).getNodeById("999");
    }
}
