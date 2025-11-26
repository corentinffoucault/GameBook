package com.reader.adventure.game;

import com.reader.adventure.story.read.dao.IStoryDao;
import com.reader.adventure.story.read.model.node.INode;
import com.reader.adventure.story.read.model.node.Node;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import org.mockito.Mockito;

import java.util.ArrayList;

import static org.mockito.Mockito.*;

@SpringBootTest
class GameBookTests {
    @MockitoBean
    private IStoryDao mockedStoryDao;
    @Autowired
    private GameBook gameBook;

    @BeforeEach
    void init() {
        Mockito.reset(mockedStoryDao);
    }

    @Test
    void getNodeById_existingId_returnsNode() {
        Node expectedNode = new Node("1", "A test node", "text", new ArrayList<>());
        when(mockedStoryDao.getNodeById("1")).thenReturn(expectedNode);

        INode result = gameBook.getNodeById("1");

        assertNotNull(result);
        assertEquals("1", result.name());
        assertEquals("A test node", result.title());
        assertEquals("text", result.text());
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
