package com.reader.adventure.story.condition;

import com.reader.adventure.story.model.condition.Comparator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ComparatorTests {

    @ParameterizedTest
    @CsvSource({
            "10,5,<,false",
            "10,5,<=,false",
            "10,5,>,true",
            "10,5,>=,true",
            "10,5,==,false",
            "10,5,!=,true",

            "5,5,<,false",
            "5,5,<=,true",
            "5,5,>,false",
            "5,5,>=,true",
            "5,5,==,true",
            "5,5,!=,false",

            "5,10,<,true",
            "5,10,<=,true",
            "5,10,>,false",
            "5,10,>=,false",
            "5,10,==,false",
            "5,10,!=,true",
    })
    void evaluate_gold_condition(int firstValue, int secondValue, String comparator, boolean expectedResult) {
        boolean result = Comparator.compare(firstValue, comparator, secondValue);
        assertEquals(result, expectedResult);
    }
}
