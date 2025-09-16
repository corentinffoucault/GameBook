package com.reader.adventure.story.choice.visitor;

import com.reader.adventure.player.model.Player;
import com.reader.adventure.story.dao.Jackson.choice.ChoiceConditionalJackson;
import com.reader.adventure.story.dao.Jackson.choice.ChoiceDirectJackson;
import com.reader.adventure.story.model.choice.SelectedChoice;
import com.reader.adventure.story.model.choice.visitor.ChoiceVisitor;
import com.reader.adventure.story.dao.Jackson.condition.ConditionGoldJackson;
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
        ChoiceDirectJackson choiceDirect = new ChoiceDirectJackson(
                "choice1",
                "Text of node",
                "NextNode"
        );
        Player player = new Player();

        SelectedChoice expectedSelectedChoice = new SelectedChoice(choiceDirect.text(), choiceDirect.next());
        SelectedChoice result = choiceVisitor.applyChoice(choiceDirect, player);
        assertEquals(result, expectedSelectedChoice);
    }

    @Test
    void apply_choice_conditional_success() {
        ConditionGoldJackson conditionGoldJackson = new ConditionGoldJackson(100, "<");
        Player player = new Player();

        when(mockedConditionVisitor.evaluate(conditionGoldJackson, player)).thenReturn(true);

        ChoiceConditionalJackson choiceConditional = new ChoiceConditionalJackson("choice1",
                "Text of node",
                "NextSuccessNode",
                "NextFailNode",
                "success",
                "Fail",
                conditionGoldJackson);

        String expectedText = choiceConditional.text() + '\n' + choiceConditional.success();

        SelectedChoice expectedSelectedChoice = new SelectedChoice(expectedText, choiceConditional.next());
        SelectedChoice result = choiceVisitor.applyChoice(choiceConditional, player);
        assertEquals(result, expectedSelectedChoice);
    }

    @Test
    void apply_choice_conditional_fail() {
        ConditionGoldJackson conditionGoldJackson = new ConditionGoldJackson(100, "<");
        Player player = new Player();

        when(mockedConditionVisitor.evaluate(conditionGoldJackson, player)).thenReturn(false);

        ChoiceConditionalJackson choiceConditional = new ChoiceConditionalJackson("choice1",
                "Text of node",
                "NextSuccessNode",
                "NextFailNode",
                "success",
                "Fail",
                conditionGoldJackson);

        String expectedText = choiceConditional.text() + '\n' + choiceConditional.fail();

        SelectedChoice expectedSelectedChoice = new SelectedChoice(expectedText, choiceConditional.nextFail());
        SelectedChoice result = choiceVisitor.applyChoice(choiceConditional, player);
        assertEquals(result, expectedSelectedChoice);
    }
}
