package com.reader.adventure.story.choice.visitor;

import com.reader.adventure.player.model.Player;
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
        ChoiceDirect choiceDirect = new ChoiceDirect();
        choiceDirect.setText("Text of node");
        choiceDirect.setNext("NextNode");
        Player player = new Player();

        SelectedChoice expectedSelectedChoice = new SelectedChoice(choiceDirect.getText(), choiceDirect.getNext());
        SelectedChoice result = choiceVisitor.applyChoice(choiceDirect, player);
        assertEquals(result, expectedSelectedChoice);
    }

    @Test
    void apply_choice_conditional_success() {
        ConditionGold  conditionGold = new ConditionGold();
        Player player = new Player();

        when(mockedConditionVisitor.evaluate(conditionGold, player)).thenReturn(true);

        ChoiceConditional choiceConditional = new ChoiceConditional();
        choiceConditional.setText("Text of node");
        choiceConditional.setNext("NextSuccessNode");
        choiceConditional.setSuccess("success");
        choiceConditional.setCondition(conditionGold);

        String expectedText = choiceConditional.getText() + '\n' + choiceConditional.getSuccess();

        SelectedChoice expectedSelectedChoice = new SelectedChoice(expectedText, choiceConditional.getNext());
        SelectedChoice result = choiceVisitor.applyChoice(choiceConditional, player);
        assertEquals(result, expectedSelectedChoice);
    }

    @Test
    void apply_choice_conditional_fail() {
        ConditionGold  conditionGold = new ConditionGold();
        Player player = new Player();

        when(mockedConditionVisitor.evaluate(conditionGold, player)).thenReturn(false);

        ChoiceConditional choiceConditional = new ChoiceConditional();
        choiceConditional.setText("Text of node");
        choiceConditional.setFail("Fail");
        choiceConditional.setNextFail("NextFailNode");
        choiceConditional.setCondition(conditionGold);

        String expectedText = choiceConditional.getText() + '\n' + choiceConditional.getFail();

        SelectedChoice expectedSelectedChoice = new SelectedChoice(expectedText, choiceConditional.getNextFail());
        SelectedChoice result = choiceVisitor.applyChoice(choiceConditional, player);
        assertEquals(result, expectedSelectedChoice);
    }
}
