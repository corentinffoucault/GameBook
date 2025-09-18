package com.reader.adventure.story.condition;

import com.reader.adventure.game.ComparatorKey;
import com.reader.adventure.story.model.condition.Comparator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ComparatorTests {

    @ParameterizedTest
    @CsvSource({
            "10,5,LT,false",
            "10,5,LTE,false",
            "10,5,GT,true",
            "10,5,GTE,true",
            "10,5,EQ,false",
            "10,5,NEQ,true",

            "5,5,LT,false",
            "5,5,LTE,true",
            "5,5,GT,false",
            "5,5,GTE,true",
            "5,5,EQ,true",
            "5,5,NEQ,false",

            "5,10,LT,true",
            "5,10,LTE,true",
            "5,10,GT,false",
            "5,10,GTE,false",
            "5,10,EQ,false",
            "5,10,NEQ,true",
    })
    void evaluate_gold_condition(int firstValue, int secondValue, ComparatorKey comparator, boolean expectedResult) {
        boolean result = Comparator.compare(firstValue, comparator, secondValue);
        assertEquals(result, expectedResult);
    }
}
