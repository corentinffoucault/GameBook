package com.reader.adventure.player;

import com.reader.adventure.player.model.Player;
import com.reader.adventure.game.AttributeKey;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.Function;

public class PlayerAttributes {

    private static final Map<AttributeKey, Function<Player, Integer>> OPERATORS = new EnumMap<>(AttributeKey.class);

    static {
        OPERATORS.put(AttributeKey.AG, Player::getAgility);
        OPERATORS.put(AttributeKey.FO, Player::getStrength);
        OPERATORS.put(AttributeKey.INT, Player::getIntelligence);
        OPERATORS.put(AttributeKey.CHA, Player::getCharisma);
        OPERATORS.put(AttributeKey.COU, Player::getCourage);
        OPERATORS.put(AttributeKey.ATT, Player::getAttack);
        OPERATORS.put(AttributeKey.PAR, Player::getParry);
        OPERATORS.put(AttributeKey.JET, Player::getThrowing);
        OPERATORS.put(AttributeKey.ESQ, Player::getDodge);
        OPERATORS.put(AttributeKey.FOU, Player::getSearch);
        OPERATORS.put(AttributeKey.ING, Player::getInventiveness);
        OPERATORS.put(AttributeKey.MAG_RES, Player::getMagic_resistance);
        OPERATORS.put(AttributeKey.MAG_PHY, Player::getPhysical_magic);
        OPERATORS.put(AttributeKey.MAG_PSY, Player::getPsychic_magic);
    }

    public static int getValue(Player player, AttributeKey key) {
        System.out.println(key);
        Function<Player, Integer> extractor = OPERATORS.get(key);
        if (extractor == null) throw new IllegalArgumentException("Aucun attribut pour la clé: " + key);
        return extractor.apply(player);
    }
}
