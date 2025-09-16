package com.reader.adventure.story.model.condition.visitor;

import com.reader.adventure.game.dice.Dice20;
import com.reader.adventure.player.model.Player;
import com.reader.adventure.story.model.condition.Comparator;
import com.reader.adventure.story.model.condition.ConditionAttributes;
import com.reader.adventure.story.model.condition.ConditionGold;

import java.util.function.Function;

public class ConditionVisitor implements IConditionVisitor {

    public enum Attributes {
        AG(Player::getAgility),
        FO(Player::getStrength),
        INT(Player::getIntelligence),
        CHA(Player::getCharisma),
        COU(Player::getCourage),
        ATT(Player::getAttack),
        PAR(Player::getParry),
        JET(Player::getThrowing),
        ESQ(Player::getDodge),
        FOU(Player::getSearch),
        ING(Player::getInventiveness),
        MAG_RES(Player::getMagic_resistance),
        MAG_PHY(Player::getPhysical_magic),
        MAG_PSY(Player::getPsychic_magic);

        private final Function<Player, Integer> extractor;

        Attributes(Function<Player, Integer> extractor) {
            this.extractor = extractor;
        }

        public int getValue(Player player) {
            return extractor.apply(player);
        }
    }

    private final Dice20 dice;

    public ConditionVisitor(Dice20 dice) {
        this.dice = dice;
    }

    @Override
    public boolean evaluate(ConditionAttributes condition, Player player) {
        int randomValue = dice.roll();
        int carac = Attributes.valueOf(condition.getType()).getValue(player);
        return Comparator.compare(randomValue, condition.getComparator(), carac);
    }

    @Override
    public boolean evaluate(ConditionGold condition, Player player) {
        return Comparator.compare(player.getGold(), condition.getComparator(), condition.getValue());
    }
}