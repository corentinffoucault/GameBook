package com.reader.adventure.game.dice;

import java.util.concurrent.ThreadLocalRandom;
import java.util.random.RandomGenerator;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Qualifier;

@Configuration
public class RandomConfig {

    @Bean
    @Qualifier("auto")
    public RandomGenerator autoRandom() {
        return ThreadLocalRandom.current();
    }
}