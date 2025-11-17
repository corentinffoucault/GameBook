package com.reader.adventure.story.read.dao.Jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.reader.adventure.game.ComparatorKey;

import java.io.IOException;
import java.util.Map;

public class ComparatorDeserializerJackson extends StdDeserializer<ComparatorKey> {

    public ComparatorDeserializerJackson() {
        super(ComparatorKey.class);
    }

    private static final Map<String, ComparatorKey> SYMBOL_TO_ENUM = Map.of(
            "==", ComparatorKey.EQ,
            ">", ComparatorKey.GT,
            "<", ComparatorKey.LT,
            ">=", ComparatorKey.GTE,
            "<=", ComparatorKey.LTE,
            "!=", ComparatorKey.NEQ
    );

    @Override
    public ComparatorKey deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String name = p.getText();
        return SYMBOL_TO_ENUM.get(name);
    }
}
