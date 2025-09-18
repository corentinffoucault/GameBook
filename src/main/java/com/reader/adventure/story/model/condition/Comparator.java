package com.reader.adventure.story.model.condition;

import com.reader.adventure.game.ComparatorKey;

import java.util.Map;
import java.util.function.BiPredicate;

public class Comparator {

        private static final Map<ComparatorKey, BiPredicate<Integer, Integer>> OPERATORS = Map.of(
                ComparatorKey.LT,  (a, b) -> a < b,
                ComparatorKey.GT,  (a, b) -> a > b,
                ComparatorKey.LTE, (a, b) -> a <= b,
                ComparatorKey.GTE, (a, b) -> a >= b,
                ComparatorKey.EQ, Integer::equals,
                ComparatorKey.NEQ, (a, b) -> !a.equals(b)
        );

        public static boolean compare(int a, ComparatorKey operator, int b) {
            BiPredicate<Integer, Integer> predicate = OPERATORS.get(operator);
            if (predicate == null) {
                throw new IllegalArgumentException("Opérateur non reconnu : " + operator);
            }
            return predicate.test(a, b);
        }
}
