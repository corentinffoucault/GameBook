package com.reader.adventure.story.dao.Jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.reader.adventure.game.AttributeKey;

import java.io.IOException;

public class AttributeDeserializerJackson extends StdDeserializer<AttributeKey> {

    public AttributeDeserializerJackson() {
        super(AttributeKey.class);
    }

    @Override
    public AttributeKey deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String name = p.getText();
        return AttributeKey.valueOf(name.toUpperCase());
    }
}
