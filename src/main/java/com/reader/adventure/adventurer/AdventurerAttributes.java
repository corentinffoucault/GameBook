package com.reader.adventure.adventurer;

import com.reader.adventure.adventurer.model.Adventurer;
import com.reader.adventure.game.AttributeKey;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.Function;

public class AdventurerAttributes {

    private static final Map<AttributeKey, Function<Adventurer, Integer>> OPERATORS = new EnumMap<>(AttributeKey.class);

    static {
        OPERATORS.put(AttributeKey.AG, Adventurer::getAgility);
        OPERATORS.put(AttributeKey.FO, Adventurer::getStrength);
        OPERATORS.put(AttributeKey.INT, Adventurer::getIntelligence);
        OPERATORS.put(AttributeKey.CHA, Adventurer::getCharisma);
        OPERATORS.put(AttributeKey.COU, Adventurer::getCourage);
        OPERATORS.put(AttributeKey.ATT, Adventurer::getAttack);
        OPERATORS.put(AttributeKey.PAR, Adventurer::getParry);
        OPERATORS.put(AttributeKey.JET, Adventurer::getThrowing);
        OPERATORS.put(AttributeKey.ESQ, Adventurer::getDodge);
        OPERATORS.put(AttributeKey.FOU, Adventurer::getSearch);
        OPERATORS.put(AttributeKey.ING, Adventurer::getInventiveness);
        OPERATORS.put(AttributeKey.MAG_RES, Adventurer::getMagic_resistance);
        OPERATORS.put(AttributeKey.MAG_PHY, Adventurer::getPhysical_magic);
        OPERATORS.put(AttributeKey.MAG_PSY, Adventurer::getPsychic_magic);
    }

    public static int getValue(Adventurer adventurer, AttributeKey key) {
        Function<Adventurer, Integer> extractor = OPERATORS.get(key);
        if (extractor == null) throw new IllegalArgumentException("Aucun attribut pour la clé: " + key);
        return extractor.apply(adventurer);
    }
}
