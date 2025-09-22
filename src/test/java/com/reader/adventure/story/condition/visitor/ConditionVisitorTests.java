package com.reader.adventure.story.condition.visitor;

import com.reader.adventure.adventurer.model.Adventurer;
import com.reader.adventure.game.AttributeKey;
import com.reader.adventure.game.ComparatorKey;
import com.reader.adventure.game.dice.Dice20;
import com.reader.adventure.story.model.condition.ConditionAttributes;
import com.reader.adventure.story.model.condition.ConditionGold;
import com.reader.adventure.story.model.condition.visitor.ConditionVisitor;
import com.reader.adventure.story.model.condition.visitor.IConditionVisitor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class ConditionVisitorTests {

    private static Dice20 mockedDice20;
    private static IConditionVisitor conditionVisitor;

    @BeforeAll
    static void initAll() {
        mockedDice20 = Mockito.mock(Dice20.class);
        conditionVisitor = new ConditionVisitor(mockedDice20);
    }

    @BeforeEach
    void init() {
        Mockito.reset(mockedDice20);
    }

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
    void evaluate_gold_condition(int playerGold, int goldLimit, ComparatorKey comparator, boolean expectedResult) {
        ConditionGold conditionGold = new ConditionGold(goldLimit, comparator);
        Adventurer adventurer = new Adventurer();
        adventurer.setGold(playerGold);

        boolean result = conditionVisitor.evaluate(conditionGold, adventurer);
        assertEquals(result, expectedResult);
    }

    @ParameterizedTest
    @CsvSource({
            "10,5,LT,true",
            "10,5,LTE,true",
            "10,5,GT,false",
            "10,5,GTE,false",
            "10,5,EQ,false",
            "10,5,NEQ,true",

            "5,5,LT,false",
            "5,5,LTE,true",
            "5,5,GT,false",
            "5,5,GTE,true",
            "5,5,EQ,true",
            "5,5,NEQ,false",

            "5,10,LT,false",
            "5,10,LTE,false",
            "5,10,GT,true",
            "5,10,GTE,true",
            "5,10,EQ,false",
            "5,10,NEQ,true",
    })
    void evaluate_agility_condition(int playerAgility, int agilityResult, ComparatorKey comparator, boolean expectedResult) {

        when(mockedDice20.roll()).thenReturn(agilityResult);

        ConditionAttributes conditionAgility = new ConditionAttributes(AttributeKey.AG, comparator);

        Adventurer adventurer = new Adventurer();
        adventurer.setAgility(playerAgility);

        boolean result = conditionVisitor.evaluate(conditionAgility, adventurer);
        assertEquals(result, expectedResult);
    }
}
