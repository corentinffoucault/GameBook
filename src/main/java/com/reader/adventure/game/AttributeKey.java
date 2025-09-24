package com.reader.adventure.game;

public enum AttributeKey {
    AG("agilité"),
    FO("force"),
    INT("intelligence"),
    CHA("charme"),
    COU("courage"),
    ATT("attaque"),
    PAR("parade"),
    JET("jet"),
    ESQ("esquive"),
    FOU("fouille"),
    ING("ingéniosité"),
    MAG_RES("résistance magique"),
    MAG_PHY("magie physique"),
    MAG_PSY("magie psychique");

    private final String name;

    AttributeKey(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

