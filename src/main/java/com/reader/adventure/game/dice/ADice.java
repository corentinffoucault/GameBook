package com.reader.adventure.game.dice;

import java.util.random.RandomGenerator;

public abstract class ADice {
    private RandomGenerator randomGenerator;
    private Integer nbSide;

    ADice(RandomGenerator randomGenerator, Integer nbSide) {
        this.randomGenerator = randomGenerator;
        this.nbSide = nbSide;
    }

    public Integer roll() {
        return randomGenerator.nextInt(1, nbSide + 1);
    }
}
