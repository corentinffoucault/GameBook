package com.reader.adventure.story.model.condition;

import java.util.Map;
import java.util.function.BiPredicate;

public class Comparator {

        private static final Map<String, BiPredicate<Integer, Integer>> OPERATORS = Map.of(
                "<",  (a, b) -> a < b,
                ">",  (a, b) -> a > b,
                "<=", (a, b) -> a <= b,
                ">=", (a, b) -> a >= b,
                "==", Integer::equals,
                "!=", (a, b) -> !a.equals(b)
        );

        public static boolean compare(int a, int b, String operator) {
            BiPredicate<Integer, Integer> predicate = OPERATORS.get(operator);
            if (predicate == null) {
                throw new IllegalArgumentException("Opérateur non reconnu : " + operator);
            }
            return predicate.test(a, b);
        }
}
