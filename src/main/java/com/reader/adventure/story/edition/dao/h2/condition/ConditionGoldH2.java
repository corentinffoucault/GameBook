package com.reader.adventure.story.edition.dao.h2.condition;

import com.reader.adventure.game.ComparatorKey;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("GOLD")
public class ConditionGoldH2 extends AConditionH2 {
    @Column(nullable = true, name = "value_col")
    Integer value;

    ConditionGoldH2() {
        super();
    }

    ConditionGoldH2(Integer value, ComparatorKey comparator) {
        super(comparator);
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
