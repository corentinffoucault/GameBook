package com.reader.adventure.game;

public enum ComparatorKey {

    EQ("=", "!="),
    NEQ("!=", "="),
    GT(">", "<="),
    GTE(">=", "<"),
    LTE("<=", ">"),
    LT("<", ">=");

    private final String symbol;
    private final String inverseSymbol;

    ComparatorKey(String symbol, String inverseSymbol) {
        this.symbol = symbol;
        this.inverseSymbol = inverseSymbol;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getInverseSymbol() {
        return inverseSymbol;
    }
}
