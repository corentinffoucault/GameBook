package com.reader.adventure.game.dice;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.random.RandomGenerator;

@Component
public class Dice20 extends ADice {
    public Dice20(@Qualifier("auto")RandomGenerator randomGenerator) {
        super(randomGenerator, 20);
    }
}
