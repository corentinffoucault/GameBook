package com.reader.adventure.story.condition.visitor;

import com.reader.adventure.game.dice.Dice20;
import com.reader.adventure.player.model.Player;
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
        mockedDice20 = Mockito.mock(Dice20.class);;
        conditionVisitor = new ConditionVisitor(mockedDice20);
    }

    @BeforeEach
    void init() {
        Mockito.reset(mockedDice20);
    }

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
    void evaluate_gold_condition(int playerGold, int goldLimit, String comparator, boolean expectedResult) {
        ConditionGold conditionGold = new ConditionGold();
        conditionGold.setValue(goldLimit);
        conditionGold.setComparator(comparator);
        Player player = new Player();
        player.setGold(playerGold);

        boolean result = conditionVisitor.evaluate(conditionGold, player);
        assertEquals(result, expectedResult);
    }

    @ParameterizedTest
    @CsvSource({
            "10,5,<,true",
            "10,5,<=,true",
            "10,5,>,false",
            "10,5,>=,false",
            "10,5,==,false",
            "10,5,!=,true",

            "5,5,<,false",
            "5,5,<=,true",
            "5,5,>,false",
            "5,5,>=,true",
            "5,5,==,true",
            "5,5,!=,false",

            "5,10,<,false",
            "5,10,<=,false",
            "5,10,>,true",
            "5,10,>=,true",
            "5,10,==,false",
            "5,10,!=,true",
    })
    void evaluate_agility_condition(int playerAgility, int agilityResult, String comparator, boolean expectedResult) {

        when(mockedDice20.roll()).thenReturn(agilityResult);

        ConditionAttributes conditionAgility = new ConditionAttributes();
        conditionAgility.setComparator(comparator);
        conditionAgility.setType("AG");

        Player player = new Player();
        player.setAgility(playerAgility);

        boolean result = conditionVisitor.evaluate(conditionAgility, player);
        assertEquals(result, expectedResult);
    }
}
