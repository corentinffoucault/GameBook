package com.reader.adventure.game.dice;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class Dice20Tests {

    @Autowired
    Dice20 dice20;

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
