package com.reader.adventure.story.choice.visitor;

import com.reader.adventure.adventurer.model.Adventurer;
import com.reader.adventure.game.ComparatorKey;
import com.reader.adventure.story.model.choice.ChoiceConditional;
import com.reader.adventure.story.model.choice.ChoiceDirect;
import com.reader.adventure.story.model.choice.SelectedChoice;
import com.reader.adventure.story.model.choice.visitor.ChoiceVisitor;
import com.reader.adventure.story.model.condition.ConditionGold;
import com.reader.adventure.story.model.condition.visitor.IConditionVisitor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ChoiceVisitorTests {

    private static ChoiceVisitor choiceVisitor;
    private static IConditionVisitor mockedConditionVisitor;

    @BeforeAll
    static void initAll() {
        mockedConditionVisitor = Mockito.mock(IConditionVisitor.class);
        choiceVisitor = new ChoiceVisitor(mockedConditionVisitor);
    }

    @BeforeEach
    void init() {
        Mockito.reset(mockedConditionVisitor);
    }

    @Test
    void apply_choice_direct() {
        ChoiceDirect choiceDirect = new ChoiceDirect(
                "choice1",
                "Text of node",
                "NextNode"
        );
        Adventurer adventurer = new Adventurer();

        SelectedChoice expectedSelectedChoice = new SelectedChoice(choiceDirect.text(), choiceDirect.next());
        SelectedChoice result = choiceVisitor.applyChoice(choiceDirect, adventurer);
        assertEquals(result, expectedSelectedChoice);
    }

    @Test
    void apply_choice_conditional_success() {
        ConditionGold conditionGold = new ConditionGold(100, ComparatorKey.LT);
        Adventurer adventurer = new Adventurer();

        when(mockedConditionVisitor.evaluate(conditionGold, adventurer)).thenReturn(true);

        ChoiceConditional choiceConditional = new ChoiceConditional("choice1",
                "Text of node",
                "NextSuccessNode",
                "NextFailNode",
                "success",
                "Fail",
                conditionGold);

        String expectedText = choiceConditional.text() + '\n' + choiceConditional.success();

        SelectedChoice expectedSelectedChoice = new SelectedChoice(expectedText, choiceConditional.next());
        SelectedChoice result = choiceVisitor.applyChoice(choiceConditional, adventurer);
        assertEquals(result, expectedSelectedChoice);
    }

    @Test
    void apply_choice_conditional_fail() {
        ConditionGold conditionGold = new ConditionGold(100, ComparatorKey.LT);
        Adventurer adventurer = new Adventurer();

        when(mockedConditionVisitor.evaluate(conditionGold, adventurer)).thenReturn(false);

        ChoiceConditional choiceConditional = new ChoiceConditional("choice1",
                "Text of node",
                "NextSuccessNode",
                "NextFailNode",
                "success",
                "Fail",
                conditionGold);

        String expectedText = choiceConditional.text() + '\n' + choiceConditional.fail();

        SelectedChoice expectedSelectedChoice = new SelectedChoice(expectedText, choiceConditional.nextFail());
        SelectedChoice result = choiceVisitor.applyChoice(choiceConditional, adventurer);
        assertEquals(result, expectedSelectedChoice);
    }
}
