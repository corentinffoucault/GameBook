package com.reader.adventure.game.dice;

import com.reader.adventure.game.GameBook;
import com.reader.adventure.player.dao.IPlayerDao;
import com.reader.adventure.story.dao.IStoryDao;
import com.reader.adventure.story.model.choice.visitor.ChoiceVisitor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Dice20Tests {
    static ADice dice20;

    @BeforeAll
    static void initAll() {
        dice20 = new Dice20(ThreadLocalRandom.current());
    }

    @RepeatedTest(1000)
    void assertFaceBetween1_20() {
        int result = dice20.roll();
        assertTrue(result >= 1 && result <= 20, "Result should be in [1,20]");
    }

    @Test
    void assertAllFaceAppear() {
        Set<Integer> seen = new HashSet<>();

        for (int i = 0; i < 10000; i++) {
            seen.add(dice20.roll());
        }

        assertEquals(20, seen.size(), "All 20 faces should appear after enough rolls");
    }
}
