package com.reader.adventure.story.edition.dao.h2.condition;

import com.reader.adventure.game.AttributeKey;
import com.reader.adventure.game.ComparatorKey;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("ATTRIBUTE")
public class ConditionAttributesH2 extends AConditionH2 {
    @Column(nullable = true)
    AttributeKey attribute;

    ConditionAttributesH2() {
        super();
    }
    ConditionAttributesH2(AttributeKey attribute,  ComparatorKey comparator) {
        super(comparator);
        this.attribute = attribute;
    }

    public AttributeKey getAttribute() {
        return attribute;
    }

    public void setAttribute(AttributeKey attribute) {
        this.attribute = attribute;
    }
}